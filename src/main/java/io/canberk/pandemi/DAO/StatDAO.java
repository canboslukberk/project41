package io.canberk.pandemi.DAO;

import com.mongodb.BasicDBObject;
import io.canberk.pandemi.Entity.Stat;
import io.canberk.pandemi.Entity.StatUpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StatDAO {

    @Autowired
    private StatRepository repository;

    public List<Stat> getStats() {
        return repository.findAll();
    }

    public Stat createStat(Stat stat) {
        return repository.insert(stat);
    }

    public Optional<Stat> getStatById(int id) {
        return repository.findById(id);
    }

    public Stat deleteStatById(UUID id) {
        List<Stat> stats = repository.findAll();
        for (Stat stat : stats) {
            if (stat.getId().equals(id)) {
                repository.delete(stat);
                return stat;
            }
        }
        return null;
    }

    public Optional<Stat> updateStatById(int id, StatUpdatePayload statUpdatePayload) {
        Optional<Stat> stat = repository.findById(id);
        stat.ifPresent(s -> {
            repository.save((s));
        });
        return stat;
    }

    public List<Stat> getCityStats(String city) {
        List<Stat> cityStats = repository.findAll();
        cityStats.removeIf(stat -> !stat.getCity().equals(city.toLowerCase()));
        return cityStats;
    }
}
