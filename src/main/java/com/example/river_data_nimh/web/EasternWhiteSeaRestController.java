package com.example.river_data_nimh.web;

import com.example.river_data_nimh.domain.EasternWhiteSeaBasin;
import com.example.river_data_nimh.services.EasternWhiteSeaBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/easternWhiteSeaBasin")
@RequiredArgsConstructor
@CrossOrigin("${host.url}")
public class EasternWhiteSeaRestController {
    private final EasternWhiteSeaBasinService easternWhiteSeaBasinService;

    @GetMapping
    public ResponseEntity<List<EasternWhiteSeaBasin>> getAllData(){
        return ResponseEntity.ok(easternWhiteSeaBasinService.findAll());
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<List<EasternWhiteSeaBasin>> getByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<EasternWhiteSeaBasin> easternWhiteSeaBasinList = easternWhiteSeaBasinService.findByLd(date);
        if (easternWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(easternWhiteSeaBasinList);
    }
    @GetMapping("/date/{startDate}/{endDate}")
    public ResponseEntity<List<EasternWhiteSeaBasin>> getByDateBetween(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EasternWhiteSeaBasin> easternWhiteSeaBasinList = easternWhiteSeaBasinService.findByLdBetween(startDate, endDate);
        if (easternWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(easternWhiteSeaBasinList);
    }

    @GetMapping("/numAndDate/{num}/{startDate}/{endDate}")
    public ResponseEntity<List<EasternWhiteSeaBasin>> getByNumAndDateBetween(@PathVariable("num") Integer num,
                                                                      @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EasternWhiteSeaBasin> easternWhiteSeaBasinList = easternWhiteSeaBasinService.findByNumStAndLdBetween(num, startDate, endDate);
        if (easternWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(easternWhiteSeaBasinList);
    }

    @GetMapping("/nameAndDate/{name}/{startDate}/{endDate}")
    public ResponseEntity<List<EasternWhiteSeaBasin>> getByNameAndDateBetween(@PathVariable("name") String name,
                                                                       @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                       @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EasternWhiteSeaBasin> easternWhiteSeaBasinList = easternWhiteSeaBasinService.findByNameAndLdBetween(name, startDate, endDate);
        if (easternWhiteSeaBasinList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(easternWhiteSeaBasinList);
    }
}
