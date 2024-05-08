package com.professionalpractice.medicalbookingbespring.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.professionalpractice.medicalbookingbespring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u")
    Page<User> queryUsers(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.isLocked = ?1")
    Page<User> queryByIsLocked(Boolean status, Pageable pageable);
}
