package com.vkopendoh.orgapp.service;

import com.vkopendoh.orgapp.model.History;
import com.vkopendoh.orgapp.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HistoryService {
    @Autowired
    HistoryRepository repository;

    public History save(History action){
        return repository.save(action);
    }
}
