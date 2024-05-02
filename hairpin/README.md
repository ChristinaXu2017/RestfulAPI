## launch backend

### install database on docker
  - launch docker deskop after installation. eg. download the one for mac with apple chip
  - create myql container based on mysql:8-oracle image
  ```
  # you can set one or multi user account to environment variable
  # AWS RDS not allow username “server_user” and pw length < 8; so add another account into env while creating docker
  docker run --detach --env MYSQL_ROOT_PASSWORD=admin  --env MYSQL_USER=server_user --env MYSQL_PASSWORD=1234567 --env MYSQL_USER=serverUser --env MYSQL_PASSWORD=12345678 --env MYSQL_DATABASE=benth_2023 --name mysql_benth --publish 3306:3306 mysql:8-oracle 

  ```
  - `docker exec -it mysql_benth mysql -u root -p `, access database container interactively, here password is "admin"
  - `mysql> use benth_2023;`
  - `mysql> CREATE TABLE lab360 (... ` # create table on the pop up-ed mysql client <Details>
      CREATE TABLE lab360 (
       id INT AUTO_INCREMENT PRIMARY KEY,
        miR_ID VARCHAR(2550),
        miR VARCHAR(255),
        number VARCHAR(255),
        pure_number INT,
        new_pure_number INT,
        miRNA_order INT,
        sequence VARCHAR(255),
        chr VARCHAR(255),
        strand ENUM('+', '-'),
        start INT,
        precursor_seq VARCHAR(255),
        Total_mature_reads_include_extra INT,
        Total_star_reads INT,
        Total_LAB_mature_reads INT,
        Total_LAB_star_reads INT,
        Total_QLD_mature_reads INT,
        Total_QLD_star_reads INT,
        LAB_FLOWER_mature INT,
        LAB_FLOWER_star INT,
        QLD_FLOWER_mature INT,
        QLD_FLOWER_star INT,
        LAB_ROOT_mature INT,
        LAB_ROOT_star INT,
        LAB_SEED_mature INT,
        LAB_SEED_star INT,
        QLD_SEED_mature INT,
        QLD_SEED_star INT,
        read_same_direction INT,
        read_both_direction INT,
        genomic_location INT,
        pri_left_range VARCHAR(255),
        pri_right_range VARCHAR(255),
        structure TEXT
      );
  </Details> 
  
  - load data into table
    - `docker cp /Users/christix/Desktop/LAB.csv mysql_benth:/var/lib/mysql-files/`   <Details>
      ```
        # you have to put file under mysql configured location, which is below
        mysql> SHOW VARIABLES LIKE 'secure_file_priv';
        +------------------+-----------------------+
        | Variable_name    | Value                 |
        +------------------+-----------------------+
        | secure_file_priv | /var/lib/mysql-files/ |
        +------------------+-----------------------+
        1 row in set (0.03 sec)
        
      ```
      </Details>
    - `chmod a+r /var/lib/mysql-files/LAB.csv` # grant permission after inspect to the container through docker desktop
    - load file to database, here “LOAD DATA INFILE” but not “LOAD DATA LOCAL INFILE” means your file is already on server side
      ```
      mysql> LOAD DATA INFILE '/var/lib/mysql-files/LAB.csv' INTO TABLE lab360 FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;
      ```
 - command for sql query <Details>
 
   ```
    mysql -u root -p
    Enter password: admin
    mysql> use benth_2023;
    mysql> show tables
    mysql> SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'lab360';
    mysql> select * from lab360 where pure_number = 156;  // return 94 records
    mysql> select distinct pure_number from lab360;   // 156, 157 are good for testing
  
   ```
 
 
 
 </Details>
    
### launch spring app on Eclipse 
create jar   or keep develpping from Eclipse, eg. Version: 2024-03 (4.31.0)
  - Spring Boot 3+ works only with Java 17+
  - git clone https://github.com/ChristinaXu2017/RestfulAPI.git
  - Eclipse > File > import > Maven > Existing Maven projects
  - select HairpinApplication.java > run as java application
  - http://localhost:5050/hello-world
  - http://localhost:5050/hello-world/name
  - http://localhost:5050/Favorites/5
  - http://localhost:5050/miRNA/156
  ```
    # default setting is prot 5050? you can stop if and reuse 
    pid=`lsof -i :5050 | tail -1 | awk ‘{print $2}’` && kill -9 $pid 
  ```

### launch react app on VS code
- cd /Users/xu102/Documents/gitHub/RestfulAPI/hairpin/hairpin-front
- npm install react-scripts --save # first time if you react is not yet installed
- PORT=3030 npm start &
- http://localhost:3030/mirna





