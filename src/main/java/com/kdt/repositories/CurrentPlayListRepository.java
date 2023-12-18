package com.kdt.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.CurrentPlayList;

public interface CurrentPlayListRepository extends JpaRepository<CurrentPlayList, Long> {

    @Query("SELECT c FROM CurrentPlayList c LEFT JOIN FETCH c.tracks WHERE c.id LIKE CONCAT(:id, '%')")
    List<CurrentPlayList> findAllByIdStartingWith(@Param("id") String id, Sort sort);
   
}


