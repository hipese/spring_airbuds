
package com.kdt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdt.domain.entity.QnaAnswer;
import com.kdt.domain.entity.QnaFile;

public interface QnaFileRepository extends JpaRepository<QnaFile, Long>{
	@Query("select q from QnaFile q where q.parentSeq = ?1%")
	List<QnaFile> selectAllByParentSeq(Long seq);
}

