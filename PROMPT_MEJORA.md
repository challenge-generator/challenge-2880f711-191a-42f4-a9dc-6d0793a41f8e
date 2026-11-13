# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `src/test/jmeter/load_test.jmx`
- `src/test/jmeter/stress_test.jmx`
- `src/test/resources/test_data.csv`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `RunCucumberTest.java` — `io.cucumber.junit`: El import io.cucumber.junit.Cucumber pertenece a io.cucumber.junit, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `RunCucumberTest.java` — `org.junit.runner`: El import org.junit.runner.RunWith pertenece a org.junit.runner, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `XSteps.java` — `io.cucumber.java`: El import io.cucumber.java.es.Cuando pertenece a io.cucumber.java, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `XSteps.java` — `org.junit`: El import org.junit.Assert pertenece a org.junit, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Pruebas de Performance, Advanced L2

### Brecha de conocimiento
Reconoce la importancia de llevar a cabo pruebas no funcionales, como las pruebas de carga, rendimiento, volumen, estrés, estabilidad, linea base, tolerancia a fallas y robustez, dentro de los proyectos, Reconoce la importancia de llevar a cabo pruebas no funcionales, como las pruebas de carga, rendimiento, volumen, estrés, estabilidad, linea base, tolerancia a fallas y robustez, dentro de los proyectos

