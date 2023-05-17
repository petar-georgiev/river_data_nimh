package com.example.river_data_nimh.web;

import com.example.river_data_nimh.domain.DunabeBasin;
import com.example.river_data_nimh.services.DunabeBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dunabeBasin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081/")
public class DunabeRestController {
    private final DunabeBasinService dunabeBasinService;

    @GetMapping
    public ResponseEntity<List<DunabeBasin>> getAllData(){
        return ResponseEntity.ok(dunabeBasinService.findAll());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<DunabeBasin>> getByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DunabeBasin> dunabeBasinList = dunabeBasinService.findByLd(date);
        if (dunabeBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dunabeBasinList);
    }
    @GetMapping("/date/{startDate}/{endDate}")
    public ResponseEntity<List<DunabeBasin>> getByDateBetween(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DunabeBasin> dunabeBasinList = dunabeBasinService.findByLdBetween(startDate, endDate);
        if (dunabeBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dunabeBasinList);
    }

    @GetMapping("/numAndDate/{num}/{startDate}/{endDate}")
    public ResponseEntity<List<DunabeBasin>> getByNumAndDateBetween(@PathVariable("num") Integer num,
                                                                      @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DunabeBasin> dunabeBasinList = dunabeBasinService.findByNumStAndLdBetween(num, startDate, endDate);
        if (dunabeBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dunabeBasinList);
    }

    @GetMapping("/nameAndDate/{name}/{startDate}/{endDate}")
    public ResponseEntity<List<DunabeBasin>> getByNameAndDateBetween(@PathVariable("name") String name,
                                                                       @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                       @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DunabeBasin> dunabeBasinList = dunabeBasinService.findByNameAndLdBetween(name, startDate, endDate);
        if (dunabeBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dunabeBasinList);
    }
}
