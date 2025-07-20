package com.chandu.NoBroker.controller;

import com.chandu.NoBroker.DTO.AllPostDTO;
import com.chandu.NoBroker.DTO.PropertyDetailsDTO;
import com.chandu.NoBroker.model.Property;
import com.chandu.NoBroker.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class PropertyController {

    @Autowired
    PropertyService propertyService;

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("propertyDetails", new PropertyDetailsDTO());
//        model.addAttribute("userId", 1);
//
//        return "index";
//    }

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

        return "success";
    }

    @GetMapping("/getAllProperties")
    public String getAllPropertyList(Model model) {
        model.addAttribute("allProperties", propertyService.getAllProperties());

        return "map";
    }

    @GetMapping("property/{propertyId}")
    public String getPropertyById(@PathVariable("propertyId") Long propertyId, Model model) {
        model.addAttribute("propertyId", propertyId);
        model.addAttribute("property", propertyService.getPropertyById(propertyId));

        return "fullPost";
    }

    @GetMapping("/map")
    public String showMap(Model model) {
        return "map";
    }

    @GetMapping("/getpage")
    public String home(Model model) {
//        Pageable firstPage = PageRequest.of(0, 6);
//        Page<Property> initialProperties = propertyService.getPaginatedProperties(firstPage);
//        model.addAttribute("properties", initialProperties.getContent());
        return "map"; // or your page name
    }

    @GetMapping("/api/properties")
    public List<Property> getPaginatedProperties(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = propertyService.getPaginatedProperties(pageable);
        return propertyPage.getContent();
    }
}