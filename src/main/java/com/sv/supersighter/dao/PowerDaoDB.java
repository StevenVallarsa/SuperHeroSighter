/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Power;
import com.sv.supersighter.dto.Sighting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;


/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Repository
public class PowerDaoDB implements PowerDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public PowerDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Power insertPower(Power power) {
        
        final String sql = "INSERT INTO power (name) VALUES (?);";
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
//        jdbc.update((Connection conn) -> {
//            PreparedStatement statement = conn.prepareStatement(
//            sql,
//                    PreparedStatement.RETURN_GENERATED_KEYS);
//            statement.setString(1, power.getName());
//            return statement;
//        }, keyHolder);
        
        jdbc.update(sql, power.getName());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerID(newId);

//        power.setPowerID(keyHolder.getKey().intValue());
        
        return power;
    }

    @Override
    public Power deletePower(int powerID) {
        final String sql = "DELETE FROM power WHERE powerID = ?;";
        Power deletedPower = selectPower(powerID);
        jdbc.update(sql, powerID);
        return deletedPower;
    }

    @Override
    public void updatePower(Power power) {
        final String sql = "UPDATE power SET name = ? WHERE powerID = ?";
        jdbc.update(sql, power.getName(), power.getPowerID());
    }

    @Override
    public Power selectPower(int powerID) {
        final String sql = "SELECT * FROM power WHERE powerID = ?;";
        return jdbc.queryForObject(sql, new PowerMapper(), powerID);
    }

    @Override
    public List<Power> selectAllPower() {
        final String sql = "SELECT * FROM power;";
        return jdbc.query(sql, new PowerMapper());
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
