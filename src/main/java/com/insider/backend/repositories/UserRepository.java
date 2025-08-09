package com.insider.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insider.backend.entity.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

//	Optional Methods for checking
	Optional<UsersEntity> findByEmail(String email);
	
	Optional<UsersEntity> findBySapid(Long sapid);
	
	void deleteBySapid(Long sapid);
}
