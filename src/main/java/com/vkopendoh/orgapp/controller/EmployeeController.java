package com.vkopendoh.orgapp.controller;

import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = EmployeeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Employee Management API", description = "Operations pertaining to manage employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    final static String REST_URL = "/rest/employee";

    @GetMapping("/{id}")
    public Employee get(@PathVariable int id) {
        return service.getFetch(id);
    }

    @GetMapping("/{id}/manager")
    public Employee getManager(@PathVariable int id) {
        return service.getManager(id);
    }

    @GetMapping("/department/{departmentId}/list")
    public List<Employee> getAll(@PathVariable int departmentId) {
        return service.getAll(departmentId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createWithLocation(@Valid @RequestBody Employee employee) throws Exception {
        Employee created = service.create(employee);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Employee employee, @PathVariable int id) throws Exception {
        service.update(employee, id);
    }

    @PatchMapping(value = "/{id}/retire", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee retire(@Valid @RequestParam LocalDate date, @PathVariable int id) throws Exception {
        return service.retire(date, id);
    }

    @PatchMapping(value = "/{id}/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee transfer(@Valid @RequestParam int departmentId, @PathVariable int id) {
        return service.transfer(departmentId, id);
    }

    @PatchMapping(value = "/department/{oldDepartmentId}/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> transferAll(@RequestParam int newId, @PathVariable int oldDepartmentId) {
        return service.transferAll(oldDepartmentId, newId);
    }


}
