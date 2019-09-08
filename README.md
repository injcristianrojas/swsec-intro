# Software Security Demo App (CI/CD demo)

## Integration testing using Jenkins

This project includes a Docker environment for security testing demo in a CI/CD environment.
This will require [Docker](https://docs.docker.com/v17.12/install/) installed. To set up,
follow these steps:

1. Create a docker image: `docker build -t jenkins-zaproxy .`
1. Launch a new container from this image: `docker run -p 8081:8080 jenkins-zaproxy`
1. Go to http://127.0.0.1:8081 and follow Jenkins instructions to install.
1. Go to "Manage Jenkins", then "Global Tool Configuration", then "Add Maven". Then fill
the "Name" field with `M3` and click Save.
1. Go to "Manage Jenkins" again, then "Manage Plugins", and install the "ZAP Pipeline",
"OWASP Dependency-Check Plugin" and "Warnings Next Generation Plugin" plugins, then restart Jenkins.
1. Create a new Pipeline job. In the Pipeline section, select "Pipeline script from SCM" as
the Definition. Select Git as your SCM. In the Repository URL, enter this repo
(https://github.com/injcristianrojas/swsec-intro.git) and in
"Branches to build" enter `*/jenkins`. Click save.
1. Click on "Build now", and watch the magic happen.