package com.applause.demo.controllers;

import com.applause.demo.dto.TesterDto;
import com.applause.demo.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/testers")
public class TesterController {

    @Autowired
    TesterService testerService;

    @GetMapping("/countries")
    public Set<String> getTestersCountries() {
        return testerService.getTestersCountries();
    }

    @GetMapping
    public List<TesterDto> getTestersBy(@PathParam("countries") String countries,
                                        @PathParam("deviceIds") String deviceIds) {
        List<String> countriesList = countries != null ? asList(countries.split(",")) : new ArrayList<>();
        Set<Integer> devices = deviceIds != null ? stream(deviceIds.split(",")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toSet()) : new HashSet<>();
        return testerService.getTestersBy(countriesList, devices);
    }

}
