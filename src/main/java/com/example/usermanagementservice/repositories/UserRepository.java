package com.example.usermanagementservice.repositories;

import com.example.usermanagementservice.dtos.PageData;
import com.example.usermanagementservice.dtos.requests.UserDetailsDto;
import com.example.usermanagementservice.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail(String usernameOrEmail);
@Query(name = "getUserDetailsProc", nativeQuery = true)
Optional<UserDetailsDto> getUserDetails(@Param("userEmail") String email);
@Query(name = "getAllUsersProc", nativeQuery = true)
List<UserDetailsDto> getAllUsers(@Param("toSkip") Long toSkip, @Param("pageCount") Long pageCount);
@Modifying
@Transactional
@Query(name = "deleteMyAccountProc", nativeQuery = true)
int deleteMyAccount(String userEmail);
}