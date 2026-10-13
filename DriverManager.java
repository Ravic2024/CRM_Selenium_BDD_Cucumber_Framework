package com.qa.framework.engine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverManager {

    private static final Logger log = LogManager.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void initializeDriver(String domain) {
        if (!"WEB".equalsIgnoreCase(domain == null ? "" : domain.trim())) {
            throw new IllegalArgumentException("Unsupported execution domain: " + domain + ". Only WEB is supported.");
        }
        if (DRIVER.get() != null) {
            log.warn("WebDriver is already initialized for this scenario and will be reused.");
            return;
        }

        String browser = GlobalParams.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);
        log.info("Initializing {} browser", browser);

        WebDriver created = null;
        try {
            switch (browser) {
                case "chrome":
                    created = createChromeDriver();
                    break;
                case "edge":
                    created = createEdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser + ". Use 'chrome' or 'edge'.");
            }
            configureDriver(created);
            DRIVER.set(created);
            log.info("{} browser initialized successfully", browser);
        } catch (RuntimeException exception) {
            quitSilently(created);
            log.error("WebDriver initialization failed for browser: {}", browser, exception);
            throw new IllegalStateException("Unable to initialize WebDriver for browser: " + browser, exception);
        }
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized. Call initializeDriver() before using the driver.");
        }
        return driver;
    }

    public static boolean isDriverInitialized() {
        return DRIVER.get() != null;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
            log.info("WebDriver closed successfully");
        } catch (RuntimeException exception) {
            log.error("An error occurred while closing WebDriver", exception);
        } finally {
            DRIVER.remove();
        }
    }

    private static WebDriver createChromeDriver() {
        configureDriverExecutable("webdriver.chrome.driver", "chromedriver.exe", "chromeDriverPath");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--remote-allow-origins=*", "--ignore-ssl-errors=yes",
                "--ignore-certificate-errors", "--window-size=1920,1080");
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
        if (isHeadless()) {
            options.addArguments("--headless=new", "--disable-gpu");
        }
        options.setAcceptInsecureCerts(GlobalParams.getBooleanProperty("acceptInsecureCertificates", true));
        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        configureDriverExecutable("webdriver.edge.driver", "msedgedriver.exe", "edgeDriverPath");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate", "--remote-allow-origins=*", "--ignore-certificate-errors",
                "--window-size=1920,1080");
        if (isHeadless()) {
            options.addArguments("--headless=new", "--disable-gpu");
        }
        options.setAcceptInsecureCerts(GlobalParams.getBooleanProperty("acceptInsecureCertificates", true));
        return new EdgeDriver(options);
    }

    private static void configureDriverExecutable(String systemProperty, String fileName, String browserPathKey) {
        String configured = GlobalParams.getOptionalProperty(browserPathKey);
        if (configured == null || configured.isBlank()) {
            configured = GlobalParams.getOptionalProperty("driverPath");
        }

        Path driverPath = (configured == null || configured.isBlank())
                ? Paths.get(System.getProperty("user.dir"), "drivers", fileName)
                : Paths.get(System.getProperty("user.dir")).resolve(configured.trim());
        driverPath = driverPath.toAbsolutePath().normalize();

        if (Files.isRegularFile(driverPath)) {
            System.setProperty(systemProperty, driverPath.toString());
            log.info("Using browser driver: {}", driverPath);
        } else if (configured != null && !configured.isBlank()) {
            throw new IllegalStateException("Configured browser driver was not found: " + driverPath);
        } else {
            log.info("No local {} found. Selenium Manager will resolve the browser driver.", fileName);
        }
    }

    private static void configureDriver(WebDriver webDriver) {
        int pageLoadTimeout = GlobalParams.getIntegerProperty("pageLoadTimeout", 60);
        int scriptTimeout = GlobalParams.getIntegerProperty("scriptTimeout", 30);
        if (pageLoadTimeout <= 0 || scriptTimeout <= 0) {
            throw new IllegalArgumentException("pageLoadTimeout and scriptTimeout must be greater than zero.");
        }

        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptTimeout));

        if (!isHeadless()) {
            try {
                webDriver.manage().window().maximize();
            } catch (RuntimeException exception) {
                log.warn("Unable to maximize the browser window. Continuing with the configured size.", exception);
            }
        }
    }

    private static boolean isHeadless() {
        return GlobalParams.getBooleanProperty("headless", false);
    }

    private static void quitSilently(WebDriver webDriver) {
        if (webDriver == null) {
            return;
        }
        try {
            webDriver.quit();
        } catch (RuntimeException cleanupException) {
            log.error("Unable to close the partially initialized WebDriver.", cleanupException);
        }
    }
}
