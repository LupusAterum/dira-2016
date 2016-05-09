package com.jersey.persistence;

import com.jersey.representations.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lupus on 09.05.16.
 */

public interface WorkshopDAO extends JpaRepository<Workshop, Long> {
}
