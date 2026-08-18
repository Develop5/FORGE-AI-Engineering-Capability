

## Lista de comandos


./gradlew compileJava

---

./gradlew dependencies --configuration testRuntimeClasspath | grep -iE "junit|platform"

---
./gradlew clean test

---

./gradlew run

---
./gradlew installDist


printf "The system must provide appropriate authentication methods.\n\nUsers must authenticate with valid credentials.\n" | build/install/forge/bin/forge

---
./gradlew test

grep -R "<testsuite" build/test-results/test

---



En el estado actual de FORGE, puedes usar esta regla práctica:

| Comando                 | Cuándo usarlo                                                                               | Qué comprueba                                                |
| ----------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| `./gradlew compileJava` | Después de cambios **solo en código de producción**, para comprobar rápidamente que compila | Compilación de `src/main`                                    |
| `./gradlew test`        | Después de **cualquier cambio de código**                                                   | Compila tests + ejecuta toda la suite                        |
| `./gradlew installDist` | Cuando quieras comprobar que el **artefacto ejecutable** se genera correctamente            | Construye la distribución instalada en `build/install/forge` |
| `./gradlew run`         | Cuando quieras **probar manualmente la aplicación** desde Gradle                            | Arranca la CLI                                               |
| `./gradlew clean test`  | Cuando quieras una **validación limpia desde cero**                                         | Borra `build/`, recompila todo y ejecuta todos los tests     |

### Para tu flujo habitual de Engineering

Después de que yo te dé cambios:

```bash
./gradlew test
```

Es el comando principal. Si pasa, ya sabes que el código y los tests están funcionando.

Después, cuando estemos validando la CLI:

```bash
./gradlew installDist
```

y después puedes ejecutar:

```bash
build/install/forge/bin/forge
```

o probarla con `printf`, como has estado haciendo.

### ¿Cuándo `compileJava`?

Es útil como comprobación rápida mientras estás haciendo cambios, pero **no sustituye a `test`**.

Por ejemplo:

```bash
./gradlew compileJava
```

te dice rápidamente si el código de producción compila, pero no comprueba los tests.

### ¿Cuándo `clean test`?

Principalmente cuando quieras asegurarte de que **no estás dependiendo de artefactos generados anteriormente**:

```bash
./gradlew clean test
```

Es una validación más fuerte que `test`, pero normalmente no necesitas ejecutarla después de cada cambio.

### Regla sencilla

Para nuestro trabajo en FORGE:

```text
Cambio de código
      ↓
./gradlew test
      ↓
¿CLI afectada?
      ↓ sí
./gradlew installDist
      ↓
prueba build/install/forge/bin/forge
```

Y periódicamente, o cuando haya dudas sobre el estado de la compilación:

```text
./gradlew clean test
```

`./gradlew run` lo reservaría para **probar el comportamiento interactivo de la CLI**, no como validación principal de cada cambio.


---------------
