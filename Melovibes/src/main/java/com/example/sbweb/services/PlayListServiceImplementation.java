package com.example.sbweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sbweb.entities.PlayList;
import com.example.sbweb.entities.Songs;
import com.example.sbweb.repositories.PlayListRepository;
import com.example.sbweb.repositories.SongsRepository;

@Service
public class PlayListServiceImplementation implements PlayListService{
	
    @Autowired
	PlayListRepository prepo;
   
	@Override
	public String addPlayList(PlayList playlist) {
		prepo.save(playlist);
		return "Playlist is created";
	}
	@Override
	public List<PlayList> fetchAllPlaylist() {
		List<PlayList>plist=prepo.findAll();
		return plist;

	}
	@Override
	public List<PlayList> searchPlaylist(String keyword) {
		        return prepo.findByNameContainingIgnoreCase(keyword);
		    }
	@Override
	public PlayList findById(int id) {
			return prepo.findById(id).orElse(null);
		}
	@Override
	public void deletePlaylist(PlayList playlistToDelete) {
		 prepo.delete(playlistToDelete);
		
	}
	
		
	}
	
	
	
		
	
	


