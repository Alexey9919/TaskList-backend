package ru.zagrebin.tasklist.backendspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zagrebin.tasklist.backendspringboot.models.Category;
import ru.zagrebin.tasklist.backendspringboot.search.CategorySearchValues;
import ru.zagrebin.tasklist.backendspringboot.service.CategoryService;
import ru.zagrebin.tasklist.backendspringboot.util.MyLogger;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping ("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public List<Category> findAll() {

        MyLogger.showMethodName("CategoryController: findAll() ...................................................... ");
        return categoryService.findAllByOrderByTitleAsc();
    }


    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){


        MyLogger.showMethodName("CategoryController: add() .......................................................... ");


        // проверка на обязательные параметры
        if (category.getId() != null && category.getId() != 0) {
            // id создается автоматически в БД (autoincrement)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }


        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category){

        MyLogger.showMethodName("CategoryController: update() ....................................................... ");


        // проверка на обязательные параметры
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        MyLogger.showMethodName("CategoryController: findById() ..................................................... ");


        Category category = null;

        // обрабатываем исключение и отправляем свой текст/статус
        try{
            category = categoryService.findById(id);
        }catch (NoSuchElementException e){ // если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return  ResponseEntity.ok(category);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("CategoryController: delete() ....................................................... ");


        try {
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); // отправляем статус 200
    }

    // поиск по любым параметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){

        MyLogger.showMethodName("CategoryController: search() ....................................................... ");


        // если вместо текста будет пусто или null, то вернутся все категории
        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getText()));
    }




}
