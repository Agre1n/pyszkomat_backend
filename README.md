# Pyszkomat Application REST API Backend

SwaggerUI: http://localhost:8080/api-docs/swagger-ui/index.html

## How to build and push Docker image

`docker build -t michal1112/pyszkomat_backend .`

`docker push michal1112/pyszkomat_backend`

## How to run Docker container

`docker-compose up`

## How to run on AWS EC2 instance (for future deployment)

### Connect to EC2 instance

`ssh -i "pyszkomat-rsa-key.pem" ec2-user@{ instance-public-ip }`

### Install Docker

`sudo yum update -y`

`sudo yum install docker -y`

`sudo systemctl start docker`

`sudo systemctl enable docker`

`sudo usermod -a -G docker $(whoami)`

`newgrp docker`

`docker --version`

### Install Docker Compose

`sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose`

`sudo chmod +x /usr/local/bin/docker-compose`

`docker-compose --version`

### Create and run Application

`nano docker-compose.yaml`

`docker-compose up`