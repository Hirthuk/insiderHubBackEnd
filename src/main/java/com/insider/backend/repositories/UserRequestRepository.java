package com.insider.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insider.backend.entity.UserRequestEntity;
import com.insider.backend.entity.UsersEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {

	Optional<UserRequestEntity> findBySapid(Long sapid); 
	
	@Transactional
	@Modifying
	@Query("DELETE FROM UserRequestEntity u WHERE u.sapid = :sapid")
	void deleteBySapid(@Param("sapid") Long sapid);

}
