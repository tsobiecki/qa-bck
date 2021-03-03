package com.applause.demo;

import com.applause.demo.dto.TesterDto;
import com.applause.demo.entity.Tester;
import com.applause.demo.repository.BugRepository;
import com.applause.demo.repository.TesterRepository;
import com.applause.demo.service.TesterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TesterServiceTest {

    @Autowired
    private TesterService testerService;

    @MockBean
    private TesterRepository testerRepository;
    @MockBean
    private BugRepository bugRepository;

    @Test
    public void shouldReturnListOfTesterCountries() {
        List<String> usersCountries = asList("US", "GB", "JP", "US", "PL", "DE");
        Set<String> expectedCountries = new HashSet<>(usersCountries);

        when(testerRepository.getTestersCountries()).thenReturn(expectedCountries);

        Set<String> result = testerRepository.getTestersCountries();

        assertThat(result).containsAll(expectedCountries);
    }

    @Test
    public void shouldReturnTestersInExperienceOrderForGivenCountries() {
        Integer firstUserId = 1;
        Integer secondUserId = 2;
        Integer thirdUserId = 3;
        List<String> usersCountries = asList("US", "PL", "DE");
        Iterable<TesterDto> expectedTesters = asList(createTestTester("Tester 1", 3),
                createTestTester("Tester 2", 2),
                createTestTester("Tester 3", 1));
        Tester firstTester = Tester.builder().id(1).firstName("Tester").lastName("1").build();
        Tester secondTester = Tester.builder().id(2).firstName("Tester").lastName("2").build();
        Tester thirdTester = Tester.builder().id(3).firstName("Tester").lastName("3").build();

        when(testerRepository.getTesters(usersCountries)).thenReturn(asList(firstTester, secondTester, thirdTester));

        when(bugRepository.getBugsBy(asList(1, 2, 3), Collections.emptySet())).thenReturn(new HashMap<>() {{
            put(thirdUserId, 1);
            put(firstUserId, 3);
            put(secondUserId, 2);
        }});

        List<TesterDto> result = testerService.getTestersBy(usersCountries, Collections.emptySet());

        assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedTesters);
    }

    @Test
    public void shouldReturnTestersInExperienceOrderForGivenDevices() {
        Integer firstUserId = 1;
        Integer secondUserId = 2;
        Integer thirdUserId = 3;
        Set<Integer> devicesIds = new HashSet<>(asList(1, 2, 3));
        Iterable<TesterDto> expectedTesters = asList(createTestTester("Tester 1", 3),
                createTestTester("Tester 2", 2),
                createTestTester("Tester 3", 1));
        Tester firstTester = Tester.builder().id(1).firstName("Tester").lastName("1").build();
        Tester secondTester = Tester.builder().id(2).firstName("Tester").lastName("2").build();
        Tester thirdTester = Tester.builder().id(3).firstName("Tester").lastName("3").build();

        when(testerRepository.getTesters(Collections.emptyList())).thenReturn(asList(firstTester, secondTester, thirdTester));

        when(bugRepository.getBugsBy(asList(1, 2, 3), devicesIds)).thenReturn(new HashMap<>() {{
            put(thirdUserId, 1);
            put(firstUserId, 3);
            put(secondUserId, 2);
        }});

        List<TesterDto> result = testerService.getTestersBy(Collections.emptyList(), devicesIds);

        assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedTesters);
    }

    @Test
    public void shouldReturnTestersInExperienceOrderForGivenCountriesAndDevices() {
        Integer firstUserId = 1;
        Integer secondUserId = 2;
        Integer thirdUserId = 3;
        List<String> usersCountries = asList("US", "PL", "DE");
        Set<Integer> devicesIds = new HashSet<>(asList(1, 2, 3));
        Iterable<TesterDto> expectedTesters = asList(createTestTester("Tester 1", 3),
                createTestTester("Tester 2", 2),
                createTestTester("Tester 3", 1));
        Tester firstTester = Tester.builder().id(1).firstName("Tester").lastName("1").build();
        Tester secondTester = Tester.builder().id(2).firstName("Tester").lastName("2").build();
        Tester thirdTester = Tester.builder().id(3).firstName("Tester").lastName("3").build();

        when(testerRepository.getTesters(usersCountries)).thenReturn(asList(firstTester, secondTester, thirdTester));

        when(bugRepository.getBugsBy(asList(1, 2, 3), devicesIds)).thenReturn(new HashMap<>() {{
            put(thirdUserId, 1);
            put(firstUserId, 3);
            put(secondUserId, 2);
        }});

        List<TesterDto> result = testerService.getTestersBy(usersCountries, devicesIds);

        assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedTesters);
    }

    @Test
    public void shouldReturnTestersInExperienceOrderForNoGivenCriteria() {
        Integer firstUserId = 1;
        Integer secondUserId = 2;
        Integer thirdUserId = 3;
        List<TesterDto> expectedTesters = asList(createTestTester("Tester 1", 3),
                createTestTester("Tester 2", 2),
                createTestTester("Tester 3", 1));
        Tester firstTester = Tester.builder().id(firstUserId).firstName("Tester").lastName("1").build();
        Tester secondTester = Tester.builder().id(secondUserId).firstName("Tester").lastName("2").build();
        Tester thirdTester = Tester.builder().id(thirdUserId).firstName("Tester").lastName("3").build();

        when(testerRepository.getTesters(Collections.emptyList())).thenReturn(asList(firstTester, secondTester, thirdTester));

        when(bugRepository.getBugsBy(asList(1, 2, 3), Collections.emptySet())).thenReturn(new HashMap<>() {{
            put(thirdUserId, 1);
            put(firstUserId, 3);
            put(secondUserId, 2);
        }});

        List<TesterDto> result = testerService.getTestersBy(Collections.emptyList(), Collections.emptySet());

        assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedTesters);
    }

    private TesterDto createTestTester(String fullName, Integer experience) {
        return TesterDto.builder()
                .fullName(fullName)
                .experience(experience)
                .build();
    }
}
