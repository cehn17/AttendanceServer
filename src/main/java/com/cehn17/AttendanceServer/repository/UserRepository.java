package com.cehn17.AttendanceServer.repository;

import com.cehn17.AttendanceServer.entities.User;
import com.cehn17.AttendanceServer.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserRole(UserRole role);

    Optional<User> findByEmail(String email);

    List<User> findAllByUserRole(UserRole userRole);
}
