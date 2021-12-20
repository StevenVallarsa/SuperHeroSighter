/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.controllers;

import com.sv.supersighter.dto.Location;
import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Sighting;
import com.sv.supersighter.dto.Super;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sv.supersighter.dao.SuperDaoOLD;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@RestController
@RequestMapping("/")
public class SuperControllerFilePath {
    
    private SuperDaoOLD superDao;
    
    public SuperControllerFilePath(SuperDaoOLD superDao) {
        this.superDao = superDao;
    }
    
//    @GetMapping("/")
//    public String hello() {
//        return "Welcome to the party, pal.";
//    }
//    
    @PostMapping("/sighting")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sighting> recordSighting(@RequestBody Sighting sighting) {
                         
        return ResponseEntity.ok(superDao.recordSighting(sighting, null));
    }
    
    @GetMapping("/orgs/{superID}")
    public List<Org> getOrgsBySuperID(@PathVariable int superID) {
        return superDao.returnOrgsBySuper(superID);
    }
    
    @GetMapping("/supers/{orgID}")
    public List<Super> getSupersByOrgID(@PathVariable int orgID) {
        return superDao.returnSupersByOrg(orgID);
    }
    
    @GetMapping("/sightings/{locationID}")
    public List<Sighting> getSightingsFromLocation(@PathVariable int locationID) {
        return superDao.returnSightingsFromLocation(locationID);
    }
    
    @GetMapping("/superlocations/{superID}")
    public List<Location> getLocationsFromSuper(@PathVariable int superID) {
        return superDao.returnSuperLocations(superID);
    }
    
    @GetMapping("/date/{year}/{month}/{day}")
    public List<Sighting> getSightingsByDate(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        String dateAsString = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
        LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ISO_LOCAL_DATE);
        return superDao.returnSightingsByADate(date);
    }
}

