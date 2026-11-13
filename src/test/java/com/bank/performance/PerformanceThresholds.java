package com.bank.performance;

public class PerformanceThresholds {

    private static final double DEFAULT_MAX_AVG_RESPONSE_TIME_MS = 500.0;
    private static final double DEFAULT_MAX_P95_RESPONSE_TIME_MS = 1000.0;
    private static final double DEFAULT_MAX_P99_RESPONSE_TIME_MS = 2000.0;
    private static final double DEFAULT_MAX_ERROR_RATE_PERCENT = 1.0;
    private static final double DEFAULT_MIN_THROUGHPUT_RPS = 50.0;
    private static final double DEFAULT_MAX_CPU_USAGE_PERCENT = 80.0;
    private static final double DEFAULT_MAX_MEMORY_USAGE_PERCENT = 85.0;
    private static final double DEFAULT_LATENCY_TOLERANCE_PERCENT = 20.0;
    private static final int DEFAULT_WARMUP_DURATION_SECONDS = 30;
    private static final int DEFAULT_TEST_DURATION_SECONDS = 300;

    private static double maxAvgResponseTimeMs = DEFAULT_MAX_AVG_RESPONSE_TIME_MS;
    private static double maxP95ResponseTimeMs = DEFAULT_MAX_P95_RESPONSE_TIME_MS;
    private static double maxP99ResponseTimeMs = DEFAULT_MAX_P99_RESPONSE_TIME_MS;
    private static double maxErrorRatePercent = DEFAULT_MAX_ERROR_RATE_PERCENT;
    private static double minThroughputRps = DEFAULT_MIN_THROUGHPUT_RPS;
    private static double maxCpuUsagePercent = DEFAULT_MAX_CPU_USAGE_PERCENT;
    private static double maxMemoryUsagePercent = DEFAULT_MAX_MEMORY_USAGE_PERCENT;
    private static double latencyTolerancePercent = DEFAULT_LATENCY_TOLERANCE_PERCENT;
    private static int warmupDurationSeconds = DEFAULT_WARMUP_DURATION_SECONDS;
    private static int testDurationSeconds = DEFAULT_TEST_DURATION_SECONDS;

    private static String environment = "development";
    private static boolean thresholdsLoaded = false;

    private PerformanceThresholds() {
    }

    public static void loadFromEnvironment(String env) {
        environment = env != null ? env : "development";
        loadThresholdsForEnvironment();
        thresholdsLoaded = true;
    }

    private static void loadThresholdsForEnvironment() {
        switch (environment.toLowerCase()) {
            case "production":
            case "prod":
                setProductionThresholds();
                break;
            case "staging":
            case "stage":
                setStagingThresholds();
                break;
            case "uat":
                setUatThresholds();
                break;
            case "development":
            case "dev":
            default:
                setDevelopmentThresholds();
                break;
        }
    }

    private static void setDevelopmentThresholds() {
        maxAvgResponseTimeMs = 1000.0;
        maxP95ResponseTimeMs = 2000.0;
        maxP99ResponseTimeMs = 3000.0;
        maxErrorRatePercent = 5.0;
        minThroughputRps = 10.0;
        maxCpuUsagePercent = 90.0;
        maxMemoryUsagePercent = 90.0;
        latencyTolerancePercent = 50.0;
        warmupDurationSeconds = 10;
        testDurationSeconds = 60;
    }

    private static void setStagingThresholds() {
        maxAvgResponseTimeMs = 750.0;
        maxP95ResponseTimeMs = 1500.0;
        maxP99ResponseTimeMs = 2500.0;
        maxErrorRatePercent = 2.0;
        minThroughputRps = 30.0;
        maxCpuUsagePercent = 85.0;
        maxMemoryUsagePercent = 88.0;
        latencyTolerancePercent = 30.0;
        warmupDurationSeconds = 20;
        testDurationSeconds = 180;
    }

    private static void setUatThresholds() {
        maxAvgResponseTimeMs = 600.0;
        maxP95ResponseTimeMs = 1200.0;
        maxP99ResponseTimeMs = 2000.0;
        maxErrorRatePercent = 1.5;
        minThroughputRps = 40.0;
        maxCpuUsagePercent = 82.0;
        maxMemoryUsagePercent = 86.0;
        latencyTolerancePercent = 25.0;
        warmupDurationSeconds = 25;
        testDurationSeconds = 240;
    }

    private static void setProductionThresholds() {
        maxAvgResponseTimeMs = 500.0;
        maxP95ResponseTimeMs = 1000.0;
        maxP99ResponseTimeMs = 2000.0;
        maxErrorRatePercent = 1.0;
        minThroughputRps = 50.0;
        maxCpuUsagePercent = 80.0;
        maxMemoryUsagePercent = 85.0;
        latencyTolerancePercent = 20.0;
        warmupDurationSeconds = 30;
        testDurationSeconds = 300;
    }

