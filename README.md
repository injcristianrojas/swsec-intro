# Aplicación de demostración de Seguridad de Software #

La presente es una aplicación simple Java (JEE) que sirve para
realizar una demostración de un programa básico de seguridad de software.

Es una aplicación débil a propósito, a la cual se le dejaron intencionalmente
las siguientes vulnerabilidades:

* SQL Injection
* Cross-Site Scripting (XSS)

## Requisitos del sistema ##

La aplicación es prácticamente autocontenida. Para utilizarla se requiere
el siguiente software:

* Java (1.5 o superior)
* Maven (2 o superior)

## Modo de uso ##

Antes del uso, edite el archivo `app.properties` de manera tal que la
propiedad `JDBC.connectionURL` apunte a la ubicación correcta de la base
de datos `db/clcert.sqlite`.

Todos los comandos descritos a continuación, requieren que Usted se sitúe
en el directorio donde se encuentra el archivo `pom.xml`.

### Activación del servidor ###

Para echar a correr el servidor, escriba:

    mvn jetty:run

El servidor se activará automáticamente y dejará la aplicación corriendo en
<http://localhost:8080>

### Pruebas internas ###

Usted puede utilizar el analizador estático de su preferencia, sin embargo
la configuración Maven ya trae uno llamado
[Findbugs](http://http://findbugs.sourceforge.net/), desarrollado por la
Universidad de Maryland. Usted podrá ejecutarlo desde Maven escribiendo

    mvn findbugs:findbugs findbugs:gui

Luego de lo cual aparecerá la interfaz de usuario de Findbugs.

### Pruebas externas (fuzzing) y posterior limpieza ###

Apunte su herramienta de fuzzing preferida a <http://localhost:8080>.

Recuerde que al utilizar este tipo de herramientas sobre la aplicación,
éstas buscarán insertar registros en la base de datos utilizando SQL
Injection. Por lo tanto después de utilizar la aplicación y antes de
iniciar una nueva demo deberá limpiar la base de datos. De eso se encarga
el archivo `clcert/DBCleanup.java`, el cual se ejecutará automáticamente
con sólo ejecutar:

    mvn exec:java
