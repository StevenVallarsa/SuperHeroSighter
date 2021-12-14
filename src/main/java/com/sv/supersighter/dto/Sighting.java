/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class Sighting {
    private int sightingID;
    private int superID;
    private int locationID;
    private LocalDateTime timeOfSighting;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.sightingID;
        hash = 97 * hash + this.superID;
        hash = 97 * hash + this.locationID;
        hash = 97 * hash + Objects.hashCode(this.timeOfSighting);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (this.superID != other.superID) {
            return false;
        }
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.timeOfSighting, other.timeOfSighting)) {
            return false;
        }
        return true;
    }

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public LocalDateTime getTimeOfSighting() {
        return timeOfSighting;
    }

    public void setTimeOfSighting(LocalDateTime timeOfSighting) {
        this.timeOfSighting = timeOfSighting;
    }
    
}
