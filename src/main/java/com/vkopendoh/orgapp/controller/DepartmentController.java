package com.vkopendoh.orgapp.controller;

import com.vkopendoh.orgapp.service.DepartmentService;
import com.vkopendoh.orgapp.to.DepartmentTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = DepartmentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {
    static final String REST_URL = "/rest/departments";
    @Autowired
    DepartmentService service;

    @GetMapping
    public Set<DepartmentTo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentTo get(@PathVariable int id) {
        return service.getTo(id);
    }

    @GetMapping("/{id}/children")
    public Set<DepartmentTo> getChildren(@PathVariable int id) {
        return service.getChildren(id);
    }

    @GetMapping("/{id}/subs")
    public Set<DepartmentTo> getAllSubs(@PathVariable int id) {
        return service.getAllSubDepartmens(id);
    }

    @GetMapping("/{id}/parents")
    public Set<DepartmentTo> getParents(@PathVariable int id) {
        return service.getAllParentDepartmens(id);
    }

    @GetMapping("/name")
    public DepartmentTo getByName(@RequestParam String name) {
        return service.getToByName(name);
    }

}



/*
@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/profile/meals";

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<MealTo> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalTime startTime,
            @RequestParam @Nullable LocalDate endDate,
            @RequestParam @Nullable LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}*/
