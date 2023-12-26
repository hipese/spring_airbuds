package com.kdt.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.Album;



public interface AlbumRepository extends JpaRepository<Album, Long> {

	@Query("SELECT a FROM Album a " +
		       "LEFT JOIN FETCH a.albumWriter " +
		       "LEFT JOIN FETCH a.albumTag " +
		       "LEFT JOIN FETCH a.tracks " +
		       "WHERE a.artistId LIKE CONCAT(:artist_id, '%')")
		List<Album> findAllByArtistIdStartingWith(@Param("artist_id") String artist_id);
	
	
	@Query("SELECT DISTINCT a FROM Album a " +
		       "LEFT JOIN FETCH a.albumWriter " +
		       "LEFT JOIN FETCH a.albumTag " +
		       "LEFT JOIN FETCH a.tracks t " +
		       "LEFT JOIN FETCH t.trackImages " +
		       "LEFT JOIN FETCH t.trackTags " +
		       "WHERE a.title LIKE %:searchText% " +
		       "   OR EXISTS (SELECT 1 FROM Track t1 WHERE t1.albumId = a.albumId AND (t1.title LIKE %:searchText% OR t1.writer LIKE %:searchText%))")
		List<Album> findAllByTitleStartingWith(@Param("searchText") String searchText);
}
