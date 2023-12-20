package com.kdt.repositories;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.AlbumWriter;

import jakarta.transaction.Transactional;

public interface AlbumWriterRepository extends JpaRepository<AlbumWriter, Long> {

	@Modifying
	@Transactional
	@Query("DELETE FROM AlbumWriter t WHERE t.albumId = :albumId")
	void deleteAllByAlbumWriterAlbumId(@Param("albumId") Long albumId);
}
