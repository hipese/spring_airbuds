package com.kdt.repositories;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.kdt.domain.entity.CurrentPlayList;

public interface CurrentPlayListRepository extends JpaRepository<CurrentPlayList, Long> {

    @Query("SELECT c FROM CurrentPlayList c LEFT JOIN FETCH c.tracks WHERE c.id LIKE CONCAT(:id, '%')")
    List<CurrentPlayList> findByIdStartingWith(@Param("id") String id, Pageable pageable);
    
    @Query("SELECT c FROM CurrentPlayList c LEFT JOIN FETCH c.tracks WHERE c.id LIKE CONCAT(:id, '%')")
    List<CurrentPlayList> findAllByIdStartingWith(@Param("id") String id, Sort sort);
   
    @Transactional
    @Modifying
    @Query("DELETE FROM CurrentPlayList c WHERE c.id = :id AND c.trackId = :trackId")
    void deleteByIdAndTrackId(@Param("id") String id, @Param("trackId") Long trackId);

    @Transactional
    @Modifying
    @Query("INSERT INTO CurrentPlayList (seq, trackId, id) VALUES (:seq, :trackId, :id)")
    void insertCurrentPlayList(@Param("seq") Long seq, @Param("trackId") Long trackId, @Param("id") String id);
    
    @Query("select cpl.seq, cpl.trackId, COUNT(cpl) from CurrentPlayList cpl where cpl.id = :id group by cpl.trackId")
    List<Object[]> selectLikeCountByName(@Param("id") String id);
}
