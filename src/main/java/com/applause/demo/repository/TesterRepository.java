package com.applause.demo.repository;

import com.applause.demo.entity.Tester;

import java.util.List;
import java.util.Set;

public interface TesterRepository {

    List<Tester> getTesters(List<String> countries);

    Set<String> getTestersCountries();

}
