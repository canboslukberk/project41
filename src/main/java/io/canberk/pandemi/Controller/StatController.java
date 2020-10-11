package io.canberk.pandemi.Controller;

import io.canberk.pandemi.Entity.Stat;
import io.canberk.pandemi.Entity.StatUpdatePayload;
import io.canberk.pandemi.Service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/stats")
public class StatController {

    @Autowired
    private StatService statService;

    @GetMapping()
    public List<Stat> getAllStats() {
        return statService.getStats();
    }

    @GetMapping("/city/{city}")
    public List<Stat> getCityStats(@PathVariable String city) {
        return statService.getCityStats(city);
    }

    @GetMapping("/{id}")
    public Optional<Stat> getStat(@PathVariable int id) {
        return statService.getStatById(id);
    }

    @PostMapping()
    @ResponseBody
    public String addStat(@RequestBody StatUpdatePayload stat, HttpServletRequest request, HttpServletResponse response) {
        if (statService.createStat(stat)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "";
    }

//    @RequestMapping(method = RequestMethod.PUT, value="/stats/{id}")
//    public void updateStat(@RequestBody StatUpdatePayload stat, @PathVariable int id){
//        statService.updateStat(id, stat);
//    }

    @DeleteMapping("/{id}")
    public void deleteStat(@PathVariable UUID id) {
        statService.deleteStat(id);
    }


}
