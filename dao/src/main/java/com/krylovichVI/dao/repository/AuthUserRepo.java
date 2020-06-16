package com.krylovichVI.dao.repository;

import com.krylovichVI.dao.entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepo extends JpaRepository<AuthUserEntity, Long> {
}
