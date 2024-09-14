package org.gov.qld.maintenance.request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.gov.qld.maintenance.request.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestControlTest {
	

    
	@GetMapping(path = "/test/a")
	public String test() {
		return "Success"; 
	}	
	


}
