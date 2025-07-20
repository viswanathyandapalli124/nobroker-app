package com.chandu.NoBroker.service;

import com.chandu.NoBroker.model.Property;
import com.chandu.NoBroker.model.User;
import com.chandu.NoBroker.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class BookMarksService {

    private final UserService userService;
    private final PropertyRepository propertyRepository;

    public BookMarksService(UserService userService, PropertyRepository propertyRepository) {
        this.userService = userService;
        this.propertyRepository = propertyRepository;
    }

    public void saveBookMarks(Long userId, Long propertyId) {
        User user = userService.findUserById(userId);
        Property property = propertyRepository.findById(propertyId).orElse(null);

        user.getBookmarkedProperties().add(property);
        property.getBookmarkedByUsers().add(user);

        propertyRepository.save(property);
    }
}