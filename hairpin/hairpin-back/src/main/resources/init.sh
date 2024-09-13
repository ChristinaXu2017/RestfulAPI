docker run -d \
    -e MYSQL_ROOT_PASSWORD=12345678 \
    -e MYSQL_USER=serverUser \
    -e MYSQL_PASSWORD=12345678 \
    -e MYSQL_DATABASE=benth_2023 \
    -p 3306:3306 \
    -v $(pwd):/var/lib/mysql-files \
    --name mysql-container \
    mysql:8-oracle \
    --init-file=/var/lib/mysql-files/init.sql

# docker ps -a
# docker rm <container id>
# docker logs <container id>
# debug volumn mount
# docker run --rm -it -v ${pwd}:/var/lib/mysql-files mysql:9-oracle ls -l /var/lib/mysql-files
