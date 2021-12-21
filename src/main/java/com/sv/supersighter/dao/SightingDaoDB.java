/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Repository
public class SightingDaoDB implements SightingDao {

        private final JdbcTemplate jdbc;
    
    @Autowired
    public SightingDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Sighting insertSighting(Sighting sighting) {
        final String sql = "INSERT INTO sighting (super, location, timeOfSighting) VALUES (?, ?, ?);";
        jdbc.update(sql,
                sighting.getSuperID(),
                sighting.getLocationID(),
                sighting.getTimeOfSighting());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSuperID(newId);
        
        return sighting;
        
    }

    @Override
    public Sighting deleteSighting(int sightingID) {
        final String sql = "DELETE FROM sighting WHERE sightingID = ?;";
        
        Sighting sighting = selectSighting(sightingID);
        
        jdbc.update(sql, sightingID);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting, int superID, int locationID) {
        System.out.println("Updating sighting not supported yet.");
//        final String sql = "UPDATE sighting SET super = ?, location = ?, timeOfSighting = ? WHERE super = ? AND location = ?";
//        jdbc.update(sql,
//                sighting.getSuperID(),
//                sighting.getLocationID(),
//                sighting.getTimeOfSighting(),
//                superID,
//                locationID);
    }

    @Override
    public Sighting selectSighting(int sightingID) {
        final String sql = "SELECT * FROM sighting WHERE sightingID = ?;";
        return jdbc.queryForObject(sql, new SightingMapper(), sightingID);
    }

    @Override
    public List<Sighting> selectAllSightings() {
        final String sql = "SELECT * FROM sighting;";
        return jdbc.query(sql, new SightingMapper());
    }
    
    @Override
    public List<String> selectAllSightingsDescLimit10() {
        final String sql = "SELECT super.name, location.name, sighting.timeOfSighting FROM sighting " +
            "JOIN super ON super.superID = sighting.super " +
            "JOIN location ON location.locationID = sighting.location " +
            "ORDER BY sighting.sightingID DESC " +
            "LIMIT 10;";
        
        List<String> result = jdbc.query(sql, (RowMapper<String>) new StringMapper());
        return result;
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSuperID(rs.getInt("super"));
            sighting.setLocationID(rs.getInt("location"));
            sighting.setTimeOfSighting(rs.getTimestamp("timeOfSighting").toLocalDateTime());
            return sighting;
        }
    }
    
    private static final class StringMapper implements RowMapper<String> {
        
        @Override
        public String mapRow(ResultSet rs, int index) throws SQLException {
            String result = "";
            result += rs.getString("super.name");
            result += " @ ";
            result += rs.getString("location.name");
            result += " - ";
            result += rs.getString("sighting.timeOfSighting");
            
            return result;
        }
    }
}
