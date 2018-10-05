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
* __A10: -Insufficient Logging & Monitoring__.

## Uso mediante Docker (regular)

### Requisitos

* Docker 1.3.2 o superior
* Docker Compose 1.6.2 o superior

### Modo de uso

Ya teniendo Docker instalado, primero se requiere bajar la imagen inicial.
Para ello ejecute:

    docker pull injcristianrojas/swsec-intro

Teniendo la imagen ya lista, active el contenedor:

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

Lo primero es bajar OWASP ZAP
(https://github.com/zaproxy/zaproxy/releases/tag/2.7.0), y luego crear un
archivo `env.properties` con el siguiente contenido:

```
zap.path=<directorio donde está instalado ZAP>
```

Luego deberá instalar la aplicación. Ésto creará la base de datos
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

## Pruebas externas y posterior limpieza

Apunte su herramienta de scanner preferida a <http://localhost:8080>.

Recuerde que al utilizar este tipo de herramientas sobre la aplicación,
éstas buscarán insertar registros en la base de datos utilizando SQL
Injection. Por lo tanto después de utilizar la aplicación y antes de
iniciar una nueva demo deberá recrear la base de datos. Para eso sólo
ejecute:

    mvn install

## Uso de Sonarqube

Levante la máquina Docker de Sonarqube:

    cd sonarqube
    docker-compose up

Cuando la máquina esté arriba y corriendo, en otro terminal, realice el
análisis y añada a la linea de `mvn`: `sonar:sonar`

## Deshabilitación de pruebas ZAP

A ratos las pruebas con OWASP ZAP son un tanto disruptivas para el trabajo, ya
que toman tiempo. Para deshabilitarlas, ir al `pom.xml` y en la sección
`<zap.skip>false</zap.skip>` setear su valor a `true`.
