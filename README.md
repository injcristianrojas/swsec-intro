# Software Security Demo App (CI/CD demo)

## Integration testing using Jenkins

This project includes a Docker environment for security testing demo in a CI/CD environment.
This will require [Docker](https://docs.docker.com/v17.12/install/) installed. To set up,
follow these steps:

1. Create a docker image: `docker build -t jenkins-zaproxy .`
1. Launch a new container from this image: `docker run -p 8081:8080 jenkins-zaproxy`
1. Go to http://127.0.0.1:8081 and follow Jenkins instructions to install.
1. Select "Open Blue Ocean". Then create a new pipeline (select "Create a new
Pipeline"):
   * In "Where do you store your code?" select Git.
   * In "Connect to a Git repository" enter this repo: 
   https://github.com/injcristianrojas/swsec-intro.git
   * Ignore the credentials part and select "Create Pipeline..."
1. The build has started. Select it and watch the magic happen.
