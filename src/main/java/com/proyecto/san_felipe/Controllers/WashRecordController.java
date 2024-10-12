package com.proyecto.san_felipe.Controllers;

import com.proyecto.san_felipe.Services.WashRecordService;
import com.proyecto.san_felipe.entities.Car;
import com.proyecto.san_felipe.entities.WashRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/washed")
public class WashRecordController {

    @Autowired
    private WashRecordService washRecordService;

    @GetMapping
    public List<WashRecord> findAll() {
        return washRecordService.getAllWashRecord();
    }

    @PostMapping("/register")
    public ResponseEntity<WashRecord> registerWashRecord(@RequestBody WashRecord washRecord) {
        WashRecord savedWashRecord = washRecordService.registerWashRecord(washRecord);
        return new ResponseEntity<>(savedWashRecord, HttpStatus.CREATED);
    }

    @GetMapping("/byCarAndDateRange")
    public ResponseEntity<List<WashRecord>> getWashRecordByCarAndTheRange(
            @RequestParam String car,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate
    ) {

        List<WashRecord> records = washRecordService.getWashRecordByCarAndTheRange(
                car, startDate, endDate
        );
        return ResponseEntity.ok(records);
    }

    @GetMapping("/licence-plate")
    public ResponseEntity<List<WashRecord>> getWashRecordByLicencePLate(@RequestParam String car){
        List<WashRecord> records = washRecordService.getWashRecordByLicencePlate(car);
        return  ResponseEntity.ok(records);
    };


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("El controlador está funcionando");
    }

}

