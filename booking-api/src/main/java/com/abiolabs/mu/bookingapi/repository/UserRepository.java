package com.abiolabs.mu.bookingapi.repository;

import com.abiolabs.mu.bookingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByUserName(String userName);
}
