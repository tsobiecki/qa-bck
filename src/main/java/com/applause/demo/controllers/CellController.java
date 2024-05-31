package com.applause.demo.controllers;

import com.applause.demo.entity.Cell;
import com.applause.demo.service.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cells")
public class CellController {

    @Autowired
    private CellService cellService;

    @GetMapping("/range")
    public List<Cell> getCellsByRange(@PathParam("from") String from,
                                      @PathParam("to") String to) {
        try {
            return cellService.filterByRange(parseInt(from), parseInt(to));
        } catch (NumberFormatException e) {
            return emptyList();
        }
    }

    @GetMapping("/list")
    public List<Cell> getCellsByList(@PathParam("type") String type) {
        return cellService.filterByList(type);

    }
}
