# Aplicación de demostración de Seguridad de Software

La presente es una aplicación simple Java (JEE) que sirve para
realizar una demostración de un programa básico de seguridad de software.

Es una aplicación débil a propósito, a la cual se le dejaron intencionalmente
las siguientes vulnerabilidades:

* SQL Injection
* Cross-Site Scripting (XSS)

## Requisitos

* Docker 1.3.2 o superior

## Modo de uso

Ya teniendo Docker instalado, primero se requiere bajar la imagen inicial.
Para ello ejecute:

    docker pull injcristianrojas/swsec-intro

Teniendo la imagen ya lista, cree el contenedor y ejecútelo:

    docker run --rm --name swsec-intro -p 8080:8080 -v ${PWD}:/usr/src/app:rw injcristianrojas/swsec-intro

El contenedor con el servidor se activará automáticamente y dejará la
aplicación corriendo en <http://localhost:8080>. Para acceder al servidor,
escriba:

    docker exec -it swsec-intro /bin/bash

## Funcionamiento directo con Maven/Gradle

La aplicación es prácticamente autocontenida. Para utilizarla se requiere
el siguiente software:

* Java (1.7 o superior)
* Maven (2 o superior) o Gradle.

### Modo de uso

Todos los comandos descritos a continuación, requieren que Usted se sitúe
en el directorio donde se encuentra el archivo `pom.xml`.

### Instalación

Antes de usar la aplicación, deberá instalarla. Ésto creará la base de datos
sqlite para la aplicación web y realizará pruebas de integración para asegurar
que ésta esté en orden. Eso se logra ejecutando:

    mvn install

En Gradle se puede hacer escribiendo:

    gradle install

Después de unos pocos segundos, Usted debería ver el siguiente mensaje:

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------

en ese caso, la base de datos estará creada, el servidor estará probado y podrá
activarlo.

### Activación del servidor

Para echar a correr el servidor, escriba:

    mvn jetty:run

En Gradle escriba:

    gradle jettyRun

El servidor se activará automáticamente y dejará la aplicación corriendo en
<http://localhost:8080>.

## Pruebas externas (fuzzing) y posterior limpieza

Apunte su herramienta de fuzzing preferida a <http://localhost:8080>.

Recuerde que al utilizar este tipo de herramientas sobre la aplicación,
éstas buscarán insertar registros en la base de datos utilizando SQL
Injection. Por lo tanto después de utilizar la aplicación y antes de
iniciar una nueva demo deberá recrear la base de datos. Para eso sólo
ejecute:

    mvn install

En Gradle use:

    gradle install
