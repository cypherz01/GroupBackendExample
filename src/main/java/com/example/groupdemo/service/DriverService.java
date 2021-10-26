package com.example.groupdemo.service;

import com.example.groupdemo.model.Driver;
import com.example.groupdemo.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void createDriver(Driver driver){
        driverRepository.save(driver);
    }

    public Optional<Driver> getDriverByID(Long id){
        return driverRepository.findById(id);
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
