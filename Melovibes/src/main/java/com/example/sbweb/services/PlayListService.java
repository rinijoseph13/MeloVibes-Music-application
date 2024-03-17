package com.example.sbweb.services;

import java.util.List;

import com.example.sbweb.entities.PlayList;

public interface PlayListService {

	public String addPlayList(PlayList playlist);

	public List<PlayList> fetchAllPlaylist();

	public List<PlayList> searchPlaylist(String keyword);

	public PlayList findById(int id);

	public void deletePlaylist(PlayList playlistToDelete);
	 
	

}
