package com.kdt.repositories;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.AlbumTag;

import jakarta.transaction.Transactional;

public interface AlbumTagRepository extends JpaRepository<AlbumTag, Long>{

	
	@Modifying
    @Transactional
    @Query("DELETE FROM AlbumTag t WHERE t.album.id = :albumId")
    void deleteAllByAlbumTagAlbumId(@Param("albumId") Long albumId);
}
