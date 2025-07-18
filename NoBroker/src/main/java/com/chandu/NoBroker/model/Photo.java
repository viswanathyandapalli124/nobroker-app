package com.chandu.NoBroker.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Photos")
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    private String url;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}