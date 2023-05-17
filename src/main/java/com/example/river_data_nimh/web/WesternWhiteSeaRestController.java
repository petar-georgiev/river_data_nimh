package com.example.river_data_nimh.web;

import com.example.river_data_nimh.domain.WesternWhiteSeaBasin;
import com.example.river_data_nimh.services.WesternWhiteSeaBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/westernWhiteSeaBasin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081/")
public class WesternWhiteSeaRestController {
    private final WesternWhiteSeaBasinService westernWhiteSeaBasinService;

    @GetMapping
    public ResponseEntity<List<WesternWhiteSeaBasin>> getAllData(){
        return ResponseEntity.ok(westernWhiteSeaBasinService.findAll());
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<List<WesternWhiteSeaBasin>> getByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<WesternWhiteSeaBasin> westernWhiteSeaBasinList = westernWhiteSeaBasinService.findByLd(date);
        if (westernWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(westernWhiteSeaBasinList);
    }
    @GetMapping("/date/{startDate}/{endDate}")
    public ResponseEntity<List<WesternWhiteSeaBasin>> getByDateBetween(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                       @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<WesternWhiteSeaBasin> westernWhiteSeaBasinList = westernWhiteSeaBasinService.findByLdBetween(startDate, endDate);
        if (westernWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(westernWhiteSeaBasinList);
    }

    @GetMapping("/numAndDate/{num}/{startDate}/{endDate}")
    public ResponseEntity<List<WesternWhiteSeaBasin>> getByNumAndDateBetween(@PathVariable("num") Integer num,
                                                                             @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                             @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<WesternWhiteSeaBasin> westernWhiteSeaBasinList = westernWhiteSeaBasinService.findByNumStAndLdBetween(num, startDate, endDate);
        if (westernWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(westernWhiteSeaBasinList);
    }

    @GetMapping("/nameAndDate/{name}/{startDate}/{endDate}")
    public ResponseEntity<List<WesternWhiteSeaBasin>> getByNameAndDateBetween(@PathVariable("name") String name,
                                                                              @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                              @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<WesternWhiteSeaBasin> westernWhiteSeaBasinList = westernWhiteSeaBasinService.findByNameAndLdBetween(name, startDate, endDate);
        if (westernWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(westernWhiteSeaBasinList);
    }
}
