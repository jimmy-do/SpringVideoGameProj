package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideoGame implements Serializable { 
// Serialization is the process of converting Java objects into tiny streams of byte data easily transmissible through network (think thin streams like a serial bar code)
	// Data can easily then 'persist' onto database -> SQL
	// Persistent data is data that is successfully stored and retrievable
// Deserialization is the process of reconstructing the data back into its original form (in our case, a Java object)
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // GenerationType.AUTO -> works as well (O_O)==b
	private Long id;
	
	@Column(name="title", unique=true, columnDefinition="varChar(50)", nullable=false)
	private String title;
	
	@Column(name="price", columnDefinition="varChar(10)")
	private String price;
	
	@Column(name="genre", columnDefinition="varChar(10)") 
	private String genre;
	
	@Column(name="audience_rating", columnDefinition="varChar(10)")
	private String audienceRating;
	
	@Column(name="single_player")
	private boolean singlePlayer;

	
	public VideoGame() { this(-1L, "N/A", "N/A", "N/A", "N/A", false);}
	
	public VideoGame(Long id, String title, String price, String genre, String audienceRating, boolean singlePlayer) {
		super();
		this.id = id;							// Ensure JSON properties RequestBody in POST and PUT requests (in Postman) matches model class' attributes
		this.title = title;							
		this.price = price;
		this.genre = genre;
		this.audienceRating = audienceRating;	// ^Capitalization counts
		this.singlePlayer = singlePlayer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getAudienceRating() {
		return audienceRating;
	}

	public void setRating(String audienceRating) {
		this.audienceRating = audienceRating;
	}
	
	public boolean getSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}

	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", title=" + title + ", price=" + price + ", genre=" + genre + ", audience_rating="
				+ audienceRating + ", single_player=" + singlePlayer + "]";
	}
}
