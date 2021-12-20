/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Org;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface OrgDao {
    Org insertOrg(Org org);
    Org deleteOrg(int orgID);
    void updateOrg(Org org);
    Org selectOrg(int orgID);
    List<Org> selectAllOrgs();    
    
}
