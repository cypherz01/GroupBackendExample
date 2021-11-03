package com.example.groupdemo.controller;

import com.example.groupdemo.model.Driver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GroupDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenDBInitilizedWith1Record1_whenGETPeople_shouldReturn1Item() throws Exception {
        final var mvcResult = mockMvc
                .perform(get("/drivers"))
                .andDo(print())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();

        List<Driver> peopleFromDB = objectMapper.readValue(contentAsString, new TypeReference<>(){});

        assertEquals(1, peopleFromDB.size());
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenPOSTPeople_shouldSaveAndReturn() throws Exception {
        var firstName = "tom";
        var lastName = "blogs";
        var id = 1l;
        var prefix = "Mrs";
        var telNumber= "012345";
        var address1 = "12 SAMPLE STREET";
        var address2 = "34 SAMPLE ROAD";
        var city = "SAMPLE CITY";
        var postCode = "123D4E";
        var carType = "BUICK";
        var engineSize = 500;
        var additionalDrivers = 1;
        var commercialPurposes = false;
        var outsideState = true;
        var dateRegistered = "10-02-2021";
        var vehicleValue = 4000;

        Driver driver = new Driver(id, prefix, firstName, lastName,  telNumber, address1, address2,
                city, postCode, carType, engineSize, additionalDrivers,
                commercialPurposes, outsideState,vehicleValue,dateRegistered);

        final var personAsJSON = objectMapper.writeValueAsString(driver);

        final var mvcResult = mockMvc
                .perform(post("/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsJSON)
                )
                .andDo(print())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();

        Driver savedDriver = objectMapper.readValue(contentAsString, Driver.class);

        assertAll(
                () -> assertNotNull(savedDriver.getId()),
                () -> assertEquals(firstName, savedDriver.getFirstName()),
                () -> assertEquals(lastName, savedDriver.getLastName())
        );
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenDELETEPeopleWithExistingID_shouldReturn200() throws Exception {

        var existingId= 1l;

        final var mvcResult = mockMvc
                .perform(delete("/drivers/delete?id=" + existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

//
//    @Test
//    @DirtiesContext
//    void givenDBInitilizedWith3Records_whenPUTPeopleWithExistingID_shouldReturnUpdatedPerson() throws Exception {
//        var firstName = "Robyn";
//        var lastName = "Thompson";
//        var existingId = 1L;
//        var prefix = "Miss";
//        var telNumber= "12345678910";
//        var address1 = "123";
//        var address2 = "Something Road";
//        var city = "Belfast";
//        var postCode = "BT123";
//        var carType = "Cabriolet";
//        var engineSize = "1000";
//        var additionalDrivers = "2";
//        var commercialPurposes = "Yes";
//        var outsideState = "Yes";
//        var dateRegistered = "10-02-2021";
//
//        Capstone person = new Capstone(existingId, prefix, firstName, lastName,  telNumber, address1, address2, city, postCode, carType, engineSize, additionalDrivers, commercialPurposes, outsideState, dateRegistered);
//        final var personAsJSON = objectMapper.writeValueAsString(person);
//
//        final var mvcResult = mockMvc
//                .perform(put("/capstone/customerDetails?id=1&newTelephoneNumber=" + telNumber)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(personAsJSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Capstone updatedPerson = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Capstone.class);
//
//        assertAll(
//
//                () -> assertEquals(telNumber, updatedPerson.getTelNumber()));
//
//    }
}
