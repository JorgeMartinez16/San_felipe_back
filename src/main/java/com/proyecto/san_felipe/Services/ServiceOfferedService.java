package com.proyecto.san_felipe.Services;

import com.proyecto.san_felipe.Repository.ServiceOfferedRepository;
import com.proyecto.san_felipe.entities.ServiceOffered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfferedService {

    @Autowired
    public ServiceOfferedRepository serviceOfferedRepository;


    public ServiceOffered registerServiceOffered(ServiceOffered serviceOffered){
        return serviceOfferedRepository.save(serviceOffered);
    }

    public List<ServiceOffered> getALlServicesOffered(){
        return serviceOfferedRepository.findAll();
    }
}
