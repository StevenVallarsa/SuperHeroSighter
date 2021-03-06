/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Super;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface SuperDao {
    
    Super insertSuper(Super superPerson);
    Super deleteSuper(int superID);
    void updateSuper(Super superPerson);
    Super selectSuper(int superID);
    List<Super> selectAllSupers();

}
