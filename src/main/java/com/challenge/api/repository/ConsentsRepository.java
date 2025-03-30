package com.challenge.api.repository;


import com.challenge.api.model.Consents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface ConsentsRepository extends MongoRepository<Consents, UUID> {

}
