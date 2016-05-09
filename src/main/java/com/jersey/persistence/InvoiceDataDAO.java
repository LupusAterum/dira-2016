package com.jersey.persistence;

import com.jersey.representations.InvoiceData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lupus on 09.05.16.
 */
public interface InvoiceDataDAO extends JpaRepository<InvoiceData, Long> {
}
