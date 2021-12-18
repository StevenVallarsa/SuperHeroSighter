/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Power;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface PowerDao {
    
    Power insertPower(Power power);
    Power deletePower(int powerID);
    void updatePower(Power power);
    Power selectPower(int powerID);
    List<Power> selectAllPower();

}
