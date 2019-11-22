# Aplicación de demostración de Seguridad de Software

La presente es una aplicación simple Java (JEE) que sirve para
realizar una demostración de un programa básico de seguridad de software.

Es una aplicación débil a propósito, la cual contiene las siguientes
vulnerabilidades incluidas en el
[OWASP Top 10 - 2017](https://www.owasp.org/index.php/Category:OWASP_Top_Ten_2017_Project):

* __A1: Injection__: SQL Injection en este caso.
* __A2: Broken Authentication and Session Management__: Falta de redundancia en
la función de cambio de password, passwords mal almacenadas.
* __A3: Sensitive Data Exposure__: Passwords mal almacenadas, no uso de HTTPS.
* __A5: Broken Access Control__: Acceso por URL directa a archivos subidos.
* __A6: Security Misconfiguration__: Exceso de información en errores (ej. 500).
* __A7: Cross-Site Scripting (XSS)__.
* __A9: Using Components with Known Vulnerabilities__: Uso de bibliotecas Java
vulnerables (existen registros [CVE](https://cve.mitre.org/) asociados a
tales versiones).
* __A10: Insufficient Logging & Monitoring__.

## Uso mediante Docker (regular)

### Requisitos

* Docker 1.3.2 o superior
* Docker Compose 1.6.2 o superior

### Modo de uso

Ya teniendo Docker Compose instalado, debe levantar el servidor. Para ello
ejecute:

    docker-compose up

El contenedor con el servidor se activará automáticamente y dejará la
aplicación corriendo en <http://localhost:8080>, y tendrá como nombre algo como
`swsecintro_victim_1`. Para acceder al shell del servidor, escriba en otro
terminal:

    docker exec -it swsecintro_victim_1 /bin/bash

Para detener el contendor, sólo presione <kbd>Ctrl</kbd>+<kbd>C</kbd>.

## Funcionamiento directo con Maven

La aplicación es prácticamente autocontenida. Para utilizarla se requiere
el siguiente software:

* Java (1.7 o superior)
* Maven (2 o superior)

### Modo de uso

Todos los comandos descritos a continuación, requieren que Usted se sitúe
en el directorio donde se encuentra el archivo `pom.xml`.

### Instalación

Deberá instalar la aplicación. Ésto creará la base de datos
sqlite para la aplicación web y realizará pruebas de integración para asegurar
que ésta esté en orden. Eso se logra ejecutando:

    mvn install

Después de unos pocos segundos, Usted debería ver el siguiente mensaje:

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------

en ese caso, la base de datos estará creada, el servidor estará probado y podrá
activarlo.

### Activación del servidor

Para echar a correr el servidor, escriba:

    mvn jetty:run

El servidor se activará automáticamente y dejará la aplicación corriendo en
<http://localhost:8080>.

### ESPECIAL: Sonarqube

Para esto se requiere Docker y Docker Compose.

En un terminal, lanzar SonarQube:

```shell
cd sonarqube
docker-compose up
```

En otro terminal, envíe el análisis a SonarQube:

```shell
mvn compile findbugs:findbugs org.owasp:dependency-check-maven:check org.sonarsource.scanner.maven:sonar-maven-plugin:sonar
```
