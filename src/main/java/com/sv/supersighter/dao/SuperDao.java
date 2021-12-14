/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Location;
import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Sighting;
import com.sv.supersighter.dto.Super;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface SuperDao {
    Sighting recordSighting(Sighting sighting, LocalDateTime dateTimeOfSighting);
    List<Sighting> returnSightingsFromLocation(int locationID);
    List<Location> returnSuperLocations(int superID);
    List<Sighting> returnSightingsByADate(LocalDate date);
    List<Super> returnSupersByOrg(int orgID);
    List<Org> returnOrgsBySuper(int superID);
    
    Super returnSuperByID(int superID);
    List<Sighting> returnAllSightings();
    Sighting deleteSightingBySightingID(int sightingID);
}
