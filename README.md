# Fundamentos de pruebas de performance

En un proyecto de desarrollo de software para una aplicación bancaria, es crucial evaluar el rendimiento del sistema bajo diferentes cargas y condiciones. Las pruebas no funcionales, como las pruebas de carga, rendimiento, volumen, estrés, estabilidad, línea base, tolerancia a fallas y robustez, son esenciales para garantizar que el sistema pueda manejar el tráfico esperado y mantener un rendimiento óptimo. En este desafío, explorarás la importancia de estas pruebas y cómo pueden impactar en la calidad del software.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Importancia de las pruebas de performance |
| **Nivel** | advanced-l2 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 2 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Introducción a las pruebas no funcionales

**Objetivo:** Comprender la importancia de las pruebas no funcionales en un proyecto de software.

**Tiempo estimado:** 30 minutos

**Instrucciones:**

- Investiga y describe las diferentes tipos de pruebas no funcionales (carga, rendimiento, volumen, estrés, estabilidad, línea base, tolerancia a fallas y robustez).
- Explica por qué cada tipo de prueba es importante para la calidad del software en un proyecto bancario.

**Entregable:** Un documento que describe las diferentes tipos de pruebas no funcionales y su importancia en un proyecto bancario.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo cada tipo de prueba puede afectar el rendimiento y la fiabilidad del sistema.
- Piensa en los posibles impactos de no realizar estas pruebas en un entorno de producción.

</details>

### Fase 2: Evaluación de casos de uso

**Objetivo:** Identificar y evaluar casos de uso específicos donde las pruebas no funcionales son críticas.

**Tiempo estimado:** 45 minutos

**Instrucciones:**

- Selecciona dos casos de uso de una aplicación bancaria donde las pruebas no funcionales sean críticas.
- Describe cómo aplicarías cada tipo de prueba no funcional a estos casos de uso y qué resultados esperas obtener.

**Entregable:** Un informe que detalla los casos de uso seleccionados y cómo se aplicarían las pruebas no funcionales a cada uno.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo las pruebas de carga y rendimiento pueden ayudar a identificar cuellos de botella en el sistema.
- Piensa en cómo las pruebas de estabilidad y tolerancia a fallas pueden garantizar que el sistema se recupere de situaciones inesperadas.

</details>

### Fase 3: Recomendaciones y mejores prácticas

**Objetivo:** Proponer recomendaciones y mejores prácticas para la implementación de pruebas no funcionales en proyectos de software.

**Tiempo estimado:** 45 minutos

**Instrucciones:**

- Basándote en tu investigación y evaluación de casos de uso, propone al menos tres recomendaciones y mejores prácticas para la implementación de pruebas no funcionales en proyectos de software.
- Justifica cada recomendación con ejemplos concretos y explica cómo pueden contribuir a mejorar la calidad del software.

**Entregable:** Un documento que presenta tus recomendaciones y mejores prácticas para la implementación de pruebas no funcionales en proyectos de software.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo la automatización de las pruebas puede mejorar la eficiencia y la cobertura.
- Piensa en la importancia de establecer líneas base y umbrales de rendimiento para evaluar el éxito de las pruebas.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas no funcionales y por qué son importantes en un proyecto de software?
- **paraQueSirve**: ¿Cómo pueden las pruebas no funcionales mejorar la calidad del software en un proyecto bancario?
- **comoSeUsa**: ¿Cómo se aplican las diferentes tipos de pruebas no funcionales a casos de uso específicos en una aplicación bancaria?
- **erroresComunes**: ¿Cuáles son los errores comunes al realizar pruebas no funcionales y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de pruebas no funcionales en un proyecto de software y cómo se pueden justificar?

## Criterios de Evaluacion

- Identificación y descripción de los diferentes tipos de pruebas no funcionales.
- Evaluación de casos de uso específicos donde las pruebas no funcionales son críticas.
- Propuesta de recomendaciones y mejores prácticas para la implementación de pruebas no funcionales en proyectos de software.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
