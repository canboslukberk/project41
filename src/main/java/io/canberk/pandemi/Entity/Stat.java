package io.canberk.pandemi.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Document(collection = "Stat")
public class Stat {



    @Id
    private UUID id;
    @CreatedDate
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date date;
    private String city;
    private int cases;
    private int deaths;
    private int recovered;

    public Stat() {

    }

    public Stat(Date date, String city, int cases, int deaths, int recovered) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.city = city;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
