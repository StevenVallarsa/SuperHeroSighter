/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.supersighter.controllers;

import com.sv.supersighter.dto.Power;
import com.sv.supersighter.service.SuperServiceLayer;
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
    
    @GetMapping("powers")
    public String displayHomePage(Power power, Model model) {
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
    
    
}
