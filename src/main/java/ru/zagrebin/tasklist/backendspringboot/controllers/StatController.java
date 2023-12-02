package ru.zagrebin.tasklist.backendspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zagrebin.tasklist.backendspringboot.models.Stat;
import ru.zagrebin.tasklist.backendspringboot.service.StatService;
import ru.zagrebin.tasklist.backendspringboot.util.MyLogger;


@RestController
public class StatController {

    private final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    private final Long defaultId = 1l; // l - чтобы тип числа был Long, иначе будет ошибка компиляции


    // для статистики всгда получаем только одну строку с id=1 (согласно таблице БД)
    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        MyLogger.showMethodName("StatController: findById() ......................................................... ");

        return  ResponseEntity.ok(statService.findById(defaultId));
    }


}
