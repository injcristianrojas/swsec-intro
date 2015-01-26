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

Ya teniendo Docker instalado, primero se requiere construir la imagen inicial.
Para ello ejecute:

    docker build -t swsec-intro .

Teniendo la imagen ya lista, cree el contenedor y ejecútelo:

    docker run --rm -p 8080:8080 -v "$(pwd)":/app swsec-intro install jetty:run

Las veces subsiguientes en las que lo ejecute sólo deberá emitir el
comando:

    docker run --rm -p 8080:8080 -v "$(pwd)":/app swsec-intro jetty:run

El contenedor con el servidor se activará automáticamente y dejará la
aplicación corriendo en <http://localhost:8080>.
