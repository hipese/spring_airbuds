package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.MyMusicLikes;

public interface MyMusicLikesRepository extends JpaRepository<MyMusicLikes, String>{

	@Query("select mml from MyMusicLikes mml where mml.id = ?1%")
	List<MyMusicLikes> selectById(String id);
}
