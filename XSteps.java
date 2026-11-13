package com.bank.performance.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Step definitions para los escenarios de pruebas de performance.
 * Implementa los pasos Given-When-Then de los archivos .feature.
 */
public class XSteps {
    
    private Map<String, Object> contextoPrueba = new HashMap<>();
    private String tipoPruebaActual;
    private int usuariosSimulados;
    private long tiempoRespuestaObtenido;
    private double throughputObtenido;
    private double tasaErrorObtenida;
    private boolean umbralCumplido;
    
    @Dado("que el sistema bancario está configurado para ejecutar pruebas de rendimiento")
    public void sistemaConfiguradoParaPruebas() {
        contextoPrueba.put("sistema_inicializado", true);
        contextoPrueba.put("entorno", "preproduccion");
        contextoPrueba.put("base_url", "https://api.banco.com");
    }
    
    @Y("se han definido los umbrales de rendimiento para las métricas clave")
    public void umbralesDefinidos() {
        contextoPrueba.put("umbral_tiempo_respuesta_ms", 2000);
        contextoPrueba.put("umbral_throughput_rps", 100);
        contextoPrueba.put("umbral_tasa_error_porcentaje", 1.0);
    }
    
    @Cuando("se ejecuta una prueba de carga con {int} usuarios simultáneos")
    public void ejecutarPruebaCarga(int usuarios) {
        usuariosSimulados = usuarios;
        contextoPrueba.put("usuarios", usuarios);
        
        // Simulación de ejecución de prueba de carga
        tiempoRespuestaObtenido = calcularTiempoRespuesta(usuarios);
        throughputObtenido = calcularThroughput(usuarios);
        tasaErrorObtenida = calcularTasaError(usuarios);
        
        contextoPrueba.put("tiempo_respuesta", tiempoRespuestaObtenido);
        contextoPrueba.put("throughput", throughputObtenido);
        contextoPrueba.put("tasa_error", tasaErrorObtenida);
    }
    
    @Entonces("el tiempo de respuesta promedio debe ser menor a {int} milisegundos")
    public void validarTiempoRespuesta(int umbralMs) {
        long umbral = (long) contextoPrueba.getOrDefault("umbral_tiempo_respuesta_ms", umbralMs);
        umbralCumplido = tiempoRespuestaObtenido < umbral;
        
        Assert.assertTrue(
            String.format("Tiempo de respuesta %d ms excede el umbral de %d ms", 
                tiempoRespuestaObtenido, umbral),
            umbralCumplido
        );
    }
    
    @Y("el throughput debe ser mayor a {int} solicitudes por segundo")
    public void validarThroughput(int umbralRps) {
        double umbral = (double) contextoPrueba.getOrDefault("umbral_throughput_rps", umbralRps);
        umbralCumplido = throughputObtenido > umbral;
        
        Assert.assertTrue(
            String.format("Throughput %.2f rps no supera el umbral de %.2f rps", 
                throughputObtenido, umbral),
            umbralCumplido
        );
    }
    
    @Y("la tasa de error debe ser menor al {double} por ciento")
    public void validarTasaError(double umbralPorcentaje) {
        double umbral = (double) contextoPrueba.getOrDefault("umbral_tasa_error_porcentaje", umbralPorcentaje);
        umbralCumplido = tasaErrorObtenida < umbral;
        
        Assert.assertTrue(
            String.format("Tasa de error %.2f%% excede el umbral de %.2f%%", 
                tasaErrorObtenida, umbral),
            umbralCumplido
        );
    }
    
    @Cuando("se ejecuta una prueba de estrés incrementando la carga hasta {int} usuarios")
    public void ejecutarPruebaEstres(int usuariosMaximos) {
        contextoPrueba.put("tipo_prueba", "estres");
        contextoPrueba.put("usuarios_maximos", usuariosMaximos);
        
        // Simulación de prueba de estrés con incremento progresivo
        for (int usuarios = 10; usuarios <= usuariosMaximos; usuarios += 10) {
            tiempoRespuestaObtenido = calcularTiempoRespuesta(usuarios);
            throughputObtenido = calcularThroughput(usuarios);
            tasaErrorObtenida = calcularTasaError(usuarios);
            
            if (tiempoRespuestaObtenido > 5000 || tasaErrorObtenida > 5.0) {
                contextoPrueba.put("punto_ruptura", usuarios);
                contextoPrueba.put("tiempo_respuesta_ruptura", tiempoRespuestaObtenido);
                break;
            }
        }
    }
    