    public static double getMaxAvgResponseTimeMs() {
        return maxAvgResponseTimeMs;
    }

    public static double getMaxP95ResponseTimeMs() {
        return maxP95ResponseTimeMs;
    }

    public static double getMaxP99ResponseTimeMs() {
        return maxP99ResponseTimeMs;
    }

    public static double getMaxErrorRatePercent() {
        return maxErrorRatePercent;
    }

    public static double getMinThroughputRps() {
        return minThroughputRps;
    }

    public static double getMaxCpuUsagePercent() {
        return maxCpuUsagePercent;
    }

    public static double getMaxMemoryUsagePercent() {
        return maxMemoryUsagePercent;
    }

    public static double getLatencyTolerancePercent() {
        return latencyTolerancePercent;
    }

    public static int getWarmupDurationSeconds() {
        return warmupDurationSeconds;
    }

    public static int getTestDurationSeconds() {
        return testDurationSeconds;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static boolean areThresholdsLoaded() {
        return thresholdsLoaded;
    }

    public static void setCustomThreshold(String thresholdName, double value) {
        switch (thresholdName.toLowerCase()) {
            case "maxavgrespons":
                maxAvgResponseTimeMs = value;
                break;
            case "maxp95":
                maxP95ResponseTimeMs = value;
                break;
            case "maxp99":
                maxP99ResponseTimeMs = value;
                break;
            case "maxerrorrate":
                maxErrorRatePercent = value;
                break;
            case "minthroughput":
                minThroughputRps = value;
                break;
            case "maxcpu":
                maxCpuUsagePercent = value;
                break;
            case "maxmemory":
                maxMemoryUsagePercent = value;
                break;
            default:
                throw new IllegalArgumentException("Unknown threshold: " + thresholdName);
        }
    }

    public static String generateThresholdReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Performance Thresholds Configuration ===\n");
        report.append("Environment: ").append(environment).append("\n");
        report.append("Max Average Response Time: ").append(maxAvgResponseTimeMs).append(" ms\n");
        report.append("Max P95 Response Time: ").append(maxP95ResponseTimeMs).append(" ms\n");
        report.append("Max P99 Response Time: ").append(maxP99ResponseTimeMs).append(" ms\n");
        report.append("Max Error Rate: ").append(maxErrorRatePercent).append("%\n");
        report.append("Min Throughput: ").append(minThroughputRps).append(" req/s\n");
        report.append("Max CPU Usage: ").append(maxCpuUsagePercent).append("%\n");
        report.append("Max Memory Usage: ").append(maxMemoryUsagePercent).append("%\n");
        report.append("Latency Tolerance: ").append(latencyTolerancePercent).append("%\n");
        report.append("Warmup Duration: ").append(warmupDurationSeconds).append(" seconds\n");
        report.append("Test Duration: ").append(testDurationSeconds).append(" seconds\n");
        return report.toString();
    }

    public static boolean validateThresholds() {
        if (maxAvgResponseTimeMs <= 0 || maxP95ResponseTimeMs <= 0 || maxP99ResponseTimeMs <= 0) {
            return false;
        }
        if (maxP95ResponseTimeMs > maxP99ResponseTimeMs) {
            return false;
        }
        if (maxAvgResponseTimeMs > maxP95ResponseTimeMs) {
            return false;
        }
        if (maxErrorRatePercent < 0 || maxErrorRatePercent > 100) {
            return false;
        }
        if (minThroughputRps <= 0) {
            return false;
        }
        if (maxCpuUsagePercent < 0 || maxCpuUsagePercent > 100) {
            return false;
        }
        if (maxMemoryUsagePercent < 0 || maxMemoryUsagePercent > 100) {
            return false;
        }
        return true;
    }

    public static void resetToDefaults() {
        maxAvgResponseTimeMs = DEFAULT_MAX_AVG_RESPONSE_TIME_MS;
        maxP95ResponseTimeMs = DEFAULT_MAX_P95_RESPONSE_TIME_MS;
        maxP99ResponseTimeMs = DEFAULT_MAX_P99_RESPONSE_TIME_MS;
        maxErrorRatePercent = DEFAULT_MAX_ERROR_RATE_PERCENT;
        minThroughputRps = DEFAULT_MIN_THROUGHPUT_RPS;
        maxCpuUsagePercent = DEFAULT_MAX_CPU_USAGE_PERCENT;
        maxMemoryUsagePercent = DEFAULT_MAX_MEMORY_USAGE_PERCENT;
        latencyTolerancePercent = DEFAULT_LATENCY_TOLERANCE_PERCENT;
        warmupDurationSeconds = DEFAULT_WARMUP_DURATION_SECONDS;
        testDurationSeconds = DEFAULT_TEST_DURATION_SECONDS;
        environment = "development";
        thresholdsLoaded = false;
    }
}