/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.service;

import com.sv.supersighter.dao.LocationDao;
import com.sv.supersighter.dao.OrgDao;
import com.sv.supersighter.dao.PowerDao;
import com.sv.supersighter.dao.SuperDao;
import com.sv.supersighter.dao.SightingDao;
import com.sv.supersighter.dto.Location;
import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Power;
import com.sv.supersighter.dto.Super;
import com.sv.supersighter.dto.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Service
public class SuperServiceLayerImpl implements SuperServiceLayer {
    
    final private PowerDao powerDao;
    final private SuperDao superDao;
    final private LocationDao locationDao;
    final private OrgDao orgDao;
    final private SightingDao sightingDao;
    
    @Autowired
    public SuperServiceLayerImpl(
            PowerDao powerDao, 
            SuperDao superDao, 
            LocationDao locationDao,
            OrgDao orgDao,
            SightingDao sightingDao) {
        this.powerDao = powerDao;
        this.superDao = superDao;
        this.locationDao = locationDao;
        this.orgDao = orgDao;
        this.sightingDao = sightingDao;
    }
    

    /*
     *  POWER
     */
    
    @Override
    public Power addPower(String powerName) {
        
        List<Power> powers = powerDao.selectAllPower();
        Power newPower = new Power();
        
        if (powers.stream().anyMatch(power -> power.getName().equals(powerName.trim().toLowerCase()))) {
            System.out.println("This power already exists.");
            return null;
        } else {
            newPower.setName(powerName.trim().toLowerCase());
        }
        
        powerDao.insertPower(newPower);
        
        return newPower;
    }

    @Override
    public Power editPower(Power power) {
        powerDao.updatePower(power);
        return power;
    }
    
    @Override
    public Power deletePower(int powerID) {
        return powerDao.deletePower(powerID);
        
    }
    
    @Override
    public Power getOnePower(int powerID) {
        return powerDao.selectPower(powerID);
    }

    @Override
    public List<Power> listAllPowers() {
        return powerDao.selectAllPower();
    }
    
    
    
    /*
     *  SUPER
     */
    
    @Override
    public Super addSuper(Super superPerson) {
        List<Super> supers = superDao.selectAllSupers();
        
        if (supers.stream().anyMatch(s -> s.getName().equals(superPerson.getName().trim()))) {
            System.out.println("This super already exists.");
            return null;
        }
        
        superDao.insertSuper(superPerson);
        
        return superPerson;
    }

    @Override
    public Super editSuper(Super superPerson) {
        superDao.updateSuper(superPerson);
        return superPerson;
    }

    @Override
    public Super deleteSuper(int superID) {
        return superDao.deleteSuper(superID);
    }

    @Override
    public Super getOneSuper(int superID) {
        return superDao.selectSuper(superID);
    }

    @Override
    public List<Super> listAllSupers() {
        return superDao.selectAllSupers();
    }

    
    
    /*
     *  LOCATION
     */

    @Override
    public Location addLocation(Location location) {
        List<Location> locations = locationDao.selectAllLocations();
        
        if (locations.stream().anyMatch(l -> l.getName().equals(location.getName()))) {
            System.out.println("This Location already exists.");
            return null;
        }
        
        locationDao.insertLocation(location);
        
        return location;    
    }

    @Override
    public Location editLocation(Location location) {
        locationDao.updateLocation(location);
        return location;
    }

    @Override
    public Location deleteLocation(int locationID) {
        return locationDao.deleteLocation(locationID);
    }

    @Override
    public Location getOneLocation(int locationID) {
        return locationDao.selectLocation(locationID);
    }

    @Override
    public List<Location> listAllLocations() {
        return locationDao.selectAllLocations();
    }

    
    
    /*
     *  ORG
     */

    @Override
    public Org addOrg(Org org) {
        List<Org> orgs = orgDao.selectAllOrgs();
        
        if (orgs.stream().anyMatch(o -> o.getName().equals(org.getName()))) {
            System.out.println("This Org already exists.");
            return null;
        }
        
        orgDao.insertOrg(org);
        
        return org;    
    }

    @Override
    public Org editOrg(Org org) {
        orgDao.updateOrg(org);
        return org;
    }

    @Override
    public Org deleteOrg(int orgID) {
        return orgDao.deleteOrg(orgID);
    }

    @Override
    public Org getOneOrg(int orgID) {
        return orgDao.selectOrg(orgID);
    }

    @Override
    public List<Org> listAllOrgs() {
        return orgDao.selectAllOrgs();
    }

    
    
    /*
     *  SIGHTINGS
     */

    @Override
    public Sighting addSighting(Sighting sighting) {
        sightingDao.insertSighting(sighting);
        return sighting;
    }

    @Override
    public Sighting editSighting(Sighting sighting, int superID, int locationID) {
        sightingDao.updateSighting(sighting, superID, locationID);
        return sighting;
    }

    @Override
    public Sighting deleteSighting(int sightingID) {
        return sightingDao.deleteSighting(sightingID);
    }

    @Override
    public Sighting getOneSighting(int sightingID) {
        return sightingDao.selectSighting(sightingID);
    }

    @Override
    public List<Sighting> listAllSightings() {
        return sightingDao.selectAllSightings();
    }

    @Override
    public List<String> listLastTenSightings() {
        return sightingDao.selectAllSightingsDescLimit10();
    }
    


}
