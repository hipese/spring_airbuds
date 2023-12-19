package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.FollowingSingerView;

public interface FollowingSingerViewRepository extends JpaRepository<FollowingSingerView, String>{

	List<FollowingSingerView> findByFollower(String follower);
	
	Long countBySinger(String singer);
}
