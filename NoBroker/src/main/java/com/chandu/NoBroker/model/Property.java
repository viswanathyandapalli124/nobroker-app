package com.chandu.NoBroker.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

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
    private boolean negotiation;

    @Temporal(TemporalType.DATE)
    private Date availableFrom;

    private String furnishing;
    private String parking;
    private String propertyStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "bookmarkedProperties")
    private Set<User> bookmarkedByUsers = new HashSet<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Photo> photos = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}