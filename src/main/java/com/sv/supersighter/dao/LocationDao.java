/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Location;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface LocationDao {
    Location insertLocation(Location location);
    Location deleteLocation(int locationID);
    void updateLocation(Location location);
    Location selectLocation(int locationID);
    List<Location> selectAllLocations();    
}
