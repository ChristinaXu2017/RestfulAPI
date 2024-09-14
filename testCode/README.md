## Backend
- spring boot 3.3.3 with java 17 is used in this application
- H2 test DB is used. database schema refer to testCode/maintenance/src/main/resources/schema.sql
- Server setting during development refer to testCode/maintenance/src/main/resources/application.properties 

### Deployment
- run as Java application on Eclipse, local access:
   -  eg. access one of endpoints http://localhost:6001/maintenance/test/1
   -  eg. document: http://localhost:6001/swagger-ui/index.html
- or create an image, run inside the docker container
   - eg. access the endpoint http://localhost:6000/maintenance/test/1
   - eg. document: http://localhost:6000/swagger-ui/index.html
 ```
  $ mvn clean spring-boot:build-image
  $ docker images 
  REPOSITORY                            TAG              IMAGE ID       CREATED         SIZE
  maintenance                           0.0.1-SNAPSHOT   8ce5e347672e   44 years ago    362MB
  $ docker run -d -p 6000:6001 --platform linux/amd64 --network my-network --name backend 8c
  47732f41d44bf418b10e09e58f016485dfeaa545e35cb18a5b456f58bbc7035e
  $ docker ps
  CONTAINER ID   IMAGE            COMMAND                  CREATED          STATUS          PORTS                               NAMES
  417f9adbcd07   8c               "/cnb/process/web"       24 seconds ago   Up 23 seconds   0.0.0.0:6000->6001/tcp              backend
 ```
 

