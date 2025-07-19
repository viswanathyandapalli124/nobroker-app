package com.chandu.NoBroker.service;

import com.chandu.NoBroker.DTO.PropertyDetailsDTO;
import com.chandu.NoBroker.model.*;
import com.chandu.NoBroker.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotosRepository photosRepository;

    public Property sageProperty(Long userId, PropertyDetailsDTO dto) {
        User user = userRepository.findById(userId).orElse(null);

        Property property = new Property();
        property.setPropertyType(dto.getPropertyType());
        property.setPropertyName(dto.getPropertyName());
        property.setBhkType(dto.getBhkType());
        property.setFloor(dto.getFloor());
        property.setTotalFloors(dto.getTotalFloors());
        property.setPropertyAge(dto.getPropertyAge());
        property.setFacing(dto.getFacing());
        property.setBuildUpArea(dto.getBuildUpArea());
        property.setAvailableFor(dto.getAvailableFor());
        property.setExpectedRent(dto.getExpectedRent());
        property.setExceptedDeposit(dto.getExceptedDeposit());
        property.setAvailableFrom(dto.getAvailableFrom());
        property.setFurnishing(dto.getFurnishing());
        property.setParking(dto.getParking());
        property.setPropertyStatus(dto.getPropertyStatus());
        property.setDescription(dto.getDescription());

        Address address = new Address();
        address.setCity(dto.getCity());
        address.setLocality(dto.getLocality());
        address.setLandmark(dto.getLandmark());
        property.setAddress(address);

        Amenity amenity = new Amenity();
        amenity.setBathrooms(dto.getBathrooms());
        amenity.setBalcony(dto.getBalcony());
        amenity.setWaterSupply(dto.getWaterSupply());
        amenity.setPetAllowed(dto.getPetAllowed());
        amenity.setGym(dto.getGym());
        amenity.setNonVeg(dto.getNonVeg());
        amenity.setGatedSecurity(dto.getGatedSecurity());
        amenity.setShowProperty(dto.getShowProperty());
        amenity.setPropertyCondition(dto.getPropertyCondition());
        amenity.setSecondaryNumber(dto.getSecondaryNumber());
        amenity.setNearByPlace(dto.getNearByPlace());
        amenity.setLift(dto.getLift());
        amenity.setGasPipeLine(dto.getGasPipeLine());
        amenity.setAirConditioner(dto.getAirConditioner());
        amenity.setPark(dto.getPark());
        amenity.setHouseKeeping(dto.getHouseKeeping());
        amenity.setInternetService(dto.getInternetService());
        amenity.setPowerBackUp(dto.getPowerBackUp());
        amenity.setServentRoom(dto.getServentRoom());
        amenity.setSwimmingPool(dto.getSwimmingPool());
        amenity.setFireSafety(dto.getFireSafety());

        property.getAmenities().add(amenity);

        property.setOwner(user);

        return propertyRepository.save(property);
    }

//    public void addImage(MultipartFile[] file, Property property) throws IOException {
//        Photo photos;
//
//        for (MultipartFile multipartFile : file) {
//            photos = new Photo();
//
//            try {
//                photos.setImageData(multipartFile.getBytes());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            photos.setImageType(multipartFile.getContentType());
//            photos.setImageName(multipartFile.getOriginalFilename());
//
//            photos.setProperty(property);
//        }
//    }

    public void saveImage(Long propertyId, MultipartFile[] propertyImages) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        Photo photos;

        for (MultipartFile multipartFile : propertyImages) {
            photos = new Photo();

            try {
                photos.setImageData(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            photos.setImageType(multipartFile.getContentType());
            photos.setImageName(multipartFile.getOriginalFilename());

            photos.setProperty(property);

            property.getPhotos().add(photos);
        }

        propertyRepository.save(property);
    }
}