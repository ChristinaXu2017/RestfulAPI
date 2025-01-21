## launch three tier web application (hairpin)

### install database on docker or RDS
  - launch docker deskop after installation. eg. download the one for mac with apple chip 
  - cd ./hairpin-back/src/main/resources && bash init.sh
     - it create mysql container based on mysql:8-oracle image
     - create database, load csv file to table
  
### launch spring app on Eclipse 
create jar   or keep develpping from Eclipse, eg. Version: 2024-03 (4.31.0)
  - Spring Boot 3+ works only with Java 17+
  - git clone https://github.com/ChristinaXu2017/RestfulAPI.git
  - Eclipse > File > import > Maven > Existing Maven projects
  - select HairpinApplication.java > run as java application
    - http://localhost:5050/miRNA/156 # show one object HairPinRestController.java
    - http://localhost:5050/miRNA/mir156 # show a list of object HairPinRestController.java
  - or run inside container java > run as maven build, set Goals: spring-boot:buid-image; then launch container
    - docker run -d -p 6000:5050 --platform linux/amd64 --name hairD ec or
       ```
        docker network create my-network
        docker network connect my-network mysql-container
        docker run -d \
            -p 5050:5050 \
            --platform linux/amd64 \
            --name hairC \
            --network my-network \
            -e RDS_HOSTNAME=mysql-container \
            -e RDS_PORT=3306 \
            -e RDS_DB_NAME=benth_2023 \
            -e RDS_USERNAME=serverUser \
            -e RDS_PASSWORD=12345678 \
            <image>
       
       ```
??? spring.main.headless=true
The error message you're seeing indicates that there is a NullPointerException related to font configuration in the Java AWT (Abstract Window Toolkit) subsystem. This is often related to running Java applications in headless environments, such as Docker containers, where there is no graphical user interface.

#### test REST API
- download postman and send request: eg. http://localhost:8080/api/input/upload
- using ` crul: eg curl -X POST "http://localhost:8080/api/input/upload" -F "uploadFile=/path/to/your/file.zip" -F "s3Key=your/s3/key" `

### launch react app on VS code
- cd /.../RestfulAPI/hairpin/hairpin-front
- npm install react-scripts --save # first time if you react is not yet installed
- PORT=3030 npm start &
- http://localhost:3030/mirna





