package com.example.ABC.Laboratories.Controller;


import com.example.ABC.Laboratories.Model.appoinment;
import com.example.ABC.Laboratories.Repository.appoinmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/appoinment/")
public class appointmentController {
    @Autowired
    private appoinmentRepo appoinmentRepo;

    // create users rest api
    @PostMapping("/saveappoinment")
    public appoinment createUsers(@RequestBody appoinment appoinment) {
        return appoinmentRepo.save(appoinment);
    }
}
