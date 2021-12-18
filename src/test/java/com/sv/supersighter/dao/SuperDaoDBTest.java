/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sv.supersighter.dao;

import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Sighting;
import com.sv.supersighter.dto.Super;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author StevePro
 */
@SpringBootTest
public class SuperDaoDBTest {
    
    @Autowired
    SuperDaoOLD superDao;
    
    public SuperDaoDBTest() {
    }

//    @org.junit.BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // Find the number of supers who are members of 
    // org 1 - "DOOM" and make sure it's Superman at index 0;
//    @Test
//    public void countSupersByOrg() {
//        
//        List<Super> superList = superDao.returnSupersByOrg(1);
//        assertEquals(2, superList.size());
//       
//        superList = superDao.returnSupersByOrg(2);
//        assertEquals(5, superList.size());
//        
//        Super superAtID100 = superDao.returnSuperByID(100);
//        Assertions.assertEquals(superList.get(0).getName(), superAtID100.getName());
//    }
//    
//    
//    // Find the number of orgs who have a super  
//    // as a member
//    @Test
//    public void countOrgsBySuper() {
//        List<Org> orgList = superDao.returnOrgsBySuper(100);
//        assertEquals(1, orgList.size());
//                
//        orgList = superDao.returnOrgsBySuper(104);
//        assertEquals(3, orgList.size());
//    }
//    
//    @Test
//    public void addSightingAndCheckSuperLocationAndSighting() {
//        List<Sighting> listOfSightings = superDao.returnAllSightings();
//        assertEquals(0, listOfSightings.size());
//        
//        // create sighting of Superman at Queen & Young
//        Sighting newSighting = new Sighting();
//        newSighting.setLocationID(1000);
//        newSighting.setSuperID(100);
//        
//        LocalDateTime timeOfSighting = LocalDateTime.of(2020, 02, 20, 20, 20, 20);
//        System.out.println(timeOfSighting);
//        
//        Sighting createdSighting = superDao.recordSighting(newSighting, timeOfSighting);
//        
//        listOfSightings = superDao.returnAllSightings();
//        assertEquals(1, listOfSightings.size());
//        
//        Assertions.assertEquals(listOfSightings.get(0).getLocationID(), createdSighting.getLocationID());
//        
//        // delete created sighting for next time this is tested
////        Sighting deletedSighting = superDao.deleteSightingBySightingID(listOfSightings.get(0).getSightingID());
//    }
//    
}
