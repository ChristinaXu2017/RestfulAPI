package org.gov.qld.maintenance.request;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    // @Autowired, constructor injection
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Transactional
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }
    
    @Transactional
    public Request getFirstRequest() {
        return requestRepository.findFirstByOrderByIdAsc();
    }
      
}