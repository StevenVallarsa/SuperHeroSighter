/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Sighting;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface SightingDao {
    
    Sighting insertSighting(Sighting sighting);
    Sighting deleteSighting(int sightingID);
    void updateSighting(Sighting sighting, int superID, int locationID);
    Sighting selectSighting(int sightingID);
    List<Sighting> selectAllSightings();
    List<String> selectAllSightingsDescLimit10();
}
