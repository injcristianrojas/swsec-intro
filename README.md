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

Todos los comandos descritos a continuación, requieren que Usted se sitúe
en el directorio donde se encuentra el archivo `pom.xml`.

### Creación de la base de datos ###

Antes de usar la aplicación, deberá ejecutar una acción la cual creará una
base de datos sqlite para la aplicación web y probará que ésta esté en orden.
Eso se logra ejecutando:

    mvn compile exec:java integration-test

Después de unos pocos segundos, Usted debería ver el siguiente mensaje:

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------

en ese caso, la base de datos estará creada, el servidor estará probado y podrá
activarlo.

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
iniciar una nueva demo deberá recrear la base de datos. Para eso sólo
ejecute:

    mvn compile exec:java

## Licencia ##

El código de esta aplicación es distribuido bajo la [licencia MIT](http://opensource.org/licenses/MIT):

	Copyright (c) 2012 Cristián Rojas

	Permission is hereby granted, free of charge, to any person obtaining
	a copy of this software and associated documentation files (the "Software"),
	to deal in the Software without restriction, including without limitation
	the rights to use, copy, modify, merge, publish, distribute, sublicense,
	and/or sell copies of the Software, and to permit persons to whom the
	Software is furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included
	in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
	THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
