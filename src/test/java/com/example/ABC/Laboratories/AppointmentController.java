package com.example.ABC.Laboratories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ABC.Laboratories.Model.appoinment;
import com.example.ABC.Laboratories.Repository.appoinmentRepo;

@SpringBootTest
public class AppointmentController {
    @Autowired
    appoinmentRepo appoinmentRepo;

    @Test
    public void testAppointmentSave() {
        appoinment appoinment = new appoinment();
        appoinment.setId(1L);
        appoinment.setC_name("Test Consultant");
        appoinment.setU_name("Test User");
        appoinment.setBook_date("17/03/2024");
        appoinment.setTime("7.00");
        appoinment.setContact("0775412455");
        appoinment.setStatus("Approved");
        appoinment.setMail("Test@gmail.com");
        appoinmentRepo.save(appoinment);
        assertNotNull(appoinmentRepo.findById(1L).get());
    }

    @Test
    public void testReadAllAppointment() {
        List<appoinment> list1 = appoinmentRepo.findAll();
        assertThat(list1).size().isGreaterThan(0);
    }

    @Test
    public void testDeleteAppointment() {
        appoinmentRepo.deleteById(2L);
        assertThat(appoinmentRepo.existsById(2L)).isFalse();
    }
}
