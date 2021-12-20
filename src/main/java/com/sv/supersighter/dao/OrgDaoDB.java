/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Org;
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
public class OrgDaoDB implements OrgDao {

    private final JdbcTemplate jdbc;
    
    @Autowired
    public OrgDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Org insertOrg(Org org) {
        final String sql = "INSERT INTO org (name, description, location) VALUES (?, ?, ?);";
        jdbc.update(sql,
                org.getName(),
                org.getDescription(),
                org.getLocation());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setOrgID(newId);
        
        return org;
    }

    @Override
    @Transactional
    public Org deleteOrg(int orgID) {
        final String sqlDeleteFromSuperOrg = "DELETE FROM super_org WHERE orgID = ?;";
        jdbc.update(sqlDeleteFromSuperOrg, orgID);
        
        final String sqlDeleteOrg = "DELETE FROM org WHERE orgID = ?;";
        Org deletedOrg = selectOrg(orgID);
        
        jdbc.update(sqlDeleteOrg, orgID);
        
        return deletedOrg;
    }

    @Override
    public void updateOrg(Org org) {
        final String sql = "UPDATE org SET name = ?, description = ?, location = ? WHERE orgID = ?";
        jdbc.update(sql, 
                org.getName(), 
                org.getDescription(),
                org.getLocation(),
                org.getOrgID());
    }

    @Override
    public Org selectOrg(int orgID) {
        final String sql = "SELECT * FROM org WHERE orgID = ?;";
        return jdbc.queryForObject(sql, new OrgMapper(), orgID);
    }

    @Override
    public List<Org> selectAllOrgs() {
        final String sql = "SELECT * FROM org;";
        return jdbc.query(sql, new OrgMapper());
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
}
