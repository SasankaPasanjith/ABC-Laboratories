package com.example.ABC.Laboratories.Controller;


import com.example.ABC.Laboratories.Exception.ResourceNotFoundException;
import com.example.ABC.Laboratories.Model.appoinment;
import com.example.ABC.Laboratories.Repository.appoinmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    // get all appoinment rest api
    @GetMapping("/getappoinment")
    private List<appoinment> getAllConsult() {
        return appoinmentRepo.findAll();
    }

    // delete appoinment rest api
    @DeleteMapping("/getappoinment/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteConsult(@PathVariable Long id) {
        appoinment appoinment = appoinmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("appoinment not exist with id : " + id));

        appoinmentRepo.delete(appoinment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
