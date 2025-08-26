package com.insider.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insider.backend.entity.UserRequestEntity;
import com.insider.backend.entity.UsersEntity;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {

	Optional<UserRequestEntity> findBySapid(Long sapid); 
	 
	void deleteBySapid(Long sapid);
}
