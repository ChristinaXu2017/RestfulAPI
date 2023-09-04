package org.qcmg.hairpin.favorite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
	
	private static List<Favorite> Favorites = new ArrayList<>();
	
	private static int FavoritesCount = 0;
	
	static {
		Favorites.add(new Favorite(++FavoritesCount, 156, 10, "John",null));
		Favorites.add(new Favorite(++FavoritesCount, 156, 2, "John",null));
		Favorites.add(new Favorite(++FavoritesCount, 156, 21, "John",null));
	}
	
	
	public List<Favorite> findByUsername(String username){
		Predicate<? super Favorite> predicate = 
				Favorite -> Favorite.getUsername().equalsIgnoreCase(username);
		return Favorites.stream().filter(predicate).toList();
	}
	
	public Favorite addFavorite(String miPure, String miOrder, String username, String image ) {
		
		
		Favorite Favorite = 
		new Favorite(++FavoritesCount, 156, 10, username,  image);
		Favorites.add(Favorite);
		return Favorite;
	}
	
	public void deleteById(int id) {
		Predicate<? super Favorite> predicate = Favorite -> Favorite.getOrder() == id;
		Favorites.removeIf(predicate);
	}

	public Favorite findById(int id) {
		Predicate<? super Favorite> predicate = Favorite -> Favorite.getOrder() == id;
		Favorite Favorite = Favorites.stream().filter(predicate).findFirst().get();
		return Favorite;
	}

	public void updateFavorite(Favorite Favorite) {
		deleteById(Favorite.getOrder());
		Favorites.add(Favorite);
	}
}