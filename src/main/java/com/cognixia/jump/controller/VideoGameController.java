package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.VideoGame;
import com.cognixia.jump.repo.VideoGameRepo;

@RequestMapping("/api/videogames") // Make sure end points match in Postman
@RestController
public class VideoGameController {
	
	// Dependency Injection allows loose coupling through not having to instantiate every object we need
	// Spring is automatically configuring a bean for us in an XML file with ApplicationContext -> we just inject the object into our class
	@Autowired
	VideoGameRepo repo;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllVideoGames(){
		
		List<VideoGame> allVideoGames = repo.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allVideoGames);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getVideoGameById(@PathVariable Long id) {
		
		Optional<VideoGame> foundVideoGame = repo.findById(id); 
		if(foundVideoGame.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(foundVideoGame);
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("videogame not found by id " + id);
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<Object> getVideoGameByTitle(@PathVariable String title){
		
		Optional<VideoGame> foundVideoGameByTitle = repo.findByTitle(title);
		if(!foundVideoGameByTitle.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(foundVideoGameByTitle);
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("videogame not found by title " + title);
	}
	
	@GetMapping("/price/{price}")
	public ResponseEntity<Object> getVideoGameByPrice(@PathVariable String price){
		
		List<VideoGame> foundVideoGameByPrice = repo.findByPrice(price);
		if(!foundVideoGameByPrice.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(foundVideoGameByPrice);
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("videogame not found by price " + price);
	}
	
	@GetMapping("/genre/{genre}")
	public ResponseEntity<Object> getVideoGameByGenre(@PathVariable String genre){
		
		List<VideoGame> foundVideoGameByGenre = repo.findByGenre(genre);
		if(!foundVideoGameByGenre.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(foundVideoGameByGenre);
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("videogame not found by genre " + genre);
	}
	
	@GetMapping("/audiencerating/{audienceRating}")
	public ResponseEntity<Object> getVideoGameByRating(@PathVariable String audienceRating){
		
		List<VideoGame> foundVideoGameByAudienceRating = repo.findByAudienceRating(audienceRating);
		if(!foundVideoGameByAudienceRating.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(foundVideoGameByAudienceRating);
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("videogame not found by audienceAudienceRating " + audienceRating);
	}
	
	@GetMapping("/singleplayer/{singlePlayer}")
	public ResponseEntity<Object> getVideoGameBySingplePlayer(@PathVariable boolean singlePlayer){
		
		List<VideoGame> foundVideoGameBySinglePlayer = repo.findBySinglePlayer(singlePlayer);
		if(!foundVideoGameBySinglePlayer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(foundVideoGameBySinglePlayer);
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("videogame not found by singlePlayer " + singlePlayer);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateVideoGame(@RequestBody VideoGame videogame) {
		
		Optional<VideoGame> found = repo.findById(videogame.getId()); 
			if(found.isPresent()) {
				repo.save(videogame);
				return ResponseEntity.status(HttpStatus.FOUND).body("VideoGame updated successfully: " + videogame.toString());
			} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VideoGame not found!");
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addVideoGame(@RequestBody VideoGame newVideoGame) {
		
		VideoGame addedVideoGame=repo.save(newVideoGame);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedVideoGame.toString()+" created");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteVideoGameById(@PathVariable Long id) {
		
		Optional<VideoGame> found = repo.findById(id);
		if(found.isPresent()) {
			repo.deleteById(id); 
			return ResponseEntity.status(HttpStatus.OK).body("VideoGame: " + found.get().toString() + " deleted.");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VideoGame not found!");
	}
}
