package com.insider.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insider.backend.entity.UserRequestEntity;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequestEntity, Long> {

}
