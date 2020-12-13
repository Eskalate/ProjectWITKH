package com.example.appengine.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CarManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Car> findAll(){
        return jdbcTemplate.query("SELECT id, mark, model FROM Car", (rs, rowNum) -> new Car(rs.getLong("id"), rs.getString("mark"), rs.getString("model")));
    }
    public void update(Car car){
        jdbcTemplate.update("UPDATE Car SET mark=?, model=? WHERE id=?", car.getMark(), car.getModel(), car.getId());
    }
    public void add(Car car){
        jdbcTemplate.update("INSERT INTO Car(mark, model) VALUES(?, ?)", car.getMark(), car.getModel());
    }
    public void delete(Car car){
        jdbcTemplate.update("DELETE FROM Car WHERE ID=?", car.getId());
    }



}
