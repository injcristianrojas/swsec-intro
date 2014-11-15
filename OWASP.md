# Chequeo de dependencias mediante OWASP

Un tema importante en seguridad de software es el hecho de que los
desarrolladores deben soportar _todo el software_, eso significa que, además
de asegurar el código que ellos desarrollan, deben soportar las bibliotecas y
frameworks sobre los cuales el código funciona.

En este branch, se incluye la biblioteca [OWASP Dependency Check](http://jeremylong.github.io/DependencyCheck/index.html),
la cual revisa las bibliotecas de las cuales depende la aplicación y permite
obtener el link al CVE donde se detallan las vulnerabilidades que pudieran
tener.

