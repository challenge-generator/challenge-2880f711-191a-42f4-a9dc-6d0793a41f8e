package com.bank.performance.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner de Cucumber para ejecutar los escenarios de pruebas de comportamiento.
 * Configura el formato de los reportes y la ubicación de los archivos feature.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.bank.performance.steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    tags = "@performance",
    dryRun = false,
    strict = true
)
public class RunCucumberTest {
    /**
     * Runner principal para ejecutar todos los escenarios de pruebas
     * marcados con la etiqueta @performance.
     * Los resultados se generan en formato HTML, JSON y JUnit XML.
     */
}