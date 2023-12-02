package ru.zagrebin.tasklist.backendspringboot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zagrebin.tasklist.backendspringboot.models.Priority;
import ru.zagrebin.tasklist.backendspringboot.repositories.PriorityRepository;

import java.util.List;

@Service
@Transactional
public class PriorityService {

    private final PriorityRepository repository;

    @Autowired
    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<Priority> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Priority add(Priority priority) {
        return repository.save(priority);
    }

    public Priority update(Priority priority){
        return repository.save(priority);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Priority findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }

    public List<Priority> findByTitle(String text){
        return repository.findByTitle(text);
    }

}
