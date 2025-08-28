package com.insider.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insider.backend.entity.UsersEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

//	Optional Methods for checking
	Optional<UsersEntity> findByEmail(String email);
	
	Optional<UsersEntity> findBySapid(Long sapid);
	
	@Transactional
	void deleteBySapid(Long sapid);
	
	Optional<UsersEntity> findByPhoneNumber(Long phoneNumber);
	
	List<UsersEntity> findByRole(String role);
	
	@Modifying
	@Transactional
	@Query(value = "update users set total_appreciation = total_appreciation+1 where sapid= :sapid", nativeQuery = true)
	int totalApprecitionIncreate(Long sapid);
	
	@Modifying
	@Transactional
	@Query(value = """
	    WITH ranked AS (
	        SELECT id, RANK() OVER (ORDER BY total_appreciation DESC) AS new_rank
	        FROM users
	    )
	    UPDATE users u
	    SET rank = r.new_rank
	    FROM ranked r
	    WHERE u.id = r.id
	    """, nativeQuery = true)
	int recalculateRanks();

	
}
