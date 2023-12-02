package ru.zagrebin.tasklist.backendspringboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zagrebin.tasklist.backendspringboot.models.Stat;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

}
