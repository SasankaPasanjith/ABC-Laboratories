package com.example.ABC.Laboratories.Controller;

import com.example.ABC.Laboratories.Exception.ResourceNotFoundException;
import com.example.ABC.Laboratories.Model.doctor;
import com.example.ABC.Laboratories.Repository.doctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/consult/")
public class doctorController {

    @Autowired
    private doctorRepo consultRepository;

    // create doctor rest api
    @PostMapping("/saveconsult")
    public doctor createUsers(@RequestBody doctor consult) {
        return consultRepository.save(consult);
    }

    // get all doctors rest api
    @GetMapping("/getconsult")
    private List<doctor> getAllConsult() {
        return consultRepository.findAll();
    }

    // get doctor by Id rest api
    @GetMapping("/getconsult/{id}")
    public ResponseEntity<doctor> getconsultById(@PathVariable Long id) {
        doctor consult = consultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctors not exist with id : " + id));
        return ResponseEntity.ok(consult);
    }

    // update doctor rest api
    @PutMapping("/getconsult/{id}")
    public ResponseEntity<doctor> updateConsult(@PathVariable Long id, @RequestBody doctor consult) {
        doctor consult2 = consultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctors not exist with id : " + id));
        consult2.setName(consult.getName());
        consult2.setEmail(consult.getEmail());
        consult2.setStart_time(consult.getStart_time());
        consult2.setEnd_time(consult.getEnd_time());
        consult2.setNic(consult.getNic());
        consult2.setPassword(consult.getPassword());

        doctor updateConsult = consultRepository.save(consult2);
        return ResponseEntity.ok(updateConsult);
    }
}
