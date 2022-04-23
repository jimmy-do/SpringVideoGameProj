package com.cognixia.jump.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.VideoGame;

@Repository
public interface VideoGameRepo extends JpaRepository<VideoGame, Long>{
// Interfaces are a collection of abstract methods (no implementations; in this app, we write the implementations in our controller class); allows Java to support multiple inheritance
	
	Optional<VideoGame> findByTitle(String title);
	List<VideoGame> findByPrice(String price);
	List<VideoGame> findByGenre(String genre);
	List<VideoGame> findByAudienceRating(String audienceRating);
	List<VideoGame> findBySinglePlayer(boolean singlePlayer);
}
