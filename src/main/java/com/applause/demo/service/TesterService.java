package com.applause.demo.service;

import com.applause.demo.dto.TesterDto;

import java.util.List;
import java.util.Set;

public interface TesterService {

    List<TesterDto> getTestersBy(List<String> countries, Set<Integer> devicesIds);

    Set<String> getTestersCountries();
}
