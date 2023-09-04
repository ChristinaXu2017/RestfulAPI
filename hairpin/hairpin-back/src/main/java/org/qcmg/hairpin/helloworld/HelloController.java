package org.qcmg.hairpin.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World v2"; 
    }
    
    @GetMapping(path = "/hello-world-bean")
    public HelloBean helloWorldBean() {
        return new HelloBean("Hello World Bean v1"); 
    }
    
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloBean(String.format("Hello World, %s", name)); 
    }   

}
