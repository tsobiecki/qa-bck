package com.applause.demo.controllers;

import com.applause.demo.dto.TesterDto;
import com.applause.demo.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/testers")
public class TesterController {

    @Autowired
    TesterService testerService;

    @GetMapping("/countries")
    public Set<String> getTestersCountries() {
        return testerService.getTestersCountries();
    }

    @GetMapping
    public List<TesterDto> getTestersBy(@PathParam("countries") List<String> countries,
                                        @PathParam("deviceIds") Set<Integer> deviceIds) {
        return testerService.getTestersBy(countries, deviceIds);
    }

}
