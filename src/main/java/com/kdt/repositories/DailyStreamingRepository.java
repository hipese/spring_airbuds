package com.kdt.repositories;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.DailyStreaming;

public interface DailyStreamingRepository extends JpaRepository<DailyStreaming, Long>{
	
	@Query("select ds from DailyStreaming ds where function('DATE',ds.streamDate) = function('CURDATE') and ds.trackId = :trackId")
	DailyStreaming selectStreamByDate(@Param("trackId") Long trackId);
	
//	@Query("select function('date',ds.streamDate), function('sum',ds.streamCount) from DailyStreaming ds where function('date',ds.streamDate) = function('curdate') group by function('date',ds.streamDate)")	
	@Query("select function('sum',ds.streamCount) from DailyStreaming ds where function('date',ds.streamDate) = function('curdate') group by function('date',ds.streamDate)")
	Long selectStreamCount();
}
