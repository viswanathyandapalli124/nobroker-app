package com.chandu.NoBroker.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Photos")
@Getter
@Setter
@ToString
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}