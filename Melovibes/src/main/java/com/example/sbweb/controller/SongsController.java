package com.example.sbweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbweb.entities.PlayList;
import com.example.sbweb.entities.Songs;
import com.example.sbweb.repositories.PlayListRepository;
import com.example.sbweb.repositories.SongsRepository;
import com.example.sbweb.services.SongsService;

@Controller
public class SongsController {
	
	@Autowired
	SongsService sserv;
	
	@Autowired
    SongsRepository srepo;
	
    @Autowired
	PlayListRepository prepo;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song)
	{
		if(sserv.songExists(song.getName())==false)
		{
			sserv.addSongs(song);
			return "addsongs";
		}
		else
		{
			return "addsongs";
		}
	}
	
	@GetMapping("/map-viewsongs")
	public String viewSongs(Model model)
	{
		List<Songs>songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist", songslist);
		return "displaysongs";
	}
	
	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model)
	{
		List<Songs>songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist", songslist);
		return "viewsongs";
	}
	@GetMapping("/search")
	 public String searchSongs(@RequestParam String keyword, Model model) {
	        List<Songs> searchedSongs = sserv.searchSongsByName(keyword);
	        model.addAttribute("searchedSongs", searchedSongs);
	        return "searchedsongs";
	        }
	
	@GetMapping("/searchbar")
	 public String searchSongs1(@RequestParam String keyword, Model model) {
	        List<Songs> searchedSongs = sserv.searchSongsByName(keyword);
	        model.addAttribute("searchedSongs", searchedSongs);
	        return "customersearchedsongs";
	        }
	
	 
	@PostMapping("/delete-song")
	public String deleteSong(@RequestParam("id") int id ) {
	Songs deletedSong=sserv.findById(id);
	if(deletedSong != null) {
		for(PlayList playlist:deletedSong.getPlaylist()) {
			playlist.getSongs().remove(deletedSong);
			prepo.save(playlist);
		}
		sserv.deleteSong(deletedSong);
	}
	return "redirect:displaysongs";
	}
	
	@GetMapping("/displaysongs")
	public String displaySongs(Model model) {
		List<Songs>songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist", songslist);
	    return "displaysongs"; 
	}
	
	

 }