    @Entonces("el sistema debe mantener la estabilidad sin caídas catastróficas")
    public void validarEstabilidadSistema() {
        double tasaErrorMaxima = (double) contextoPrueba.getOrDefault("tasa_error", 0.0);
        
        Assert.assertTrue(
            "El sistema presentó comportamiento catastrófico bajo estrés",
            tasaErrorMaxima < 20.0
        );
    }
    
    @Y("el punto de ruptura debe identificarse claramente")
    public void validarPuntoRuptura() {
        Object puntoRuptura = contextoPrueba.get("punto_ruptura");
        
        Assert.assertNotNull(
            "No se identificó el punto de ruptura en la prueba de estrés",
            puntoRuptura
        );
    }
    
    @Cuando("se ejecuta una prueba de volumen con {int} registros en la base de datos")
    public void ejecutarPruebaVolumen(int registros) {
        contextoPrueba.put("tipo_prueba", "volumen");
        contextoPrueba.put("registros_bbdd", registros);
        
        // Simulación de prueba de volumen
        tiempoRespuestaObtenido = calcularTiempoRespuestaVolumen(registros);
        contextoPrueba.put("tiempo_respuesta", tiempoRespuestaObtenido);
    }
    
    @Entonces("las operaciones de consulta deben completarse dentro del tiempo esperado")
    public void validarTiempoConsultaVolumen() {
        long tiempoConsulta = (long) contextoPrueba.getOrDefault("tiempo_respuesta", 0L);
        long umbral = 3000; // 3 segundos para consultas con volumen
        
        Assert.assertTrue(
            String.format("Consulta con volumen tardó %d ms, excede el umbral de %d ms", 
                tiempoConsulta, umbral),
            tiempoConsulta < umbral
        );
    }
    
    @Dado("que se ha ejecutado una prueba de rendimiento previamente")
    public void pruebaPreviaEjecutada() {
        contextoPrueba.put("prueba_previa_ejecutada", true);
        // Simulación de métricas de prueba anterior
        contextoPrueba.put("tiempo_respuesta_baseline", 1500);
        contextoPrueba.put("throughput_baseline", 80);
    }
    
    @Cuando("se comparan los resultados actuales con la línea base")
    public void compararConBaseline() {
        double tiempoActual = (double) contextoPrueba.getOrDefault("tiempo_respuesta", 1500.0);
        double tiempoBaseline = (double) contextoPrueba.getOrDefault("tiempo_respuesta_baseline", 1500.0);
        double variacionPorcentaje = ((tiempoActual - tiempoBaseline) / tiempoBaseline) * 100;
        
        contextoPrueba.put("variacion_tiempo_porcentaje", variacionPorcentaje);
    }
    
    @Entonces("la degradación de rendimiento no debe exceder el {int} por ciento")
    public void validarDegradacion(int porcentajeMaximo) {
        double variacion = (double) contextoPrueba.getOrDefault("variacion_tiempo_porcentaje", 0.0);
        
        Assert.assertTrue(
            String.format("Degradación del %.2f%% excede el límite del %d%%", 
                variacion, porcentajeMaximo),
            variacion <= porcentajeMaximo
        );
    }
    
    private long calcularTiempoRespuesta(int usuarios) {
        // Simulación: tiempo aumenta con usuarios
        long base = 200;
        long incremento = usuarios * 5;
        return base + incremento + (long)(Math.random() * 100);
    }
    
    private double calcularThroughput(int usuarios) {
        // Simulación: throughput óptimo alrededor de 50-100 usuarios
        if (usuarios <= 50) return usuarios * 2.5;
        if (usuarios <= 100) return 100 + (usuarios - 50) * 0.8;
        return Math.max(10, 140 - (usuarios - 100) * 0.5);
    }
    
    private double calcularTasaError(int usuarios) {
        // Simulación: tasa de error aumenta con overload
        if (usuarios <= 80) return 0.1 + Math.random() * 0.5;
        if (usuarios <= 120) return 0.5 + (usuarios - 80) * 0.1;
        return Math.min(15.0, 4.0 + (usuarios - 120) * 0.15);
    }
    
    private long calcularTiempoRespuestaVolumen(int registros) {
        // Simulación: tiempo de consulta crece con volumen de datos
        long base = 500;
        long incremento = (long)(registros * 0.05);
        return base + incremento + (long)(Math.random() * 200);
    }
}