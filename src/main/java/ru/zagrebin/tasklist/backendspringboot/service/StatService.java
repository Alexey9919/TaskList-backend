package ru.zagrebin.tasklist.backendspringboot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zagrebin.tasklist.backendspringboot.models.Stat;
import ru.zagrebin.tasklist.backendspringboot.repositories.StatRepository;


@Service
@Transactional
public class StatService {

    private final StatRepository repository;

    @Autowired
    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findById(Long id){
        return repository.findById(id).get();
    }


}
