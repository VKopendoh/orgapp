package com.vkopendoh.orgapp.controller;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Payroll;
import com.vkopendoh.orgapp.service.DepartmentService;
import com.vkopendoh.orgapp.to.DepartmentTo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(value = DepartmentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Department Management API", description = "Operations pertaining to manage departments")
public class DepartmentController {

    static final String REST_URL = "/rest/department";
    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

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

    @GetMapping(value = "/{id}/payroll")
    public Payroll getPayroll(@PathVariable int id) {
        return service.getPayroll(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}/set/name", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void rename(@RequestParam String newName, @PathVariable int id) {
        service.rename(newName, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> createWithLocation(@RequestBody Department department) {
        Department created = service.create(department);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/set/parent")
    @ResponseStatus(value = HttpStatus.OK)
    public void setParent(@RequestParam Integer parentId, @PathVariable int id) {
        service.setParent(parentId, id);
    }


}


