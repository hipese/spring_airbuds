package com.kdt.repositories;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.kdt.domain.entity.Track;

import jakarta.transaction.Transactional;

public interface TrackRepository extends JpaRepository<Track, Long> {
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Track t WHERE t.albumId = :albumId")
	void deleteAllByTrackAlbumId(@Param("albumId") Long albumId);

	
	// fetch join 문법
	@Query("select b from Track b left join fetch b.trackImages")
	List<Track> findAllByFetchJoin();

	@EntityGraph(attributePaths = {"trackImages"})
	List<Track> findAllByOrderByTrackIdDesc(Pageable pageable);

	@Query("SELECT t FROM Track t left JOIN FETCH t.trackImages WHERE t.writer LIKE CONCAT(:writer, '%')")
	List<Track> findAllByWriterStartingWith(@Param("writer") String writer);
	
	@Query("SELECT t FROM Track t left JOIN FETCH t.trackImages WHERE t.writeId LIKE CONCAT(:write_id, '%')")
	List<Track> findAllByWriterIdStartingWith(@Param("writeId") String write_id);
	
	@Query("SELECT t FROM Track t left JOIN FETCH t.trackImages WHERE t.albumId LIKE CONCAT(:album_id, '%')")
	List<Track> findAllByAlbumIdStartingWith(@Param("album_id") String album_id);
	
	@Query("SELECT DISTINCT t FROM Track t LEFT JOIN FETCH t.trackImages WHERE t.title LIKE %:searchText% OR t.writer LIKE %:searchText%")
	List<Track> findAllByTitleOrWriter(@Param("searchText") String searchText);

	@Query("SELECT function('YEAR',t.releaseDate) AS year, function('MONTH',t.releaseDate) AS month, function('COUNT', t) AS count FROM Track t	GROUP BY 1, 2 ORDER BY 1, 2")
	List<Map<String, Object>> selectReleasedMusic();
	
	
}
