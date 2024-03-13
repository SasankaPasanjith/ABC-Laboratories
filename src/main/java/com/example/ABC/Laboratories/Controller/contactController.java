package com.example.ABC.Laboratories.Controller;


import com.example.ABC.Laboratories.Model.contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.ABC.Laboratories.Model.contact;
import com.example.ABC.Laboratories.Repository.contactRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/contact/")
public class contactController {
    @Autowired
    private contactRepo contactRepo;

    // create users rest api
    @PostMapping("/savecontact")
    public contact createContact(@RequestBody contact contact) {
        return contactRepo.save(contact);
    }

    // get all Users rest api
    @GetMapping("/getContact")
    private List<contact> getAllContact() {
        return contactRepo.findAll();
    }
}
