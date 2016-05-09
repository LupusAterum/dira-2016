package com.jersey.persistence;

import com.jersey.representations.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lupus on 09.05.16.
 */
public interface UserDAO extends JpaRepository<User, Long> {
}
