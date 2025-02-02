## why spring boot
[microservices architecture ](https://www.bing.com/images/search?q=microservices+architecture&form=HDRSC3&first=1) <br>
[spring boot web application includes:](https://medium.com/featurepreneur/spring-boot-for-beginner-62dd9785bb44)<br>
refer to [spring boot feature](https://spring.io/projects/spring-boot)
- It's built on top of the Spring Framework and simplifies its setup and usage.
- It provides a simpler approach to start Spring projects by auto-configuring the setup based on the included dependencies. 
- It comes with embedded servers like Tomcat, Jetty, or Undertow, so you don't need to deploy your applications onto separate servers.
- It provides production-ready features like health checks, metrics, etc., through Spring Boot Actuator.

## React issue
- When use npx create-react-app React app, it keeps complain "Could not resolve dependency", although npm list react shows react@19.0.0 and npm list @testing-library/react shows @testing-library/react@16.1.0; try below command
```
npm config set legacy-peer-deps true
npx create-react-app vacc-build
```
