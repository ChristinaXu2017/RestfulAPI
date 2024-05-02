## why spring boot
[microservices architecture ](https://www.bing.com/images/search?q=microservices+architecture&form=HDRSC3&first=1) <br>
[spring boot web application includes:](https://medium.com/featurepreneur/spring-boot-for-beginner-62dd9785bb44)<br>
refer to [spring boot feature](https://spring.io/projects/spring-boot)
- It's built on top of the Spring Framework and simplifies its setup and usage.
- It provides a simpler approach to start Spring projects by auto-configuring the setup based on the included dependencies. 
- It comes with embedded servers like Tomcat, Jetty, or Undertow, so you don't need to deploy your applications onto separate servers.
- It provides production-ready features like health checks, metrics, etc., through Spring Boot Actuator.
  
### before spring boot
-  dependency management on pom.xml, see spring.dependency.png
-  define web app configure on web.xml, see spring.web.xml.configure
-  manage spring bean on context.xml, see spirng.configure.png
-  manager non functional features, see spring.nfrs

### start up spring boot project
https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/02-springboot
#### `demo:` 
- go to [spring initializr](https://start.spring.io/)
  - java; maven; latest spring boot version but not snapshot; jar; java17
  - spring boot provides variety of starter dependencies:
     - `spring web` for restAPI, tomcat: spring-boot-starter-web and spring-boot-starter-test
     - `spring boot actuator`: see below
     - `spring boot DevTools`: startup server automatically during developping
     - `spring data JDBC`: no sql query with limited code. 
     - `spring data JPA`: java persistence API: eg. hairpin/hairpin-back/src/main/java/org/qcmg/hairpin/demo/Lab360Repository.java . JPA simplify code and almost replace JDBC. However JDBC still needed to execute complex SQL. 
     -  `H2 database`: H2 database is more for developping and debug
     - `spring security` dependency
    
- auto configuration on pom.xml 
  - maven dependencies: there are many autoconfiguration classes
  - org.springframework.boot.autoconfigure.web package is related for restapi
  - Tomcat is configured for you (EmbededWebServerFactoryCustomizerAutoConfiguration).
  - let's see dispatcherServletAutoConfigureation source code, open type (command/Ctrl+shift+T); you can see dispatchservlet is configured, means web application of rest API is enabled.
  -  you can see Error page is auto-confiugured for you: it will pop up "Whitelabel Error page... status=404" when url is not exists.
  - class annoated with @@ConfigurationProperties(...) can retrive settings from application.properties (Udemy: 66: step 10 - ConfigureationProperties)
  - Spirng boot DevTools: Udemy: 64: step 08 - build Faster with spring Boot DevTools ( if have time demo this part) : it can automatically restart server during developping (code updated).
     
- launch it from Eclipse
    - unzip the downloaded folder to eclipse: File::import:: existing maven project
    - @springbootapplication class with main method
    - add "server.port=8080" to application.propertie;
      - http://localhost:8080   # shows a default login form (username: user; password: copy from console)
      - http://localhost:8080/actuator # : see java, dependecy, bean, jvm etc. eg. http.server.requests: request number, time consuming etc. 
   	 - <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-actuator</artifactId></dependency>  
   	 - management.endpoints.web.exposure.include=health,metrics  or management.endpoints.web.exposure.include=*
   	 - 
    - select HairpinApplication.java > run as java application   	  
	  - http://localhost:5050/hello-world     # show a string DemoRestController.java
	  - http://localhost:5050/hello-world/name # show a string with parameter variale  DemoRestController.java
	  - http://localhost:5050/miRNA/156 # show one object HairPinRestController.java
	  - http://localhost:5050/miRNA/mir156 # show a list of object HairPinRestController.java
	  - http://localhost:5050/cycle     # MVC to display cycle2.jsp in old day (HelloMVC.java)
  ```
    # default setting is prot 5050? you can stop if and reuse 
    pid=`lsof -i :5050 | tail -1 | awk ‘{print $2}’` && kill -9 $pid 
  ```
#### show code
- build a simple rest API using spring boot
  - see [helloWorld inside hairpin](https://github.com/ChristinaXu2017/RestfulAPI/blob/main/hairpin/hairpin-back/src/main/java/org/qcmg/hairpin/helloworld/HelloController.java)
    - @RestController: means every method annotated with @RequestMapping etc in this class return types are directly written to HTTP response
    - @Controller: means method returns view (HTML, JSP ). eg return "cycle2" means return cycle2.jsp file;
        - unless annotated both @GetMapping("/rawdata1") and @ResponseBody to the method, it will return tyeps to HTTP responses as method inside @RestController class
    - @RequestMapping handle all types of HTTP requests (GET, POST, PUT, DELETE, etc.) when you specify the method type.
    - @GetMapping is a shortcut for @RequestMapping(method = RequestMethod.GET), only handle GET type HTTP requests.

- `demo maybe` application.properties
  - logging.level.org.springframework=debug refer to [application.properties](02-springboot/src/main/resources/application.properties).  now you run app, you can see log from app run std report "Conditions Evaluation Report": 
  - Spring boot Actuator
    - <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-actuator</artifactId></dependency>  
    - management.endpoints.web.exposure.include=health,metrics  or management.endpoints.web.exposure.include=*
    - localhost:8000/actuator/   : see java, dependecy, bean, jvm etc. eg. http.server.requests: request number, time consuming etc. 

## hairpin project 
- JPA: hairpin/hairpin-back/src/main/java/org/qcmg/hairpin/demo/Lab360Repository.java and LAB360.java.
  - it help to write custome query, such as. repository.count(); findAll() follow name conversiton (Udemy 83)
- demo hairpin/hairpin-back/src/main/java/org/qcmg/hairpin/helloworld/HelloController.java
    - add DeleteMapping, putMapping, PostMaaping, like todo project todo/todo-restAPI-spring/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/TodoJpaResource.java 
- enable Cross origin request
- axoi in react to access api
- setup Auth Context
- connection to mySQL
  
### spring security:
When you include the Spring Security starter dependency in your Spring Boot application, Spring Boot automatically configures a default security configuration for your application. You can then further customize this configuration to suit your needs. spring security includes:  
    - Authentication: userid/password; finger/face scan; mobile code; BasicAuthenticationFilter
    - Authorization: AuthorizationFilter
    - security filter chain:
      - BasicAuthenticationFilter, AuthorizationFilter,
      - CorsFilter(cross origin resource sharing),
      - CsrFilter (update request in case malicous web use stored authentication)
      - login/logout page filter
      - exceptiontranslationFilter
    - Form Authentication
    - Basici Authentication: not recomend for production
    - JWT Authentication
    - CSRF: to protect the cross-site request Forgery (some website use your cookie to access your bank)
      - create token with every request. here spring will create csrf token but not JWT token
    - CORS
    - OAuth
  - by Default everyting is protected
    - a chain of filters ensure proper authentication and authorization
    - form authentication with session cookie;
 - `spring security` dependency:
   - generate security password and launch defaulte security filter chain for each run
   - provide default login page  Udemy 303
   - `demo` helloworld.java with  above password and "user" s username
   - hairpin use env to store credential: eg. spring.datasource.username=${RDS_USERNAME:serverUser}
     
## run hairpin full statck
### run on local with docker
⁃	my microRNA publication: https://genomebiology.biomedcentral.com/articles/10.1186/gb-2011-12-12-r126
	⁃	docker run mysql_benth
	⁃	run backend 
	  ⁃	source ~/.bashrc
  	⁃	java17 -jar /Users/christix/Documents/Code/Eclipse/spring/xu/hairpin/hairpin-0.0.1-SNAPSHOT.jar
  	⁃	try api test: chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/index.html#requests or
  	⁃	http://localhost:5050/swagger-ui/index.html#/
	⁃	run front
	  ⁃	cd /Users/christix/Documents/Code/react/xu/hairpin-app; 
  	⁃	PORT=3030 npm start &
  	⁃	http://localhost:3030/mirna


ldap.userDn=${LDAP_USER_DN} # set env in ~/.bashrc
ldap.password=${LDAP_PASSWORD}
  
