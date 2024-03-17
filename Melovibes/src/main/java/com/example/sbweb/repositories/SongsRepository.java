package com.example.sbweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sbweb.entities.Songs;

public interface SongsRepository extends JpaRepository<Songs,Integer>
{

	public Songs findByName(String name);
	public List<Songs> findByNameContainingIgnoreCase(String name);
	public void deleteByPlaylistId(int id);
}

