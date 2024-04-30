## why spring boot
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
- `demo:` go to [spring initializr](https://start.spring.io/)
  - java; maven; latest spring boot version but not snapshot; jar; java17
  - spring boot provides variety of starter dependencies:
     - `spring web` for restAPI, tomcat: spring-boot-starter-web and spring-boot-starter-test
     - `spring boot actuator`: see below
     - `spring boot DevTools`: startup server automatically during developping
      
- unzip the downloaded folder to eclipse: File::import:: existing maven project
    - pom.xml
    - maven dependencies
    - @springbootapplication class with main method
      
- build a simple rest API using spring boot
  - see [helloWorld inside hairpin](https://github.com/ChristinaXu2017/RestfulAPI/blob/main/hairpin/hairpin-back/src/main/java/org/qcmg/hairpin/helloworld/HelloController.java)
    - @RestController: means every method annotated with @RequestMapping etc in this class return types are directly written to HTTP response
    - @Controller: means method returns view (HTML, JSP ). eg return "cycle2" means return cycle2.jsp file;
        - unless annotated both @GetMapping("/rawdata1") and @ResponseBody to the method, it will return tyeps to HTTP responses as method inside @RestController class
    - @RequestMapping handle all types of HTTP requests (GET, POST, PUT, DELETE, etc.) when you specify the method type.
    - @GetMapping is a shortcut for @RequestMapping(method = RequestMethod.GET), only handle GET type HTTP requests.

- `demo:` HelloController.java: helloWorld() and helloWorldPathVariable(@PathVariable String name)
   - http://localhost:8080/hello-world/path-variable/{name}
 
- `demo:` auto configuration
  - there are many autoconfiguration classes
  - org.springframework.boot.autoconfigure.web package is related for restapi
  - Tomcat is configured for you (EmbededWebServerFactoryCustomizerAutoConfiguration).
  - let's see dispatcherServletAutoConfigureation source code, open type (command/Ctrl+shift+T); you can see dispatchservlet is configured, means web application of rest API is enabled.
  -  you can see Error page is auto-confiugured for you: it will pop up "Whitelabel Error page... status=404" when url is not exists.
  - class annoated with @@ConfigurationProperties(...) can retrive settings from application.properties (Udemy: 66: step 10 - ConfigureationProperties)
  - Spirng boot DevTools: Udemy: 64: step 08 - build Faster with spring Boot DevTools ( if have time demo this part) : it can automatically restart server during developping (code updated).
    
- `demo maybe` application.properties
  - logging.level.org.springframework=debug refer to [application.properties](02-springboot/src/main/resources/application.properties).  now you run app, you can see log from app run std report "Conditions Evaluation Report": 
  - Spring boot Actuator
    - <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-actuator</artifactId></dependency>  
    - management.endpoints.web.exposure.include=health,metrics  or management.endpoints.web.exposure.include=*
    - localhost:8000/actuator/   : see java, dependecy, bean, jvm etc. eg. http.server.requests: request number, time consuming etc. 

- `demo maybe` spring boot vs spring mvc vs spring udemy 69








  
