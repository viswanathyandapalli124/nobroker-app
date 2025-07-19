package com.chandu.NoBroker.controller;

import com.chandu.NoBroker.DTO.AllPostDTO;
import com.chandu.NoBroker.DTO.PropertyDetailsDTO;
import com.chandu.NoBroker.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String addProperty(@RequestParam("userId") Long userId, @ModelAttribute PropertyDetailsDTO propertyDetailsDTO,
                              Model model) {
        model.addAttribute("propertyId", propertyService.sageProperty(userId, propertyDetailsDTO).getPropertyId());

        return "check";
    }

    @PostMapping("/image/{propertyId}")
    public String saveImages(@PathVariable("propertyId") Long propertyId,
                             @RequestParam("file") MultipartFile[] propertyImages) {
        propertyService.saveImage(propertyId, propertyImages);
        return  "success";
    }

    @GetMapping("/getAllPropertiesList")
    public String getAllPropertyList(Model model) {
        model.addAttribute("allProperties", propertyService.getAllProperties());

        return "allPosts";
    }

    @GetMapping("property/{propertyId}")
    public String getPropertyById(@PathVariable("propertyId") Long propertyId, Model model) {
        model.addAttribute("propertyId", propertyId);
        model.addAttribute("property", propertyService.getPropertyById(propertyId));

        return "fullPost";
    }
}
