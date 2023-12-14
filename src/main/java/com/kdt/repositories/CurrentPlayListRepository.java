package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.CurrentPlayList;

public interface CurrentPlayListRepository extends JpaRepository<CurrentPlayList, Long>{

}
