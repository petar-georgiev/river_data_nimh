package com.example.river_data_nimh.web;

import com.example.river_data_nimh.domain.BlackSeaBasin;
import com.example.river_data_nimh.services.BlackSeaBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/blackSeaBasin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081/")
public class BlackSeaBasinRestController {
    private final BlackSeaBasinService blackSeaBasinService;

    @GetMapping
    public ResponseEntity<List<BlackSeaBasin>> getAllData() {
        return ResponseEntity.ok(blackSeaBasinService.findAll());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<BlackSeaBasin>> getByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BlackSeaBasin> blackSeaBasinList = blackSeaBasinService.findByLd(date);
        if (blackSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blackSeaBasinList);
    }

    @GetMapping("/date/{startDate}/{endDate}")
    public ResponseEntity<List<BlackSeaBasin>> getByDateBetween(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<BlackSeaBasin> blackSeaBasinList = blackSeaBasinService.findByLdBetween(startDate, endDate);
        if (blackSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blackSeaBasinList);
    }

    @GetMapping("/numAndDate/{num}/{startDate}/{endDate}")
    public ResponseEntity<List<BlackSeaBasin>> getByNumAndDateBetween(@PathVariable("num") Integer num,
                                                               @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                               @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<BlackSeaBasin> blackSeaBasinList = blackSeaBasinService.findByNumStAndLdBetween(num, startDate, endDate);
        if (blackSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blackSeaBasinList);
    }

    @GetMapping("/nameAndDate/{name}/{startDate}/{endDate}")
    public ResponseEntity<List<BlackSeaBasin>> getByNameAndDateBetween(@PathVariable("name") String name,
                                                                      @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<BlackSeaBasin> blackSeaBasinList = blackSeaBasinService.findByNameAndLdBetween(name, startDate, endDate);
        if (blackSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blackSeaBasinList);
    }
}
