package com.qa.framework.steps;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.framework.engine.DriverManager;
import com.qa.framework.engine.ExcelReaderandwrite;
import com.qa.framework.utils.FailureArtifacts;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private final Logger log = LogManager.getLogger(Hooks.class);
    private final DateTimeFormatter logFileTimestamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
    private final DateTimeFormatter displayTimestamp = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    private final Path runLogDirectory = Paths.get("target", "runLogs");
    private final Path screenshotDirectory = Paths.get("target", "failure-artifacts", "screenshots");

    private final DriverManager driverManager;

    private ExecutorService executorService;
    private ExcelReaderandwrite readerWorker;
    private PrintStream scenarioLog;
    private boolean screenshotCaptured;

    public Hooks(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Before(order = 0)
    public void setup() {
        if (readerWorker == null) {
            executorService = Executors.newSingleThreadExecutor();
            readerWorker = new ExcelReaderandwrite();
            executorService.submit(readerWorker);
            log.info("Excel reader worker started");
        }
    }

    @Before(order = 1)
    public void sessionIn(Scenario scenario) {
        try {
            Files.createDirectories(runLogDirectory);
            Path logFile = runLogDirectory.resolve(FailureArtifacts.fileName(scenario.getName()) + "_"
                    + LocalDateTime.now().format(logFileTimestamp) + ".txt");
            scenarioLog = new PrintStream(
                    Files.newOutputStream(logFile, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE),
                    true, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to create the scenario log file for: " + scenario.getName(),
                    exception);
        }

        logInfo("//===============Session In=======================//");
        logInfo("Test Execution started at " + LocalDateTime.now().format(displayTimestamp));
        logInfo("Scenario '" + scenario.getName() + "' is executing.");
        logInfo("Scenario ID: " + scenario.getId());
    }

    @AfterStep
    public void captureFailureScreenshot(Scenario scenario) {
        if (!scenario.isFailed() || screenshotCaptured) {
            return;
        }
        if (!driverManager.isDriverInitialized()) {
            logError("Unable to capture failure screenshot because WebDriver is not initialized", null);
            return;
        }

        WebDriver driver = driverManager.getDriver();
        if (!(driver instanceof TakesScreenshot)) {
            logError("The current WebDriver does not support screenshots", null);
            return;
        }

        try {
            Files.createDirectories(screenshotDirectory);
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path screenshotPath = screenshotDirectory
                    .resolve(FailureArtifacts.fileName(scenario.getName()) + ".png")
                    .toAbsolutePath()
                    .normalize();
            Files.write(screenshotPath, screenshot, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);

            scenario.attach(screenshot, "image/png", "Failed Screenshot");
            FailureArtifacts.add(scenario.getId(), "Screenshot: " + screenshotPath);
            screenshotCaptured = true;
            logInfo("Failure screenshot saved: " + screenshotPath);
        } catch (IOException | RuntimeException exception) {
            logError("Error while capturing the failure screenshot", exception);
        }
    }

    @After(order = 2)
    public void sessionOut(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logError("Scenario FAILED: '" + scenario.getName() + "' [" + scenario.getStatus()
                        + "] - see test-output/logs/failed-scenarios.log for the failed step and error", null);
            } else {
                logInfo("^^^^Test successfully completed^^^^");
            }
            logInfo("Test Execution completed at " + LocalDateTime.now().format(displayTimestamp));
            logInfo("//===============Session Out=======================//");
        } finally {
            if (scenarioLog != null) {
                scenarioLog.close();
                scenarioLog = null;
            }
        }
    }

    @After(order = 1)
    public void tearDown() {
        try {
            ExcelReaderandwrite.stopWorker();
            if (executorService != null) {
                executorService.shutdown();
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            if (executorService != null) {
                executorService.shutdownNow();
            }
            logError("Interrupted while stopping the Excel reader worker", exception);
        } finally {
            readerWorker = null;
            executorService = null;
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        driverManager.quitDriver();
    }

    private void logInfo(String message) {
        log.info(message);
        if (scenarioLog != null) {
            scenarioLog.println(message);
        }
    }

    private void logError(String message, Throwable error) {
        if (error == null) {
            log.error(message);
        } else {
            log.error(message, error);
        }
        if (scenarioLog != null) {
            scenarioLog.println(message);
            if (error != null) {
                error.printStackTrace(scenarioLog);
            }
        }
    }
}
