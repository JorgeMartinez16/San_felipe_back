package com.proyecto.san_felipe.Repository;

import com.proyecto.san_felipe.entities.WashRecord;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface WashRecordRepository extends MongoRepository<WashRecord, Date> {
    List<WashRecord> findByDate(Date date);

}
