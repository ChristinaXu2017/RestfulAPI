docker run -d \
    -e MYSQL_ROOT_PASSWORD=admin \
    -e MYSQL_USER=serverUser \
    -e MYSQL_PASSWORD=12345678 \
    -e MYSQL_DATABASE=benth_2023 \
    -p 3306:3306 \
    --name mysql-container \
    mysql:8-oracle

# copy local files to container
docker cp $(pwd)/init.sql mysql-container:/var/lib/mysql-files/init.sql
docker cp $(pwd)/LAB.csv  mysql-container:/var/lib/mysql-files/LAB.csv

# run sql to load csv to table
docker exec -i mysql-container  bash -c "mysql -uroot -padmin benth_2023 < /var/lib/mysql-files/init.sql"

# then test the database inside the container
# mysql -h 127.0.0.1 -P 3306 -u serverUser -p12345678 benth_2023
# show tables;
# select * from lab360 limits 2;



# docker ps -a
# docker rm <container id>
# docker logs <container id>
# debug volumn mount
# docker run --rm -it -v $(pwd):/var/lib/mysql-files mysql:8-oracle ls -l /var/lib/mysql-files
# docker exec -i mysql-container07 bash -c "mysql -uroot -padmin benth_2023 < /var/lib/mysql-files/init.sql"

## run sql inside the container
# mysql -h 127.0.0.1 -P 3306 -u serverUser -p12345678 benth_2023
# mysql> show tables
# docker run -d \
# -e MYSQL_ROOT_PASSWORD=admin \
# -e MYSQL_USER=serverUser \
# -e MYSQL_PASSWORD=12345678 \
# -e MYSQL_DATABASE=benth_2023 \
# -p 3307:3307 \
# -v $(pwd):/var/lib/mysql-files \
# --name mysql-container07 \
# mysql:8-oracle \
# --init-file=/var/lib/mysql-files/init.sql