### Reto
- Tema: Importancia de las pruebas de performance
- Seniority: advanced-l2
- Tipo: theoretical
- Título: Fundamentos de pruebas de performance
- Tiempo estimado: 2 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Introducción a las pruebas no funcionales — objetivo: Comprender la importancia de las pruebas no funcionales en un proyecto de software. — entregable (NO resolver): Un documento que describe las diferentes tipos de pruebas no funcionales y su importancia en un proyecto bancario.
- Fase 2: Evaluación de casos de uso — objetivo: Identificar y evaluar casos de uso específicos donde las pruebas no funcionales son críticas. — entregable (NO resolver): Un informe que detalla los casos de uso seleccionados y cómo se aplicarían las pruebas no funcionales a cada uno.
- Fase 3: Recomendaciones y mejores prácticas — objetivo: Proponer recomendaciones y mejores prácticas para la implementación de pruebas no funcionales en proyectos de software. — entregable (NO resolver): Un documento que presenta tus recomendaciones y mejores prácticas para la implementación de pruebas no funcionales en proyectos de software.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.bank</groupId>
    <artifactId>performance-tests</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <jmeter.version>5.6.3</jmeter.version>
        <jmeter.maven.plugin.version>3.8.0</jmeter.maven.plugin.version>
        <surefire.plugin.version>3.2.5</surefire.plugin.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${surefire.plugin.version}</version>
            </plugin>
            <plugin>
                <groupId>com.lazerycode.jmeter</groupId>
                <artifactId>jmeter-maven-plugin</artifactId>
                <version>${jmeter.maven.plugin.version}</version>
                <executions>
                    <execution>
                        <id>jmeter-tests</id>
                        <goals>
                            <goal>jmeter</goal>
                        </goals>
                        <configuration>
                            <testFilesIncluded>
                                <testFile>load_test.jmx</testFile>
                                <testFile>stress_test.jmx</testFile>
                            </testFilesIncluded>
                            <propertiesJMeter>
                                <jmeter.save.saveservice.output_format>xml</jmeter.save.saveservice.output_format>
                                <jmeter.save.saveservice.assertion_results>all</jmeter.save.saveservice.assertion_results>
                                <jmeter.save.saveservice.bytes>true</jmeter.save.saveservice.bytes>
                                <jmeter.save.saveservice.response_code>true</jmeter.save.saveservice.response_code>
                            </propertiesJMeter>
                            <generateReports>true</generateReports>
                            <reportDir>${project.build.directory}/jmeter-reports</reportDir>
                        </configuration>
                    </execution>
                </executions>
                <dependencies>
                    <dependency>
                        <groupId>org.apache.jmeter</groupId>
                        <artifactId>ApacheJMeter_core</artifactId>
                        <version>${jmeter.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>org.apache.jmeter</groupId>
                        <artifactId>ApacheJMeter_http</artifactId>
                        <version>${jmeter.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>org.apache.jmeter</groupId>
                        <artifactId>ApacheJMeter_functions</artifactId>
                        <version>${jmeter.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>org.apache.jmeter</groupId>
                        <artifactId>jorphan</artifactId>
                        <version>${jmeter.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.apache.jmeter</groupId>
            <artifactId>ApacheJMeter_core</artifactId>
            <version>${jmeter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.jmeter</groupId>
            <artifactId>ApacheJMeter_http</artifactId>
            <version>${jmeter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.jmeter</groupId>
            <artifactId>ApacheJMeter_functions</artifactId>
            <version>${jmeter.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.jmeter</groupId>
            <artifactId>jorphan</artifactId>
            <version>${jmeter.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>

// === ARCHIVO: RunCucumberTest.java ===
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

// === ARCHIVO: XSteps.java ===
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

// === ARCHIVO: README.md ===
# Pruebas de Rendimiento - Banco

## Descripción del Proyecto

Proyecto de pruebas de rendimiento para la aplicación bancaria. El objetivo es evaluar el comportamiento del sistema bajo diferentes condiciones de carga, estrés y volumen.

## Estructura del Proyecto

```
src/
├── test/
│   ├── java/
│   │   └── com/
│   │       └── bank/
│   │           └── performance/
│   │               ├── steps/           # Step definitions de Cucumber
│   │               │   ├── RunCucumberTest.java
│   │               │   └── XSteps.java
│   │               ├── BaseTest.java
│   │               └── PerformanceThresholds.java
│   ├── jmeter/                    # Planes de prueba JMeter
│   │   ├── load_test.jmx
│   │   └── stress_test.jmx
│   └── resources/
│       ├── features/              # Escenarios Gherkin
│       └── test_data.csv          # Datos de prueba
target/
├── jmeter-reports/                # Reportes HTML de JMeter
└── cucumber-reports/              # Reportes de Cucumber
```

## Requisitos

- Java 21
- Maven 3.8+
- JMeter 5.6.3 (gestionado por el plugin de Maven)

## Ejecución de Pruebas

### Pruebas de Comportamiento (Cucumber)

```bash
mvn test
```

Ejecuta los escenarios de pruebas definidos en los archivos `.feature` con la etiqueta `@performance`.

### Pruebas de Rendimiento (JMeter)

```bash
mvn jmeter:jmeter
```

Ejecuta los planes de prueba JMeter (`load_test.jmx` y `stress_test.jmx`) y genera reportes HTML.

### Generación de Reportes

Los reportes se generan automáticamente en:
- **JMeter**: `target/jmeter-reports/`
- **Cucumber**: `target/cucumber-reports/`

## Configuración de Umbrales

Los umbrales de rendimiento se configuran en `PerformanceThresholds.java`:

- Tiempo de respuesta máximo: 2000 ms
- Throughput mínimo: 100 req/s
- Tasa de error máxima: 1.0%

## Tipos de Pruebas

### Pruebas de Carga (Load Testing)
Valida el comportamiento del sistema bajo carga esperada en producción.

### Pruebas de Estrés (Stress Testing)
Identifica el punto de ruptura del sistema superando los límites operativos.

### Pruebas de Volumen (Volume Testing)
Evalúa el rendimiento con grandes volúmenes de datos.

### Pruebas de Línea Base (Baseline Testing)
Compara el rendimiento actual contra métricas históricas.

## Interpretación de Resultados

### Métricas Clave

| Métrica | Descripción | Umbral Ideal |
|---------|-------------|--------------|
| Response Time | Tiempo de respuesta promedio | < 2000 ms |
| Throughput | Solicitudes por segundo | > 100 rps |
| Error Rate | Porcentaje de errores | < 1% |
| Apdex | Índice de satisfacción | > 0.9 |

### Criterios de Éxito

Una prueba de rendimiento es exitosa cuando:
1. El tiempo de respuesta está dentro del umbral
2. El throughput cumple con los requisitos
3. La tasa de errores es inferior al límite
4. El sistema se recupera después de sobrecarga

## Entornos

Las pruebas pueden ejecutarse en diferentes entornos modificando la configuración en `BaseTest.java`:
- Desarrollo: `http://localhost:8080`
- Preproducción: `https://pre.banco.com`
- Producción: `https://api.banco.com` (solo pruebas de humo)

## Integración Continua

El proyecto está configurado para ejecutarse en pipelines de CI/CD. Los reportes se publican como artefactos de build.

// === ARCHIVO: src/test/java/com/bank/performance/BaseTest.java ===
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

// === ARCHIVO: src/test/java/com/bank/performance/PerformanceThresholds.java ===
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

// === ARCHIVO: reports/performance_report.html ===
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reporte de Pruebas de Rendimiento - Aplicación Bancaria</title>
    <style>
        :root {
            --primary-color: #1a5f7a;
            --secondary-color: #159895;
            --success-color: #2ecc71;
            --warning-color: #f39c12;
            --danger-color: #e74c3c;
            --bg-light: #f8f9fa;
            --text-dark: #2c3e50;
            --border-color: #dee2e6;
        }
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--bg-light);
            color: var(--text-dark);
            line-height: 1.6;
        }
        
        .container {
            max-width: 1400px;
            margin: 0 auto;
            padding: 20px;
        }
        
        header {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 30px;
            border-radius: 10px;
            margin-bottom: 30px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        
        header h1 {
            font-size: 2.2em;
            margin-bottom: 10px;
        }
        
        header .meta-info {
            display: flex;
            gap: 30px;
            margin-top: 15px;
            font-size: 0.9em;
        }
        
        .summary-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .card {
            background: white;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            border-left: 4px solid var(--primary-color);
        }
        
        .card.success { border-left-color: var(--success-color); }
        .card.warning { border-left-color: var(--warning-color); }
        .card.danger { border-left-color: var(--danger-color); }
        
        .card h3 {
            font-size: 0.9em;
            text-transform: uppercase;
            color: #7f8c8d;
            margin-bottom: 10px;
        }
        
        .card .value {
            font-size: 2em;
            font-weight: bold;
            color: var(--text-dark);
        }
        
        .card .status {
            display: inline-block;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 0.85em;
            font-weight: 600;
            margin-top: 10px;
        }
        
        .status.pass { background-color: #d5f4e6; color: #1e8449; }
        .status.fail { background-color: #fadbd8; color: #c0392b; }
        .status.warn { background-color: #fdebd0; color: #d68910; }
        
        .section {
            background: white;
            border-radius: 10px;
            padding: 30px;
            margin-bottom: 30px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .section h2 {
            color: var(--primary-color);
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid var(--border-color);
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid var(--border-color);
        }
        
        th {
            background-color: #f8f9fa;
            font-weight: 600;
            color: var(--text-dark);
        }
        
        tr:hover {
            background-color: #f8f9fa;
        }
        
        .metric-bar {
            height: 25px;
            background-color: #e9ecef;
            border-radius: 5px;
            overflow: hidden;
            margin: 5px 0;
        }
        
        .metric-bar .fill {
            height: 100%;
            border-radius: 5px;
            transition: width 0.3s ease;
        }
        
        .fill.green { background-color: var(--success-color); }
        .fill.yellow { background-color: var(--warning-color); }
        .fill.red { background-color: var(--danger-color); }
        
        .chart-placeholder {
            height: 300px;
            background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #7f8c8d;
            font-style: italic;
        }
        
        .test-scenario {
            background: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 15px;
            border-left: 3px solid var(--secondary-color);
        }
        
        .test-scenario h4 {
            color: var(--primary-color);
            margin-bottom: 10px;
        }
        
        .tags {
            display: flex;
            gap: 8px;
            flex-wrap: wrap;
            margin-top: 10px;
        }
        
        .tag {
            padding: 4px 12px;
            border-radius: 15px;
            font-size: 0.8em;
            font-weight: 500;
        }
        
        .tag.load { background-color: #d6eaf8; color: #2471a3; }
        .tag.stress { background-color: #fadbd8; color: #c0392b; }
        .tag.volume { background-color: #d5f5e3; color: #1e8449; }
        .tag.stability { background-color: #fdebd0; color: #d68910; }
        
        footer {
            text-align: center;
            padding: 20px;
            color: #7f8c8d;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Reporte de Pruebas de Rendimiento</h1>
            <p>Aplicación Bancaria - Sistema de Gestión de Transacciones</p>
            <div class="meta-info">
                <span><strong>Fecha de ejecución:</strong> 2024-01-15 14:30:00</span>
                <span><strong>Entorno:</strong> Staging</span>
                <span><strong>Versión:</strong> 2.4.1</span>
                <span><strong>Duración total:</strong> 4h 23m</span>
            </div>
        </header>

        <div class="summary-cards">
            <div class="card success">
                <h3>Estado General</h3>
                <div class="value">APROBADO</div>
                <span class="status pass">4 de 5 escenarios pasaron</span>
            </div>
            <div class="card">
                <h3>Requests Totales</h3>
                <div class="value">1,247,832</div>
                <span class="status pass">Tasa de éxito: 99.2%</span>
            </div>
            <div class="card warning">
                <h3>Tiempo Promedio</h3>
                <div class="value">487 ms</div>
                <span class="status warn">Umbral: 500ms (cerca)</span>
            </div>
            <div class="card success">
                <h3>Throughput</h3>
                <div class="value">87 req/s</div>
                <span class="status pass">Meta: 50 req/s</span>
            </div>
        </div>

        <div class="section">
            <h2>Resumen Ejecutivo</h2>
            <p>Las pruebas de rendimiento se ejecutaron exitosamente en el entorno de staging. El sistema demostró capacidad para manejar la carga esperada de 1000 usuarios concurrentes con tiempos de respuesta dentro de los umbrales establecidos. Se identificó una degradación de rendimiento en el escenario de estrés máximo que requiere optimización.</p>
        </div>

        <div class="section">
            <h2>Métricas por Escenario de Prueba</h2>
            
            <table>
                <thead>
                    <tr>
                        <th>Escenario</th>
                        <th>Tipo</th>
                        <th>Usuarios</th>
                        <th>Requests</th>
                        <th>Tiempo Prom.</th>
                        <th>P95</th>
                        <th>P99</th>
                        <th>Errores</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Carga Normal</td>
                        <td><span class="tag load">Load</span></td>
                        <td>100</td>
                        <td>523,450</td>
                        <td>245 ms</td>
                        <td>380 ms</td>
                        <td>520 ms</td>
                        <td>0.1%</td>
                        <td><span class="status pass">PASS</span></td>
                    </tr>
                    <tr>
                        <td>Pico de Transacciones</td>
                        <td><span class="tag load">Load</span></td>
                        <td>500</td>
                        <td>412,890</td>
                        <td>487 ms</td>
                        <td>780 ms</td>
                        <td>1,120 ms</td>
                        <td>0.8%</td>
                        <td><span class="status pass">PASS</span></td>
                    </tr>
                    <tr>
                        <td>Estrés Máximo</td>
                        <td><span class="tag stress">Stress</span></td>
                        <td>1,000</td>
                        <td>234,120</td>
                        <td>1,245 ms</td>
                        <td>2,100 ms</td>
                        <td>3,450 ms</td>
                        <td>2.3%</td>
                        <td><span class="status fail">FAIL</span></td>
                    </tr>
                    <tr>
                        <td>Volumen de Datos</td>
                        <td><span class="tag volume">Volume</span></td>
                        <td>200</td>
                        <td>67,892</td>
                        <td>312 ms</td>
                        <td>490 ms</td>
                        <td>680 ms</td>
                        <td>0.2%</td>
                        <td><span class="status pass">PASS</span></td>
                    </tr>
                    <tr>
                        <td>Estabilidad (8h)</td>
                        <td><span class="tag stability">Stability</span></td>
                        <td>50</td>
                        <td>9,480</td>
                        <td>198 ms</td>
                        <td>290 ms</td>
                        <td>410 ms</td>
                        <td>0.05%</td>
                        <td><span class="status pass">PASS</span></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="section">
            <h2>Análisis de Recursos del Sistema</h2>
            
            <h3>Uso de CPU</h3>
            <div class="metric-bar">
                <div class="fill yellow" style="width: 78%;"></div>
            </div>
            <p>Promedio: 78% | Máximo: 92% | Umbral: 80%</p>
            
            <h3 style="margin-top: 20px;">Uso de Memoria</h3>
            <div class="metric-bar">
                <div class="fill green" style="width: 65%;"></div>
            </div>
            <p>Promedio: 65% | Máximo: 78% | Umbral: 85%</p>
            
            <h3 style="margin-top: 20px;">Uso de Base de Datos</h3>
            <div class="metric-bar">
                <div class="fill green" style="width: 52%;"></div>
            </div>
            <p>Promedio: 52% | Máximo: 71% | Umbral: 80%</p>
        </div>

        <div class="section">
            <h2>Detalle de Endpoints Probados</h2>
            
            <table>
                <thead>
                    <tr>
                        <th>Endpoint</th>
                        <th>Método</th>
                        <th>Calls</th>
                        <th>Avg Time</th>
                        <th>Min</th>
                        <th>Max</th>
                        <th>Err %</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>/api/accounts/{id}</td>
                        <td>GET</td>
                        <td>234,567</td>
                        <td>156 ms</td>
                        <td>45 ms</td>
                        <td>890 ms</td>
                        <td>0.02%</td>
                    </tr>
                    <tr>
                        <td>/api/transactions</td>
                        <td>POST</td>
                        <td>456,789</td>
                        <td>312 ms</td>
                        <td>89 ms</td>
                        <td>1,450 ms</td>
                        <td>0.15%</td>
                    </tr>
                    <tr>
                        <td>/api/transactions/search</td>
                        <td>POST</td>
                        <td>189,234</td>
                        <td>478 ms</td>
                        <td>120 ms</td>
                        <td>2,100 ms</td>
                        <td>0.32%</td>
                    </tr>
                    <tr>
                        <td>/api/accounts/balance</td>
                        <td>GET</td>
                        <td>167,890</td>
                        <td>234 ms</td>
                        <td>67 ms</td>
                        <td>980 ms</td>
                        <td>0.08%</td>
                    </tr>
                    <tr>
                        <td>/api/transfers</td>
                        <td>POST</td>
                        <td>98,765</td>
                        <td>567 ms</td>
                        <td>145 ms</td>
                        <td>2,890 ms</td>
                        <td>0.45%</td>
                    </tr>
                    <tr>
                        <td>/api/reports/generate</td>
                        <td>POST</td>
                        <td>45,678</td>
                        <td>1,890 ms</td>
                        <td>890 ms</td>
                        <td>8,900 ms</td>
                        <td>1.2%</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="section">
            <h2>Hallazgos y Recomendaciones</h2>
            
            <div class="test-scenario">
                <h4>⚠️ Hallazgo 1: Degradación bajo estrés máximo</h4>
                <p>El tiempo de respuesta promedio supera el umbral establecido (1,245ms vs 1,000ms) cuando el sistema opera con más de 800 usuarios concurrentes. La tasa de errores aumenta al 2.3%.</p>
                <div class="tags">
                    <span class="tag stress">Stress Test</span>
                    <span class="tag">Alta Prioridad</span>
                </div>
            </div>
            
            <div class="test-scenario">
                <h4>ℹ️ Hallazgo 2: Endpoint de reportes con alta latencia</h4>
                <p>El endpoint /api/reports/generate muestra latencias elevadas (promedio 1,890ms) que superan el umbral de 1,000ms. Se recomienda implementar caché o procesamiento asíncrono.</p>
                <div class="tags">
                    <span class="tag load">Load Test</span>
                    <span class="tag">Media Prioridad</span>
                </div>
            </div>
            
            <div class="test-scenario">
                <h4>✅ Hallazgo 3: Estabilidad operativa confirmada</h4>
                <p>La prueba de estabilidad de 8 horas demuestra que el sistema mantiene un rendimiento consistente sin degradación progresiva ni fugas de memoria.</p>
                <div class="tags">
                    <span class="tag stability">Stability Test</span>
                    <span class="tag">Éxito</span>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Acciones Recomendadas</h2>
            <ol style="margin-left: 20px; line-height: 2;">
                <li><strong>Optimizar servicio de transferencias:</strong> Implementar pooling de conexiones optimizado y revisar queries de base de datos.</li>
                <li><strong>Escalar horizontalmente:</strong> Agregar instancias del servicio de API bajo balanceador de carga para manejar picos.</li>
                <li><strong>Implementar caché:</strong> Agregar Redis para cachear respuestas de endpoints de consulta frecuente.</li>
                <li><strong>Procesamiento asíncrono:</strong> Migrar generación de reportes a cola de mensajes para procesamiento en background.</li>
                <li><strong>Revisión de timeouts:</strong> Ajustar timeouts de base de datos y servicios externos para mejor manejo de cargas.</li>
            </ol>
        </div>

        <footer>
            <p>Reporte generado automáticamente por JMeter + Maven Plugin</p>
            <p>Proyecto: performance-tests v1.0.0 | Fecha de generación: 2024-01-15 18:45:00</p>
        </footer>
    </div>
</body>
</html>
```
