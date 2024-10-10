package com.proyecto.san_felipe.Controllers;

import com.proyecto.san_felipe.Services.WashRecordService;
import com.proyecto.san_felipe.entities.WashRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/washed")
public class WashRecordController {

    @Autowired
    private WashRecordService washRecordService;

    @GetMapping
    public List<WashRecord> findAll(){
        return washRecordService.getAllWashRecord();
    }

    @PostMapping("/register")
    public ResponseEntity<WashRecord> registerWashRecord(@RequestBody WashRecord washRecord) {
        WashRecord savedWashRecord = washRecordService.registerWashRecord(washRecord);
        return new ResponseEntity<>(savedWashRecord, HttpStatus.CREATED);
    }

}

