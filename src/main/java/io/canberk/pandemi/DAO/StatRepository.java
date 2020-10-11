package io.canberk.pandemi.DAO;

import io.canberk.pandemi.Entity.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatRepository extends MongoRepository<Stat, Integer> {

}
