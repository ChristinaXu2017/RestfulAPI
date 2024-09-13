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

### launch react app on VS code
- cd /.../RestfulAPI/hairpin/hairpin-front
- npm install react-scripts --save # first time if you react is not yet installed
- PORT=3030 npm start &
- http://localhost:3030/mirna





