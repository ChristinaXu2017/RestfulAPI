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
@RequestMapping("/maintenance")
public class RequestController {
	
    @Autowired
    private final RequestService requestService;


    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    
    @PostMapping("/user/request")
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
   
	@GetMapping(value="/admin/{priority}")  //eg. http://localhost:6001/maintenance/admin/HIGH
	public List<Request> accessRequest1(@PathVariable Request.Priority priority) {	 

		return requestService.getRequests(priority);
	}
	
	
	// debug only
    
	@PostMapping("/test/request")
	public Request createRequest1( @RequestBody Request req) {		
		return requestService.saveRequest(req);
	}
	  
	@GetMapping(value="/test/{order}")
	public Request processRequest( @PathVariable int order) {	
		Request first = requestService.getFirstRequest();
		if (first == null)
			return  new Request("fake type",Request.Priority.LOW,"fake description for test only!");
		return first; 
	}

}