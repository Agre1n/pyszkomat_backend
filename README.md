# pyszkomat_backend

SwaggerUI: http://localhost:8080/api-docs/swagger-ui/index.html

## How to build and push Docker image

open CMD in `./app`

`docker build --platform=linux/amd64 -t michal1112/pyszkomat_backend .`

`docker push michal1112/pyszkomat_backend`

## How to run locally

open CMD in `./app`

`docker-compose up`

## How to run on AWS EC2 instance (for future deployment)

open CMD in `./aws`

`ssh -i "pyszkomat_app_ssh_key.pem" ec2-user@ec2-35-171-150-192.compute-1.amazonaws.com`

### Install Docker

`sudo yum install -y yum-utils device-mapper-persistent-data lvm2`

`sudo yum install docker`

`sudo service docker start`

`sudo usermod -aG docker $USER`

`docker --version`

### Install Docker Compose

`sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`

`sudo chmod +x /usr/local/bin/docker-compose`

`docker-compose --version`

### Create and run compose.yml file

reconnect to the instance

`nano compose.yml`

`docker-compose up`