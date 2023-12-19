package com.kdt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdt.domain.entity.AlbumWriter;

public interface AlbumWriterRepository extends JpaRepository<AlbumWriter, Long> {

}
