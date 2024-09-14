package org.gov.qld.maintenance.request;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
	@PutMapping("/admin/update")
	public ResponseEntity<Request> createApproval( @RequestBody Request req) {
		
		if( req.getApproval() != null) {
            Request updatedRequest = requestService.updateRequest(req);
            return ResponseEntity.ok(updatedRequest);
		}
		
		return  ResponseEntity.badRequest().build();
	}  
	
	@PutMapping("/user/update")
	public ResponseEntity<Request> updateRequest( @RequestBody Request req) {
		
		if( req.getApproval() == null) {
            Request updatedRequest = requestService.updateRequest(req);
            return ResponseEntity.ok(updatedRequest);
		}
		
		return  ResponseEntity.badRequest().build();
	}  	
	
	@GetMapping(value="/user/{id}")
	public Request accessRequest1( @PathVariable int id) {
		
		return requestService.getFirstRequest();
	}
   
	@GetMapping(value="/admin/{priority}")
	public List<Request> accessRequest1(@PathVariable Request.Priority pri) {
		
		return requestService.getRequests(pri);
	}
    
	@PostMapping("/test/request")
	public Request createRequest1( @RequestBody Request req) {
		
		System.out.println(req.toString());
		
		return requestService.saveRequest(req);
	}
	  
	
	@GetMapping(value="/test/{order}")
	public Request processRequest( @PathVariable int order) {
		
		return requestService.getFirstRequest();
	}

}