package com.example.ABC.Laboratories.Controller;


import com.example.ABC.Laboratories.Exception.ResourceNotFoundException;
import com.example.ABC.Laboratories.Model.appoinment;
import com.example.ABC.Laboratories.Repository.appoinmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import com.example.ABC.Laboratories.Model.doctor;


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

    @Autowired
    private JavaMailSender javaMailSender;

    // create appoinment rest api
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

    @PostMapping("/appointments/{id}/approve")
    public ResponseEntity<String> approveAppointment(@PathVariable Long id) {
        Optional<appoinment> optionalAppointment = appoinmentRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            appoinment appointment = optionalAppointment.get();
            appointment.setStatus("Approved"); // Assuming 'approved' flag in Appointment entity
            appoinmentRepo.save(appointment);

            sendEmailToPatient(appointment); // Pass the appointment object to sendEmailToPatient method
            return ResponseEntity.ok("Appointment approved successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void sendEmailToPatient(appoinment appointment) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Doctor Approved Your Appointment Successfully!");
        mailMessage.setTo(appointment.getMail());
        mailMessage.setFrom("docfinder.xyz@gmail.com");

        String emailContent = "Dear " + appointment.getU_name() + ",\n\n"
                + "We are pleased to inform you that your appointment has been approved.\n\n"
                + "Here are the details:\n" + "Doctor Name: " + appointment.getC_name() + "\n"
                + "Doctor Available Date: " + appointment.getTime() + "\n"
                + "If you have any concerns or need to make any changes, please don't hesitate to contact us.\n\n"
                + "Thank you for choosing ABC Laboratories.\n\n" + "Sincerely,\n" + "The ABC Laboratories Team";

        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);
    }

    @PostMapping("/appointments/{id}/reject")
    public ResponseEntity<String> rejectAppointment(@PathVariable Long id) {
        Optional<appoinment> optionalAppointment = appoinmentRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            appoinment appointment = optionalAppointment.get();
            appointment.setStatus("Rejected"); // Assuming a 'status' field in Appointment entity
            appoinmentRepo.save(appointment);

            sendRejectionEmailToPatient(appointment); // Pass the appointment object to sendRejectionEmailToPatient method
            return ResponseEntity.ok("Appointment rejected successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void sendRejectionEmailToPatient(appoinment appointment) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Appointment Rejected by Doctor");
        mailMessage.setTo(appointment.getMail());
        mailMessage.setFrom("docfinder.xyz@gmail.com");

        String emailContent = "Dear " + appointment.getU_name() + ",\n\n"
                + "We regret to inform you that your appointment has been rejected.\n\n"
                + "Here are the details:\n" + "Doctor Name: " + appointment.getC_name() + "\n"
                + "Appointment Date: " + appointment.getTime() + "\n"
                + "If you have any concerns or need further assistance, please feel free to contact us.\n\n"
                + "Thank you for considering ABC Laboratories.\n\n" + "Sincerely,\n" + "The ABC Laboratories Team";

        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);
    }

    @PostMapping("/consultant-name/{name}")
    public List<appoinment> filterByCounsultName(@PathVariable String name) {
        return appoinmentRepo.findByCounsultName(name);
    }
}
