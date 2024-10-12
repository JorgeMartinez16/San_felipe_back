package com.proyecto.san_felipe.Services;

import com.proyecto.san_felipe.Repository.ServiceOfferedRepository;
import com.proyecto.san_felipe.Repository.WashRecordRepository;
import com.proyecto.san_felipe.entities.ServiceOffered;
import com.proyecto.san_felipe.entities.WashRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WashRecordService {

    @Autowired
    private ServiceOfferedRepository serviceOfferedRepository;

    @Autowired
    private WashRecordRepository washRecordRepository;

    public WashRecord registerWashRecord(WashRecord washRecord) {
        return washRecordRepository.save(washRecord);
    }

    public List<WashRecord> getAllWashRecord() {
        return washRecordRepository.findAll();
    }

    public List<WashRecord> getWashRecordByCarAndTheRange(String car, Date starDate, Date endDate) {
        return washRecordRepository.findByCarAndDateBetween(car, starDate, endDate);
    }

    public List<WashRecord> getWashRecordByLicencePlate(String car) {
        return washRecordRepository.findByCar(car);
    }

    public List<WashRecord> getWashRecordByEmployeeAndDate(String employee, Date starDate, Date endDate) {
        return  washRecordRepository.findByEmployeeAndDateBetween(employee, starDate, endDate);
    }

    public double calculateEmployeePayment(String employee, Date startDate, Date endDate) {
        List<WashRecord> records = washRecordRepository.findByEmployeeAndDateBetween(employee, startDate, endDate);

        System.out.println("Registros encontrados: " + records.size());
        double totalPayment = 0;
        for (WashRecord record : records) {
            double servicePrice = Double.parseDouble(record.getServiceOffered());
            totalPayment += servicePrice;
        }
        return totalPayment * 0.35;
    }




}
