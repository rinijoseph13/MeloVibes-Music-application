package com.example.sbweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbweb.entities.Songs;

public interface SongsService {
	public String addSongs(Songs song);
	public boolean songExists(String name);
	public List<Songs> fetchAllSongs();
	public void updateSong(Songs song);
	public List<Songs> searchSongsByName(String keyword);
	public Songs findById(int id);
	public void deleteSong(Songs deletedSong);
	public void deleteByPlaylistId(int id);
	
	
	


}
