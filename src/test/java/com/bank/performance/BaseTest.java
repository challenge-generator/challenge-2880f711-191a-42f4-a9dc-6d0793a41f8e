package com.bank.performance;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.util.JMeterUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected static StandardJMeterEngine jmeterEngine;
    protected static String testPlanPath;
    protected static String resultsFilePath;
    protected static String reportOutputDir;
    protected static Properties testConfig;
    protected static Map<String, SampleResult> baselineResults;

    static {
        initializePaths();
        loadTestConfiguration();
    }

    private static void initializePaths() {
        String userDir = System.getProperty("user.dir");
        testPlanPath = userDir + File.separator + "src" + File.separator + "test" 
                    + File.separator + "jmeter";
        resultsFilePath = userDir + File.separator + "target" + File.separator 
                        + "jmeter-results" + File.separator + "results.jtl";
        reportOutputDir = userDir + File.separator + "target" + File.separator 
                        + "jmeter-reports";
        
        new File(resultsFilePath).getParentFile().mkdirs();
        new File(reportOutputDir).mkdirs();
    }

    private static void loadTestConfiguration() {
        testConfig = new Properties();
        String configPath = System.getProperty("test.config", 
            "src/test/resources/test.properties");
        
        try (FileInputStream fis = new FileInputStream(configPath)) {
            testConfig.load(fis);
        } catch (IOException e) {
            System.out.println("Config file not found, using defaults: " + e.getMessage());
            setDefaultConfiguration();
        }
        
        baselineResults = new HashMap<>();
    }

    private static void setDefaultConfiguration() {
        testConfig.setProperty("base.url", "http://localhost:8080/api");
        testConfig.setProperty("threads", "10");
        testConfig.setProperty("rampup.period", "10");
        testConfig.setProperty("duration.seconds", "60");
        testConfig.setProperty("loops", "1");
    }

    protected static void setupJMeter() {
        String jmeterHome = System.getProperty("jmeter.home", 
            System.getenv("JMETER_HOME"));
        
        if (jmeterHome == null || jmeterHome.isEmpty()) {
            jmeterHome = System.getProperty("user.home") + File.separator + ".jmeter";
        }
        
        File jmeterHomeDir = new File(jmeterHome);
        if (!jmeterHomeDir.exists()) {
            jmeterHomeDir.mkdirs();
        }
        
        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.loadResource("bin/saveservice.properties");
        
        Properties jmeterProps = JMeterUtils.getJMeterProperties();
        jmeterProps.setProperty("jmeter.save.saveservice.output_format", "xml");
        jmeterProps.setProperty("jmeter.save.saveservice.response_data", "true");
        jmeterProps.setProperty("jmeter.save.saveservice.samplerData", "true");
        
        jmeterEngine = new StandardJMeterEngine();
    }

    protected static void configureTestPlan(String testFileName) {
        String testFilePath = testPlanPath + File.separator + testFileName;
        File testFile = new File(testFilePath);
        
        if (!testFile.exists()) {
            throw new RuntimeException("Test file not found: " + testFilePath);
        }
    }

    protected static ResultCollector createResultCollector() {
        Summariser summariser = new Summariser("summary");
        ResultCollector resultCollector = new ResultCollector(summariser);
        
        try {
            resultCollector.setFilename(resultsFilePath);
        } catch (Exception e) {
            System.out.println("Could not set results file: " + e.getMessage());
        }
        
        return resultCollector;
    }

    protected static void runTestPlan(String testFileName) {
        if (jmeterEngine == null) {
            setupJMeter();
        }
        
        String testFilePath = testPlanPath + File.separator + testFileName;
        org.apache.jmeter.save.SaveService.loadTestPlan(testFilePath);
        
        jmeterEngine.run();
    }

    protected static void stopTest() {
        if (jmeterEngine != null) {
            jmeterEngine.stop();
        }
    }

    protected static void teardown() {
        if (jmeterEngine != null) {
            jmeterEngine.exit();
        }
    }

    protected String getBaseUrl() {
        return testConfig.getProperty("base.url", "http://localhost:8080/api");
    }

    protected int getThreadCount() {
        return Integer.parseInt(testConfig.getProperty("threads", "10"));
    }

    protected int getRampUpPeriod() {
        return Integer.parseInt(testConfig.getProperty("rampup.period", "10"));
    }

    protected int getDuration() {
        return Integer.parseInt(testConfig.getProperty("duration.seconds", "60"));
    }

    protected void storeBaseline(String testName, SampleResult result) {
        baselineResults.put(testName, result);
    }

    protected SampleResult getBaseline(String testName) {
        return baselineResults.get(testName);
    }

    protected boolean compareWithBaseline(String testName, SampleResult current) {
        SampleResult baseline = getBaseline(testName);
        if (baseline == null) {
            return true;
        }
        
        double baselineTime = baseline.getTime();
        double currentTime = current.getTime();
        double threshold = PerformanceThresholds.getLatencyTolerancePercent() / 100.0;
        
        return currentTime <= baselineTime * (1 + threshold);
    }

    protected void logTestSummary(String testName, SampleResult result) {
        System.out.println("=== Test Summary: " + testName + " ===");
        System.out.println("Samples: " + result.getSampleCount());
        System.out.println("Errors: " + result.getErrorCount());
        System.out.println("Average Response Time: " + result.getTime() + " ms");
        System.out.println("Min Response Time: " + result.getTimeMin() + " ms");
        System.out.println("Max Response Time: " + result.getTimeMax() + " ms");
        System.out.println("Throughput: " + String.format("%.2f", result.getThroughput()) + " req/sec");
    }

    protected void assertPerformanceThresholds(SampleResult result) {
        double avgResponseTime = result.getTime();
        double maxResponseTime = result.getTimeMax();
        double errorRate = (double) result.getErrorCount() / result.getSampleCount() * 100;
        double throughput = result.getThroughput();
        
        if (avgResponseTime > PerformanceThresholds.getMaxAvgResponseTimeMs()) {
            throw new AssertionError("Average response time " + avgResponseTime 
                + "ms exceeds threshold " + PerformanceThresholds.getMaxAvgResponseTimeMs() + "ms");
        }
        
        if (maxResponseTime > PerformanceThresholds.getMaxP95ResponseTimeMs()) {
            throw new AssertionError("Max response time " + maxResponseTime 
                + "ms exceeds P95 threshold " + PerformanceThresholds.getMaxP95ResponseTimeMs() + "ms");
        }
        
        if (errorRate > PerformanceThresholds.getMaxErrorRatePercent()) {
            throw new AssertionError("Error rate " + String.format("%.2f", errorRate) 
                + "% exceeds threshold " + PerformanceThresholds.getMaxErrorRatePercent() + "%");
        }
        
        if (throughput < PerformanceThresholds.getMinThroughputRps()) {
            throw new AssertionError("Throughput " + String.format("%.2f", throughput) 
                + " req/s is below minimum " + PerformanceThresholds.getMinThroughputRps() + " req/s");
        }
    }
}