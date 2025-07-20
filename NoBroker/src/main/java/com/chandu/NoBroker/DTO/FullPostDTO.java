package com.chandu.NoBroker.DTO;

import com.chandu.NoBroker.model.Photo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class FullPostDTO {

    private String title;
    private String address;
    private Long price;

    private Boolean isSale;

    private double buildUpArea;
    private Long expectedDeposit;

    private int bedroom;
    private String propertyType;
    private int floor;
    private Date availabilityFrom;
    private String parking;
    private int propertyAge;
    private int balcony;
    private LocalDateTime createdAt;

    //overview
    private String furnishing;
    private String facing;
    private String waterSupply;
    private int totalFloors;
    private int bathrooms;
    private Boolean petAllowed;
    private Boolean nonVegAllowed;
    private Boolean gatedSecurity;

//    /description
    private String description;

    //amenities

    private Boolean lift;
    private Boolean gasPipeLine;
    private Boolean airConditioner;
    private Boolean park;
    private Boolean houseKeeping;
    private Boolean internetService;
    private Boolean powerBackUp;
    private Boolean serventRoom;
    private Boolean swimmingPool;
    private Boolean fireSafety;

    private Set<Photo> images;
}