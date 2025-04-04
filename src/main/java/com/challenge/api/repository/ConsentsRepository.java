package com.challenge.api.repository;


import com.challenge.api.constants.ConsentsStatus;
import com.challenge.api.model.Consents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface ConsentsRepository extends MongoRepository<Consents, UUID> {

    @Query("{'_id': ?0}")
    @Update("{ '$set': { 'status': ?1 } }")
    void updateConsent(UUID id, ConsentsStatus status);
}
