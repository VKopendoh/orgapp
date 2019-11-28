package com.vkopendoh.orgapp;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.repository.CrudDepartmentRepository;
import com.vkopendoh.orgapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OrgappApplication implements CommandLineRunner {

	@Autowired
	CrudDepartmentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(OrgappApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Department dpt1 = new Department("dpt1", EmployeeUtils.LOCAL_DATE);
		Department dpt2 = new Department("dpt2", EmployeeUtils.LOCAL_DATE);
		Department dpt11 = new Department("dpt11", EmployeeUtils.LOCAL_DATE);
		Department dpt21 = new Department("dpt21", EmployeeUtils.LOCAL_DATE);

		dpt2.setParent(dpt1);
		dpt11.setParent(dpt1);
		dpt21.setParent(dpt2);

		//dpt1.setChildrens(Arrays.asList(dpt2,dpt11));

		repository.save(dpt1);
		repository.save(dpt2);
		repository.save(dpt11);
		repository.save(dpt21);

		Department  d = repository.findById(1).orElse(new Department("EMPTY", EmployeeUtils.LOCAL_DATE));
		System.out.println(d.getName());
		d.getChildrens().forEach(x-> System.out.println("\n" + x.getName()));
		System.out.println("\n\nDPT2:\n");
		Department  d2 = repository.findById(2).orElse(new Department("EMPTY2", EmployeeUtils.LOCAL_DATE));
		System.out.println(d2.getName());
		d2.getChildrens().forEach(x-> System.out.println(x.getName()));




	}
}
