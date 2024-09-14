package org.gov.qld.maintenance.request;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestControl {
	
    @Autowired
    private final RequestService requestService;


    public RequestControl(RequestService requestService) {
        this.requestService = requestService;
    }
    
    @PostMapping
    public ResponseEntity<Request> createRequests(@RequestBody Request request) {
        Request savedRequest = requestService.saveRequest(request);
        return ResponseEntity.ok(savedRequest);
    }
    
    
	@PostMapping("/test/request")
	public Request createRequest( @RequestBody Request req) {
		
		System.out.println(req.toString());
		
		return requestService.saveRequest(req);
	}
    
	@GetMapping(path = "/test/a")
	public String test() {
		return "Success"; 
	}	
	
	
	@GetMapping(value="/test/{order}")
	public Request processRequest( @PathVariable int order) {
		
		return requestService.getFirstRequest();
	}
	
}