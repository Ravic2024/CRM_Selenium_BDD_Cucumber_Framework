package com.qa.framework.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class FailureArtifacts {

    private static final Map<String, List<String>> ARTIFACTS = new ConcurrentHashMap<>();
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private FailureArtifacts() {
    }

    public static void add(String scenarioId, String description) {
        ARTIFACTS.computeIfAbsent(scenarioId, k -> new CopyOnWriteArrayList<>()).add(description);
    }

    public static List<String> remove(String scenarioId) {
        List<String> list = ARTIFACTS.remove(scenarioId);
        return list == null ? List.of() : list;
    }

    public static String fileName(String scenarioName) {
        String safe = scenarioName.replaceAll("[^a-zA-Z0-9-_]+", "_");
        if (safe.length() > 80) {
            safe = safe.substring(0, 80);
        }
        return safe + "_" + LocalDateTime.now().format(TS) + "_" + Thread.currentThread().getId();
    }
}

