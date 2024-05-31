package com.applause.demo;

import com.applause.demo.entity.Cell;
import com.applause.demo.repository.CellRepository;
import com.applause.demo.service.CellService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.applause.demo.entity.Cell.Radio;
import static com.applause.demo.entity.Cell.builder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CellServiceTest {

    @Autowired
    private CellService cellService;

    @Autowired
    private CellRepository cellRepository;

    @Test
    public void shouldFilterByRange() {
        int range = 1001;
        List<Cell> result = cellService.filterByRange(range, range);

        assertThat(result).usingElementComparatorOnFields("range").contains(cellWithRangeOnly(range));
    }

    @Test
    public void shouldNotContaintCellsOutOfRange() {
        int range = 1001;
        List<Cell> result = cellService.filterByRange(range, range);

        assertThat(result).usingElementComparatorOnFields("range").doesNotContain(cellWithRangeOnly(range + 1));
    }

    @Test
    public void shouldFilterByType() {
        Radio type = Radio.GSM;

        List<Cell> result = cellService.filterByList(Radio.GSM.name());

        assertThat(result).usingElementComparatorOnFields("radio").contains(Cell.builder().radio(type).build());
    }

    @Test
    public void shouldNotContainOtherTypeThanGiven() {
        List<Cell> result = cellService.filterByList(Radio.GSM.name());

        assertThat(result).usingElementComparatorOnFields("radio").doesNotContain(Cell.builder().radio(Radio.LTE).build());
    }

    private Cell cellWithRangeOnly(int range) {
        return builder().range(range).build();
    }
}
