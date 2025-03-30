package com.challenge.api.repository;


import com.challenge.api.model.Consents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsentRepository extends JpaRepository<Consents, UUID> {

}
