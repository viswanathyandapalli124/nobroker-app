package com.chandu.NoBroker.service;

import com.chandu.NoBroker.DTO.AllPostDTO;
import com.chandu.NoBroker.DTO.FullPostDTO;
import com.chandu.NoBroker.DTO.PropertyDetailsDTO;
import com.chandu.NoBroker.model.*;
import com.chandu.NoBroker.repository.*;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private Cloudinary cloudinary;

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
        property.setNegotiation(true);

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

        property.setAmenity(amenity);

        property.setOwner(user);

        return propertyRepository.save(property);
    }

    public void saveImage(Long propertyId, MultipartFile[] propertyImages) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property == null) return;

        for (MultipartFile multipartFile : propertyImages) {
            try {
                // Upload to Cloudinary
                Map<?, ?> uploadResult = cloudinary.uploader().upload(
                        multipartFile.getBytes(),
                        ObjectUtils.emptyMap()
                );

                String imageUrl = uploadResult.get("secure_url").toString();

                Photo photo = new Photo();
                photo.setImageUrl(imageUrl);
                photo.setProperty(property);

                photosRepository.save(photo);

            } catch (IOException e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }
    }


    public List<Property> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        Set<AllPostDTO> allPostDTOS = new HashSet<>();

        for(Property property : properties) {
            AllPostDTO allPostDTO = new AllPostDTO();
            allPostDTO.setTitle(property.getBhkType() + " BHK " + property.getPropertyType() + " In " +" "+
                    property.getPropertyName() +" For " + ((property.getIsSale())? "Sale" : "Rent") +
                    " in " + property.getAddress().getLocality());

            allPostDTO.setDescription(property.getAddress().getLandmark()+ " " +
                    property.getAddress().getLocality() + " " + property.getAddress().getCity());

            allPostDTO.setPrice(0L);
            allPostDTO.setPricePerSqft((long)(0 / property.getBuildUpArea()));

            allPostDTO.setBuildUpAre(property.getBuildUpArea());
            allPostDTO.setFacing(property.getFacing());
            allPostDTO.setBhkType(property.getBhkType());
            allPostDTO.setBathrooms(property.getAmenity().getBathrooms());
            allPostDTO.setParking(property.getParking());

            allPostDTO.setImages(property.getPhotos());

            allPostDTOS.add(allPostDTO);
        }

        return properties;
    }

    public FullPostDTO getPropertyById(Long propertyId) {

        Property property =  propertyRepository.findById(propertyId).orElse(null);

        FullPostDTO fullPostDTO = new FullPostDTO();

        fullPostDTO.setTitle(property.getBhkType() + "BHK Flat in " + property.getPropertyName() + "For Rent " + property.getAddress().getLandmark());
        fullPostDTO.setAddress(property.getAddress().getLandmark()+ " " +
                property.getAddress().getLocality() + " " + property.getAddress().getCity());

        fullPostDTO.setPrice(property.getPrice());
        fullPostDTO.setIsSale(property.getIsSale());
        fullPostDTO.setBuildUpArea(property.getBuildUpArea());
        fullPostDTO.setExpectedDeposit(property.getExceptedDeposit());
        fullPostDTO.setBedroom(property.getAmenity().getBathrooms());
        fullPostDTO.setPropertyType(property.getPropertyType());
        fullPostDTO.setFloor(property.getFloor());
        fullPostDTO.setAvailabilityFrom(property.getAvailableFrom());
        fullPostDTO.setParking(property.getParking());
        fullPostDTO.setPropertyAge(property.getPropertyAge());
        fullPostDTO.setBalcony(property.getAmenity().getBalcony());
        fullPostDTO.setCreatedAt(property.getCreatedAt());

        fullPostDTO.setFurnishing(property.getFurnishing());
        fullPostDTO.setFacing(property.getFacing());
        fullPostDTO.setWaterSupply(property.getAmenity().getWaterSupply());
        fullPostDTO.setTotalFloors(property.getTotalFloors());
        fullPostDTO.setBathrooms(property.getAmenity().getBathrooms());
        fullPostDTO.setPetAllowed(property.getAmenity().getPetAllowed());
        fullPostDTO.setNonVegAllowed(property.getAmenity().getNonVeg());
        fullPostDTO.setGatedSecurity(property.getAmenity().getGatedSecurity());

        fullPostDTO.setDescription(property.getDescription());
        fullPostDTO.setLift(property.getAmenity().getLift());
        fullPostDTO.setGasPipeLine(property.getAmenity().getGasPipeLine());
        fullPostDTO.setAirConditioner(property.getAmenity().getAirConditioner());
        fullPostDTO.setPark(property.getAmenity().getPark());
        fullPostDTO.setHouseKeeping(property.getAmenity().getHouseKeeping());
        fullPostDTO.setInternetService(property.getAmenity().getInternetService());
        fullPostDTO.setPowerBackUp(property.getAmenity().getPowerBackUp());
        fullPostDTO.setServentRoom(property.getAmenity().getServentRoom());
        fullPostDTO.setSwimmingPool(property.getAmenity().getSwimmingPool());
        fullPostDTO.setFireSafety(property.getAmenity().getFireSafety());
        fullPostDTO.setImages(property.getPhotos());

        return fullPostDTO;
    }

    public Page<Property> getPaginatedProperties(Pageable pageable) {

        Page<Property> propertyPage = propertyRepository.findAll(pageable);

        return propertyPage;
    }
}