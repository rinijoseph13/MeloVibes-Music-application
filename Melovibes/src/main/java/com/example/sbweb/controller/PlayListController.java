package com.example.sbweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbweb.entities.PlayList;
import com.example.sbweb.entities.Songs;
import com.example.sbweb.entities.Users;
import com.example.sbweb.repositories.SongsRepository;
import com.example.sbweb.repositories.UsersRepository;
import com.example.sbweb.services.PlayListService;
import com.example.sbweb.services.SongsService;

@Controller
public class PlayListController {
	
	private static final Object plist = null;

	@Autowired
	PlayListService pserv;
	
	@Autowired
	SongsService sserv;
	
	@Autowired
    SongsRepository srepo;
	
	
	@GetMapping("/createplaylist")
	public String createPlayList(Model model)
	{
		List<Songs>songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist", songslist);
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlayList(@ModelAttribute PlayList playlist)
	{
		
		pserv.addPlayList(playlist);
		List<Songs> songsList=playlist.getSongs();
		for(Songs song:songsList)
		{
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		return "createplaylist";
	}

	@GetMapping("/viewplaylist")
	public String viewPlayList(Model model)
	{
		List<PlayList>plist=pserv.fetchAllPlaylist();
		model.addAttribute("plist", plist);
		return "displayplaylist";
	}
	
	 @GetMapping("/search-playlist")
	    public String searchPlaylist(@RequestParam("keyword") String keyword, Model model) {
	        List<PlayList> playlists = pserv.searchPlaylist(keyword);
	        model.addAttribute("plist", plist);
	        return "playlistsearch"; // Assuming playlist-search-results.html is your view
	    }
	 
	 @PostMapping("/delete-playlist")
	    public String deletePlaylist(@RequestParam("id") int id) {
	        PlayList playlistToDelete = pserv.findById(id);

	        if (playlistToDelete != null) {
	            // Remove the playlist from each associated song's playlist list
	            for (Songs song : playlistToDelete.getSongs()) {
	                song.getPlaylist().remove(playlistToDelete);
	                srepo.save(song); // Save the modified song
	            }
	            // Now you can safely delete the playlist
	            pserv.deletePlaylist(playlistToDelete);
	        }

	        return "redirect:/displayplaylist";
	    }
	
	 @GetMapping("displayplaylist")
		public String viewCusPlayList(Model model)
		{
			List<PlayList>plist=pserv.fetchAllPlaylist();
			model.addAttribute("plist", plist);
			return "displayplaylist";
		}
}
