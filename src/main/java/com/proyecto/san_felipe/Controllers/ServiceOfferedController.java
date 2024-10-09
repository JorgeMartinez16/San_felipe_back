package com.proyecto.san_felipe.Controllers;


import com.proyecto.san_felipe.Repository.ServiceOfferedRepository;
import com.proyecto.san_felipe.Services.ServiceOfferedService;
import com.proyecto.san_felipe.entities.Employee;
import com.proyecto.san_felipe.entities.ServiceOffered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceOfferedController {

    @Autowired
    public ServiceOfferedService serviceOfferedService;

    @GetMapping
    public List<ServiceOffered> findAll(){
        return serviceOfferedService.getALlServicesOffered();
    }

    @PostMapping("/register")
    public ResponseEntity<ServiceOffered> registerServiceOffered(@RequestBody ServiceOffered serviceOffered) {
        ServiceOffered savedServiceOffered = serviceOfferedService.registerServiceOffered(serviceOffered);
        return new ResponseEntity<>(savedServiceOffered, HttpStatus.CREATED);
    }

}
