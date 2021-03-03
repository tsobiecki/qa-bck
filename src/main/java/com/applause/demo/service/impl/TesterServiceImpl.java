package com.applause.demo.service.impl;

import com.applause.demo.dto.TesterDto;
import com.applause.demo.entity.Tester;
import com.applause.demo.repository.BugRepository;
import com.applause.demo.repository.TesterRepository;
import com.applause.demo.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TesterServiceImpl implements TesterService {

    @Autowired
    private TesterRepository testerRepository;

    @Autowired
    private BugRepository bugRepository;

    @Override
    public List<TesterDto> getTestersBy(List<String> countries, Set<Integer> devicesIds) {
        List<Tester> testers = testerRepository.getTesters(countries);

        HashMap<Integer, Integer> bugs = bugRepository.getBugsBy(testers.stream().map(Tester::getId).collect(Collectors.toList()), devicesIds);

        List<TesterDto> testeritos = testers.stream().map(tester -> TesterDto.builder()
                .fullName(tester.getFirstName() + " " + tester.getLastName())
                .experience(bugs.get(tester.getId()))
                .build())
                .sorted(Comparator.comparingInt(TesterDto::getExperience).reversed())
                .collect(Collectors.toList());
        System.out.println(testeritos);
        return testeritos;
    }

    @Override
    public Set<String> getTestersCountries() {
        return testerRepository.getTestersCountries();
    }

}
