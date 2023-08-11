package org.qcmg.hairpin.favorite;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

 

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	
	List<Favorite> findByUsername(String username);

}
