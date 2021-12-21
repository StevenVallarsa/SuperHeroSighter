/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.controllers;

import com.sv.supersighter.dto.Location;
import com.sv.supersighter.dto.Org;
import com.sv.supersighter.dto.Power;
import com.sv.supersighter.dto.Sighting;
import com.sv.supersighter.dto.Super;
import com.sv.supersighter.service.SuperServiceLayer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Controller
public class SuperControllerFormActions {
    
    @Autowired
    SuperServiceLayer service;
    
    
    @GetMapping("main")
    public String displayHomePage(Model model) {
        
        List<Super> supers = service.listAllSupers();
        List<Location> locations = service.listAllLocations();
        List<String> sightings = service.listLastTenSightings();
        
        model.addAttribute("supers", supers);
        model.addAttribute("locations", locations);
        model.addAttribute("sightings", sightings);
        
        return "main"; 
        
    }
    
    
    /*
     * POWERS MAPPING
     */
    
    @GetMapping("powers")
    public String displayPowersPage(Power power, Model model) {
        List<Power> powers = service.listAllPowers();

        model.addAttribute("powers", powers);
        return "powers";
    }
    
    @PostMapping("addPower")
    public String addPower(Power power, HttpServletRequest request, Model model) {
        
        String newPowerName = request.getParameter("newPower");
        Power newPower = service.addPower(newPowerName);
        
        if (newPower == null) {
//            FieldError error = new FieldError("power", "name", "That superpower already exists");
//            result.addError(error);
            return "redirect:/powers";
        }
        List<Power> powerList = service.listAllPowers();
        model.addAttribute("powers", powerList);
        
        return "powers";
    }
    
    @GetMapping("deletePower")
    public String deletePower(Integer powerID) {
        service.deletePower(powerID);
        return "redirect:/powers";
    }
    
    @GetMapping("editPower")
    public String editPowerGet(Integer powerID, Model model) {
//        if (name == null || name.trim().isEmpty()) {
//            FieldError error = new FieldError("power", "name", "Name field can't be empty");
//            result.addError(error);
//            return "redirect:/powers";
//        }

        Power power = service.getOnePower(powerID);
        model.addAttribute("power", power);
        
        return "editpower";
    }
    
    @PostMapping("editPower")
    public String editPowerPost(Power power, HttpServletRequest request, Model model) {
        
        String name = request.getParameter("powerName");
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        
        Power editedPower = service.getOnePower(powerID);
        editedPower.setName(name);
        
        service.editPower(editedPower);
        List<Power> powersList = service.listAllPowers();
        model.addAttribute("powers", powersList);
        return "powers";
    }
    
    /*
     * SUPERS MAPPING
     */
    
    @GetMapping("supers")
    public String displaySupersPage(Super superPerson, Model model) {
        List<Super> supers = service.listAllSupers();

        model.addAttribute("supers", supers);
        return "supers";
    }

    @PostMapping("addSuper")
    public String addSuper(Super superPerson, HttpServletRequest request, Model model) {
        
        Super newSuper = new Super();
        newSuper.setName(request.getParameter("name"));
        newSuper.setDescription(request.getParameter("description"));
        newSuper.setAlignment(request.getParameter("alignment"));
        newSuper.setImageURL(request.getParameter("imageURL"));
        
        Super returnedSuper = service.addSuper(newSuper);
        
        if (returnedSuper == null) {
            System.out.println("That super already exists");
            return "redirect:/supers";
        }
        List<Super> supersList = service.listAllSupers();
        model.addAttribute("supers", supersList);
        
        return "redirect:/supers";
    }
    
    @GetMapping("deleteSuper")
    public String deleteSuper(Integer superID) {
        service.deleteSuper(superID);
        return "redirect:/supers";
    }
    
    @GetMapping("editSuper")
    public String editSuperGet(Integer superID, Model model) {

        Super superPerson = service.getOneSuper(superID);
        model.addAttribute("supers", superPerson);
        
        return "editSuper";
    }
    
    @PostMapping("editSuper")
    public String editSuperPost(Super superPerson, HttpServletRequest request, Model model) {
                
        superPerson.setName(request.getParameter("name"));
        superPerson.setDescription(request.getParameter("description"));
        superPerson.setAlignment(request.getParameter("alignment"));
        superPerson.setImageURL(request.getParameter("imageURL"));
                
        service.editSuper(superPerson);
        
        List<Super> supersList = service.listAllSupers();
        model.addAttribute("supers", supersList);

        return "redirect:/supers";
    }
    
    
    
    /*
     * LOCATION MAPPING
     */
    
