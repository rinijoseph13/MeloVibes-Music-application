package com.example.sbweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sbweb.entities.Songs;
import com.example.sbweb.repositories.PlayListRepository;
import com.example.sbweb.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService {
	
	@Autowired
    SongsRepository srepo;
	
	
	@Override
	public String addSongs(Songs song) {
		srepo.save(song);
		return "song is added";
	}

	@Override
	public boolean songExists(String name) {
		if(srepo.findByName(name)==null) {
		return false;
	}
		else
		{
			return true;
		}

}
    @Override
	public List<Songs> fetchAllSongs (){
		List<Songs>songslist=srepo.findAll();
		return songslist;
	}

	@Override
	public void updateSong(Songs song) {
		srepo.save(song);
		
	}

	@Override
	 public List<Songs> searchSongsByName(String keyword) {
        return srepo.findByNameContainingIgnoreCase(keyword);
    }

	@Override
	public Songs findById(int id) {
			return srepo.findById(id).orElse(null);
		}

	@Override
	public void deleteSong(Songs deletedSong) {
			srepo.delete(deletedSong);
		}

	@Override
	public void deleteByPlaylistId(int id) {
		        srepo.deleteByPlaylistId(id);
		    }

	
		
	}
		
		
	
	


