package io.canberk.pandemi.Service;

import io.canberk.pandemi.DAO.StatDAO;
import io.canberk.pandemi.Entity.Stat;
import io.canberk.pandemi.Entity.StatUpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StatService {

    @Autowired
    private StatDAO statDAO;

    public List<Stat> getStats() {
        return statDAO.getStats();
    }

    public Optional<Stat> getStatById(int id) {
        return statDAO.getStatById(id);
    }

    public boolean createStat(StatUpdatePayload news){
        ParseService ps = new ParseService(news);
        Stat stat = ps.performParse();
        if(stat == null){
            return false;
        }
        statDAO.createStat(stat);
        return true;
    }

    public Optional<Stat> updateStat(int id, StatUpdatePayload stat) {
        return statDAO.updateStatById(id, stat);
    }

    public Stat deleteStat(UUID id) {
        return statDAO.deleteStatById(id);
    }

    public List<Stat> getCityStats(String city) {
        return statDAO.getCityStats(city);
    }
}
