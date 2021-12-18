/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Power;
import com.sv.supersighter.dto.Super;
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
public class SuperDaoDB implements SuperDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public SuperDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Super insertSuper(Super superPerson) {
        final String sql = "INSERT INTO super (name, description, alignment, imageURL) VALUES (?, ?, ?, ?);";
        jdbc.update(sql,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getAlignment(),
                superPerson.getImageURL());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superPerson.setSuperID(newId);
        
        return superPerson;
    }

    @Override
    @Transactional
    public Super deleteSuper(int superID) {
        
        final String sqlDeleteFromSuper = "DELETE FROM super WHERE superID = ?;";
        Super deletedSuper = selectSuper(superID);
        jdbc.update(sqlDeleteFromSuper, superID);
        
        final String sqlDeleteFromSuperOrg = "DELETE FROM super_org WHERE superID = ?;";
        jdbc.update(sqlDeleteFromSuperOrg, superID);
        
        final String sqlDeleteFromSuperPower = "DELETE FROM super_power WHERE superID = ?;";
        jdbc.update(sqlDeleteFromSuperPower, superID);
        
        return deletedSuper;
    }

    @Override
    public void updateSuper(Super superPerson) {
        final String sql = "UPDATE power SET name = ?, description = ?, alignment=?, imageURL = ? WHERE superID = ?";
        jdbc.update(sql, 
                superPerson.getName(), 
                superPerson.getDescription(),
                superPerson.getAlignment(),
                superPerson.getImageURL());
    }

    @Override
    public Super selectSuper(int superID) {
        final String sql = "SELECT * FROM power WHERE superID = ?;";
        return jdbc.queryForObject(sql, new SuperMapper(), superID);
    }

    @Override
    public List<Super> selectAllSupers() {
        final String sql = "SELECT * FROM super;";
        return jdbc.query(sql, new SuperMapper());
    }
    
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
}
