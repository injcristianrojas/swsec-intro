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

    docker run --rm --name swsec-intro --privileged=true -p 8080:8080 -v ${PWD}:/usr/src/app injcristianrojas/swsec-intro

El contenedor con el servidor se activará automáticamente y dejará la
aplicación corriendo en <http://localhost:8080>. Para acceder al servidor,
escriba:

    docker exec -it swsec-intro /bin/bash
