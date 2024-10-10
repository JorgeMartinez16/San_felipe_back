package com.proyecto.san_felipe.Services;

import com.proyecto.san_felipe.Repository.WashRecordRepository;
import com.proyecto.san_felipe.entities.WashRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WashRecordService {

    @Autowired
    private WashRecordRepository washRecordRepository;

    public WashRecord registerWashRecord(WashRecord washRecord) {
        return washRecordRepository.save(washRecord);
    }

    public List<WashRecord> getAllWashRecord(){
        return washRecordRepository.findAll();
    }
    public List<WashRecord> getWashedByDate(Date date){
        return washRecordRepository.findByDate(date);
    }

}
