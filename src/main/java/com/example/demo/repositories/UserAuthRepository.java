package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<User, String> {
    @Override
    @Query("SELECT DISTINCT u FROM User u WHERE u.phoneNumber = :phoneNumber  ")
    User getReferenceById(@Param("phoneNumber")String phoneNumber);
}
