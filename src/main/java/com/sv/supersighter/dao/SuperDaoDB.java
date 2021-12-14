/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Location;
import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Power;
import com.sv.supersighter.dto.Sighting;
import com.sv.supersighter.dto.Super;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Repository
public class SuperDaoDB implements SuperDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public SuperDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Sighting recordSighting(Sighting sighting, LocalDateTime dateTimeOfSighting) {
        
        final String sql = "INSERT INTO sighting (super, location, timeOfSighting) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        if (dateTimeOfSighting == null) {
            sighting.setTimeOfSighting(LocalDateTime.now());
        } else {
            sighting.setTimeOfSighting(dateTimeOfSighting);
        }

        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, sighting.getSuperID());
            statement.setInt(2, sighting.getLocationID());
            statement.setTimestamp(3, Timestamp.valueOf(sighting.getTimeOfSighting()));
            return statement;
        }, keyHolder);
        
        sighting.setSightingID(keyHolder.getKey().intValue());

        return sighting;
    }

    @Override
    public List<Sighting> returnSightingsFromLocation(int locationID) {
        final String sql = "SELECT * FROM sighting WHERE location = ?;";
        return jdbc.query(sql, new SightingMapper(), locationID);
    }

    @Override
    public List<Location> returnSuperLocations(int superID) {
        final String sql = "SELECT DISTINCT l.* FROM location l JOIN sighting s ON l.locationID = s.location WHERE s.super = ?;";
        return jdbc.query(sql, new LocationMapper(), superID);
    }

    @Override
    public List<Sighting> returnSightingsByADate(LocalDate date) {
        final String sql = "SELECT * FROM sighting WHERE DATE(timeOfSighting) = ?;";
        return jdbc.query(sql, new SightingMapper(), date);
    }

    @Override
    public List<Super> returnSupersByOrg(int orgID) {
        final String sql = "SELECT s.* FROM super s " +
                "JOIN super_org so ON s.superID = so.superID " +
                "JOIN org o ON o.orgID = so.orgID " + 
                "WHERE o.orgID = ?";
        return jdbc.query(sql, new SuperMapper(), orgID);
    }

    @Override
    public List<Org> returnOrgsBySuper(int superID) {
        final String sql = "SELECT o.* FROM super s " +
                "JOIN super_org so ON s.superID = so.superID " +
                "JOIN org o ON o.orgID = so.orgID " + 
                "WHERE s.superID = ?";
        return jdbc.query(sql, new OrgMapper(), superID);
    }
    
    
    // Helper Functions to gather individual rows of DB tables
    public Super returnSuperByID(int superID) {
        final String sql = "SELECT * FROM super WHERE superID = ?";
        List<Super> superListOfOne = jdbc.query(sql, new SuperMapper(), superID);
        return superListOfOne.get(0);
    }
    
    public List<Sighting> returnAllSightings() {
        final String sql = "SELECT * FROM sighting";
        return jdbc.query(sql, new SightingMapper());
    }
    
    public Sighting deleteSightingBySightingID(int sightingID) {
        final String sql = "DELETE FROM sighting WHERE sightingID = ?";
        return jdbc.queryForObject(sql, new SightingMapper(), sightingID);
    }
    

    
    
    // MAPPERS
    
    private static final class SuperMapper implements RowMapper<Super> {
        
        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException {
            Super superPerson = new Super();
            superPerson.setSuperID(rs.getInt("superID"));
            superPerson.setName(rs.getString("name"));
            superPerson.setDescription(rs.getString("description"));
            superPerson.setAlignment(rs.getString("alignment"));
            superPerson.setImageURL(rs.getString("imageURL"));
            return superPerson;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {
        
        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLongitude(rs.getFloat("longitude"));
            location.setLatitude(rs.getFloat("latitude"));
            return location;
        }
    }

    private static final class OrgMapper implements RowMapper<Org> {
        
        @Override
        public Org mapRow(ResultSet rs, int index) throws SQLException {
            Org org = new Org();
            org.setOrgID(rs.getInt("orgID"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setLocation(rs.getInt("location"));
            return org;
        }
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
    
    private static final class PowerMapper implements RowMapper<Power> {
        
        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {
            Power power = new Power();
            power.setPowerID(rs.getInt("powerID"));
            power.setName(rs.getString("name"));
            return power;
        }
    }
}
    
