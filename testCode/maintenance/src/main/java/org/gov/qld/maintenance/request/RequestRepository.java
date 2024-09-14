package org.gov.qld.maintenance.request;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	List<Request> findById(long id);
	
    // Find a list of requests by priority
    List<Request> findByPriority(Request.Priority poriority);
    
    // return the first avaible record for test
    Request findFirstByOrderByIdAsc();
}

