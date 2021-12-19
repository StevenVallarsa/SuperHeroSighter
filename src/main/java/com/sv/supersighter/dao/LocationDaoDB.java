/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
public class LocationDaoDB implements LocationDao {

    private final JdbcTemplate jdbc;
    
    @Autowired
    public LocationDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Location insertLocation(Location location) {
        final String sql = "INSERT INTO location (name, description, address, longitude, latitude) VALUES (?, ?, ?, ?, ?);";
        jdbc.update(sql,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLongitude(),
                location.getLatitude());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationID(newId);
        
        return location;
    }

    @Override
    @Transactional
    public Location deleteLocation(int locationID) {
        
        final String sqlUpdateToDefaultLocation = "UPDATE org SET location = 1002 WHERE location = ?;";
        jdbc.update(sqlUpdateToDefaultLocation, locationID);
        
        final String sqlUpdateSightingLocation = "UPDATE sighting SET location = 1002 WHERE location = ?;";
        jdbc.update(sqlUpdateSightingLocation, locationID);
        
        Location deletedLocation = selectLocation(locationID);
        
        final String sqlDeleteLocation = "DELETE FROM location WHERE locationID = ?;";
        jdbc.update(sqlDeleteLocation, locationID);
        
        return deletedLocation;
        
    }

    @Override
    public void updateLocation(Location location) {
        final String sql = "UPDATE location SET name = ?, description = ?, address = ?, longitude = ?, latitude = ? WHERE locationID = ?;";
        jdbc.update(sql, 
                location.getName(), 
                location.getDescription(),
                location.getAddress(),
                location.getLongitude(),
                location.getLatitude(),
                location.getLocationID());
        
        System.out.println(location.getLocationID());
    }

    @Override
    public Location selectLocation(int locationID) {
        final String sql = "SELECT * FROM location WHERE locationID = ?;";
        return jdbc.queryForObject(sql, new LocationMapper(), locationID);
    }

    @Override
    public List<Location> selectAllLocations() {
        final String sql = "SELECT * FROM location;";
        return jdbc.query(sql, new LocationMapper());
        
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
}
