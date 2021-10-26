package com.example.groupdemo.controller;

import com.example.groupdemo.model.Driver;
import com.example.groupdemo.service.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @PostMapping("/drivers")
    void createDriver(@RequestBody Driver driver){
        service.createDriver(driver);
    }

    @GetMapping("/drivers")
    Optional<Driver> getDriver(@RequestParam("id") Long id){
        return service.getDriverByID(id);
    }

    @PutMapping("/drivers/update")
    Driver updateDriver(@RequestParam("id") Long id,
                        @RequestParam("newTelephoneNumber") String newTelephoneNumber){
        return service.updateDriverTelephoneNumberByID(id,newTelephoneNumber);
    }

    @DeleteMapping("/drivers/delete")
    void deleteDriver(@RequestParam("id") Long id){
        service.deleteDriverByID(id);
    }
}
