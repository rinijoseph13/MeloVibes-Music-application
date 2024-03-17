package com.example.sbweb.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Songs {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String name;
	String genre;
	String image;
	String link;
	
	
	@ManyToMany
	List<PlayList>playlist;


	public Songs() 
	{
		super();
	}


	public Songs(int id, String name, String genre, String image, String link, List<PlayList> playlist) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.image = image;
		this.link = link;
		this.playlist = playlist;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public List<PlayList> getPlaylist() {
		return playlist;
	}


	public void setPlaylist(List<PlayList> playlist) {
		this.playlist = playlist;
	}


	@Override
	public String toString() {
		return "Songs [id=" + id + ", name=" + name + ", genre=" + genre + ", image=" + image + ", link=" + link
				+ ", playlist=" + playlist + "]";
	}
	
	
	

	

	}