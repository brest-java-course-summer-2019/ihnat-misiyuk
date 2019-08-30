package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Rental;
import com.epam.brest.summer.courses2019.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class CarRestControllerTest {

    @Autowired
    private RentalRestController controller;

    @Autowired
    private RentalService service;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void after() {
        Mockito.reset(service);
    }

    @Test
    public void cars() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(createRentalFixture(0), createRentalFixture(1)));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/rentals")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentalRate", Matchers.is("def0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentalId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rentalRate", Matchers.is("def1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rentalId", Matchers.is(1)))
        ;

        Mockito.verify(service).findAll();
    }

    private Rental createRentalFixture(int index) {
        Rental rental = new Rental();
        rental.setRentalRate("def" + index);
        rental.setRentalId(index);
        //нужны ли остальные поля
        return rental;
    }

    @Test
    public void shouldPersistRental() throws Exception {

        Rental expectedRental = createRentalFixture(3);

        Rental inputRental = new Rental();
        inputRental.setRentalRate(expectedRental.getRentalRate());

        String json = new ObjectMapper().writeValueAsString(inputRental);

        Mockito.when(service.add(any(Rental.class))).thenReturn(expectedRental);

        MockHttpServletResponse response = mockMvc.perform(
                post("/rental")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        String content = response.getContentAsString();
        Rental result = objectMapper.readValue(content, Rental.class);
        assertEquals(expectedRental.getRentalRate(), result.getRentalRate());
        assertEquals(expectedRental.getRentalId(), result.getRentalId());
    }

    @Test
    public void shouldUpdateRental() throws Exception {
        Rental rental = createRentalFixture(1);
        String json = new ObjectMapper().writeValueAsString(rental);

        mockMvc.perform(put("/rental")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());
    }
}