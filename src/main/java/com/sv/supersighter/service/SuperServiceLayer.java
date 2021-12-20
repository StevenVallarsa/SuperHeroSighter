/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.service;

import com.sv.supersighter.dto.Location;
import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Power;
import com.sv.supersighter.dto.Sighting;
import com.sv.supersighter.dto.Super;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author StevePro
 */
public interface SuperServiceLayer {
    
    Power addPower(String powerName);
    Power editPower(Power power);
    Power deletePower(int powerID);
    Power getOnePower(int powerID);
    List<Power> listAllPowers();
    
    Super addSuper(Super superPerson);
    Super editSuper(Super superPerson);
    Super deleteSuper(int superID);
    Super getOneSuper(int superID);
    List<Super> listAllSupers();

    Location addLocation(Location location);
    Location editLocation(Location location);
    Location deleteLocation(int locationID);
    Location getOneLocation(int locationID);
    List<Location> listAllLocations();   
    
    Org addOrg(Org org);
    Org editOrg(Org org);
    Org deleteOrg(int orgID);
    Org getOneOrg(int orgID);
    List<Org> listAllOrgs();   
    
    Sighting addSighting(Sighting sighting);
    Sighting editSighting(Sighting sighting, int superID, int locationID);
    Sighting deleteSighting(int sightingID);
    Sighting getOneSighting(int sightingID);
    List<Sighting> listAllSightings();
    List<String> listLastTenSightings();
    
}