    @GetMapping("locations")
    public String displayLocationsPage(Location location, Model model) {
        List<Location> locations = service.listAllLocations();

        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(Location location, HttpServletRequest request, Model model) {
        
        Location newLocation = new Location();
        newLocation.setName(request.getParameter("name"));
        newLocation.setDescription(request.getParameter("description"));
        newLocation.setAddress(request.getParameter("address"));
        newLocation.setLongitude(Float.parseFloat(request.getParameter("longitude")));
        newLocation.setLatitude(Float.parseFloat(request.getParameter("latitude")));
        
        Location returnedLocation = service.addLocation(newLocation);
        
        if (returnedLocation == null) {
            System.out.println("That location already exists");
            return "redirect:/locations";
        }
        List<Location> locations = service.listAllLocations();
        model.addAttribute("locations", locations);
        
        return "redirect:/locations";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer locationID) {
        service.deleteLocation(locationID);
        return "redirect:/locations";
    }
    
    @GetMapping("editLocation")
    public String editLocationGet(Integer locationID, Model model) {

        Location location = service.getOneLocation(locationID);
        model.addAttribute("location", location);
        
        return "editLocation";
    }
    
    @PostMapping("editLocation")
    public String editLocationPost(Location location, HttpServletRequest request, Model model) {
        
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setLongitude(Float.parseFloat(request.getParameter("longitude")));
        location.setLatitude(Float.parseFloat(request.getParameter("latitude")));
                
        service.editLocation(location);
        
        List<Location> locations = service.listAllLocations();
        model.addAttribute("locations", locations);

        return "redirect:/locations";
    }    

    

    /*
     * ORG MAPPING
     */
    
    @GetMapping("orgs")
    public String displayOrgsPage(Org org, Model model) {
        List<Org> orgs = service.listAllOrgs();

        model.addAttribute("orgs", orgs);
        return "orgs";
    }

    @PostMapping("addOrg")
    public String addOrg(Org org, HttpServletRequest request, Model model) {
        
        Org newOrg = new Org();
        newOrg.setName(request.getParameter("name"));
        newOrg.setDescription(request.getParameter("description"));
        newOrg.setLocation(Integer.parseInt(request.getParameter("location")));
        
        Org returnedOrg = service.addOrg(newOrg);
        
        if (returnedOrg == null) {
            System.out.println("That org already exists");
            return "redirect:/orgs";
        }
        List<Org> orgs = service.listAllOrgs();
        model.addAttribute("orgs", orgs);
        
        return "redirect:/orgs";
    }
    
    @GetMapping("deleteOrg")
    public String deleteOrg(Integer orgID) {
        service.deleteOrg(orgID);
        return "redirect:/orgs";
    }
    
    @GetMapping("editOrg")
    public String editOrgGet(Integer orgID, Model model) {

        Org org = service.getOneOrg(orgID);
        model.addAttribute("org", org);
        
        return "editOrg";
    }
    
    @PostMapping("editOrg")
    public String editOrgPost(Org org, HttpServletRequest request, Model model) {
        
        org.setName(request.getParameter("name"));
        org.setDescription(request.getParameter("description"));
        org.setLocation(Integer.parseInt(request.getParameter("location")));
                
        service.editOrg(org);
        
        List<Org> orgs = service.listAllOrgs();
        model.addAttribute("orgs", orgs);

        return "redirect:/orgs";
    } 

    

    /*
     * SIGHTINGS MAPPING
     */
    
    @GetMapping("sightings")
    public String displaySightingsPage(Sighting sighting, Model model) {
        List<Sighting> sightings = service.listAllSightings();
        List<Super> supers = service.listAllSupers();
        List<Location> locations = service.listAllLocations();

        model.addAttribute("sightings", sightings);
        model.addAttribute("supers", supers);
        model.addAttribute("locations", locations);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request, Model model) {
        
        Sighting newSighting = new Sighting();
        newSighting.setSuperID(Integer.parseInt(request.getParameter("super")));
        newSighting.setLocationID(Integer.parseInt(request.getParameter("location")));
        newSighting.setTimeOfSighting(LocalDateTime.now());
        
        service.addSighting(newSighting);
        
        List<Sighting> sightings = service.listAllSightings();
        model.addAttribute("sightings", sightings);
        
        return "redirect:/main";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting(Integer sightingID) {
        service.deleteSighting(sightingID);
        return "redirect:/sightings";
    }
    
//    @GetMapping("editSighting")
//    public String editSightingGet(Integer sightingID, Model model) {
//
//        Sighting sighting = service.getOneSighting(sightingID);
//        model.addAttribute("sighting", sighting);
//        
//        return "editOrg";
//    }
//    
//    @PostMapping("editSighting")
//    public String editSightingPost(Sighting sighting, HttpServletRequest request, Model model) {
//     return null;
//    }     
    
}
