package org.qcmg.hairpin.favorite;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FavoriteJpaResource {
	
	private FavoriteRepository favoriteRepository;
	
	public FavoriteJpaResource(FavoriteRepository FavoriteRepository) {
		this.favoriteRepository = FavoriteRepository;
	}
	
	@GetMapping("/users/{username}/Favorites")
	public List<Favorite> retrieveFavorites(@PathVariable String username) {
		//return FavoriteService.findByUsername(username);
		return favoriteRepository.findByUsername(username);
	}

	@GetMapping("/users/{username}/Favorites/{id}")
	public Favorite retrieveFavorite(@PathVariable String username,
			@PathVariable int id) {
		//return FavoriteService.findById(id);
		return favoriteRepository.findById(id).get();
	}

	@DeleteMapping("/users/{username}/Favorites/{id}")
	public ResponseEntity<Void> deleteFavorite(@PathVariable String username,
			@PathVariable int id) {
		//FavoriteService.deleteById(id);
		favoriteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/Favorites/{id}")
	public Favorite updateFavorite(@PathVariable String username,
			@PathVariable int id, @RequestBody Favorite Favorite) {
		//FavoriteService.updateFavorite(Favorite);
		favoriteRepository.save(Favorite);
		return Favorite;
	}

	@PostMapping("/users/{username}/Favorites")
	public Favorite createFavorite(@PathVariable String username,
			 @RequestBody Favorite Favorite) {
		Favorite.setUsername(username);
		return favoriteRepository.save(Favorite);
	}

}
