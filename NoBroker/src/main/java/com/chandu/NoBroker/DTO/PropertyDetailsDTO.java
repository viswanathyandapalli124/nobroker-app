package com.chandu.NoBroker.DTO;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class PropertyDetailsDTO {

    private String propertyType;
    private String propertyName;
    private int bhkType;
    private int floor;
    private int totalFloors;
    private int propertyAge;
    private String facing;
    private double buildUpArea;
    private String availableFor;
    private long expectedRent;
    private long exceptedDeposit;
    private boolean negotiation1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date availableFrom;

    private String furnishing;
    private String parking;
    private String propertyStatus;
    private String description;

    private String city;
    private String locality;
    private String landmark;

    private Integer bathrooms;
    private Integer balcony;
    private String waterSupply;
    private Boolean petAllowed;
    private Boolean gym;
    private Boolean nonVeg;
    private Boolean gatedSecurity;
    private String showProperty;
    private String propertyCondition;
    private String secondaryNumber;
    private String nearByPlace;
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
}