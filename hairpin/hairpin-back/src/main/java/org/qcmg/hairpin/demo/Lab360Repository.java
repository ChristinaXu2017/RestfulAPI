package org.qcmg.hairpin.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Lab360Repository extends JpaRepository<LAB360, Integer> {
 	List<LAB360> findByPureNumber(Integer pureNumber);
}
