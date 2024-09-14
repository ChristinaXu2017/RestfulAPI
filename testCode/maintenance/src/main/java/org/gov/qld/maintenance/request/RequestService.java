package org.gov.qld.maintenance.request;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    // @Autowired, constructor injection
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

  
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }
    
     
    public Request getFirstRequest() {
        return requestRepository.findFirstByOrderByIdAsc();
    }
    
    public Request updateRequest(Request req) {
    	requestRepository.deleteById(req.getId());
    	return requestRepository.save(req);

	}
    
    
    public List<Request> getRequests(Request.Priority pri){
    	return requestRepository.findByPriority(pri);
    	
    }
      
}