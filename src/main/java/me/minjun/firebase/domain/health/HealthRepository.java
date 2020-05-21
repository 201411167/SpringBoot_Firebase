package me.minjun.firebase.domain.health;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthRepository extends MongoRepository<HealthInfo, String> {
}
