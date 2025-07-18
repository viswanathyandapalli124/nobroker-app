package com.chandu.NoBroker.controller;

import com.chandu.NoBroker.DTO.PropertyDetailsDTO;
import com.chandu.NoBroker.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("propertyDetails", new PropertyDetailsDTO());
        model.addAttribute("userId",1);

        return "index";
    }

    @PostMapping("/submitProperty")
    public String addProperty(@RequestParam("userId") Long userId, @ModelAttribute  PropertyDetailsDTO propertyDetailsDTO) {
        propertyService.sageProperty(userId, propertyDetailsDTO);

        return "success";
    }
}
