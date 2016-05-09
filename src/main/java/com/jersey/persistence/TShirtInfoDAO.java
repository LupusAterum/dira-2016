package com.jersey.persistence;

import com.jersey.representations.TShirtInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lupus on 09.05.16.
 */
public interface TShirtInfoDAO extends JpaRepository<TShirtInfo, Long> {

}
