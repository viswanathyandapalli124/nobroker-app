package com.chandu.NoBroker.DTO;

import com.chandu.NoBroker.model.Photo;
import lombok.Data;

import java.util.Set;

@Data
public class AllPostDTO {

    private String title;
    private String description;
    private Long price;
    private Long pricePerSqft;
    private double buildUpAre;
    private String facing;
    private int bhkType;
    private int bathrooms;
    private String parking;
    private Set<Photo> images;
}
