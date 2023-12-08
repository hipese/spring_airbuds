package com.kdt.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kdt.domain.entity.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

	// fetch join 문법
	@Query("select b from Track b left join fetch b.trackImages")
	List<Track> findAllByFetchJoin();

	List<Track> findAllByOrderByTrackIdDesc(Pageable pageable);

	@Query("SELECT t FROM Track t left JOIN FETCH t.trackImages WHERE t.writer LIKE CONCAT(:writer, '%')")
	List<Track> findAllByWriterStartingWith(@Param("writer") String writer);

}
