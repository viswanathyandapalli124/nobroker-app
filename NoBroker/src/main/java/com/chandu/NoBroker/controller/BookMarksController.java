package com.chandu.NoBroker.controller;

import com.chandu.NoBroker.service.BookMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookMarksController {

    @Autowired
    private BookMarksService bookMarksService;

    @GetMapping("bookmarks/{userId}/{propertyId}/")
    public String getBookmarks(@PathVariable("userId") Long userId,@PathVariable("propertyId") Long propertyId, Model model) {
        bookMarksService.saveBookMarks(userId, propertyId);
        return  "hi";
    }
}
