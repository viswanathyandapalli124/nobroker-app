package com.chandu.NoBroker.repository;

import com.chandu.NoBroker.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository  extends JpaRepository<Photo, Long> {
}
