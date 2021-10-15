package com.example.groupdemo.service;

import com.example.groupdemo.model.Driver;
import com.example.groupdemo.repository.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriverByID(Long id){
        return driverRepository.findById(id).get();
    }

    public void deleteDriverByID(Long id){
        driverRepository.deleteById(id);
    }

    public Driver updateDriverTelephoneNumberByID(Long id,String newTelephoneNumber){
        Driver driver = driverRepository.findById(id).get();
        driver.setTelephoneNumber(newTelephoneNumber);
        driverRepository.save(driver);
        return driver;
    }


}
