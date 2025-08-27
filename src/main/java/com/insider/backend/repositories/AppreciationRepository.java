package com.insider.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.insider.backend.entity.AppreciationPostEntity;

import jakarta.transaction.Transactional;


@Repository
public interface AppreciationRepository extends JpaRepository<AppreciationPostEntity, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE appreciations SET likes = likes + 1 WHERE id = :id", nativeQuery = true)
	int updateLikes(Long id);

}
