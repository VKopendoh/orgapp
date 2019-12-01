package com.vkopendoh.orgapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrgappApplication implements CommandLineRunner {

   /* @Autowired
    CrudDepartmentRepository departmentRepository;

    @Autowired
    CrudEmployeeRepository employeeRepository;

    @Autowired
    CrudPositionRepository positionRepository;*/

    public static void main(String[] args) {
        SpringApplication.run(OrgappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      /*  Department dpt1 = new Department("dpt1", EmployeeUtils.LOCAL_DATE);
        Department dpt2 = new Department("dpt2", EmployeeUtils.LOCAL_DATE);
        Department dpt11 = new Department("dpt11", EmployeeUtils.LOCAL_DATE);
        Department dpt21 = new Department("dpt21", EmployeeUtils.LOCAL_DATE);

        Employee emp1 = new Employee("Иванов", "Иван", "Иванович", Sex.MALE, LocalDate.now(),
                "phone is 555", "ivan@mm", LocalDate.now(), LocalDate.now(), 100);
        Employee emp2 = new Employee("Петров", "Иван", "Иванович", Sex.MALE, LocalDate.now(),
                "phone is 777", "pet@mm", LocalDate.now(), LocalDate.now(), 200);
        Employee emp3 = new Employee("Сидорова", "Иван", "Иванович", Sex.FEMALE, LocalDate.now(),
                "phone is 888", "sid@mm", LocalDate.now(), LocalDate.now(), 300);

        dpt2.setParent(dpt1);
        dpt11.setParent(dpt1);
        dpt21.setParent(dpt2);

        //dpt1.setChildrens(Arrays.asList(dpt2,dpt11));
        dpt2.setChildrens(Arrays.asList(dpt21));
        dpt1.setChildrens(Arrays.asList(dpt2, dpt11));

        List<Department> ch = DepartmentUtils.getAllSubDepartments(dpt1, null);

        departmentRepository.save(dpt1);
        departmentRepository.save(dpt2);
        departmentRepository.save(dpt11);
        departmentRepository.save(dpt21);

        emp1.setDepartment(dpt1);
        emp2.setDepartment(dpt1);
        emp3.setDepartment(dpt1);

        emp2.setManager(true);
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);

        Department d = departmentRepository.findById(1).orElse(new Department("EMPTY", EmployeeUtils.LOCAL_DATE));
        System.out.println(d.getName());
        d.getChildrens().forEach(x -> System.out.println("\n" + x.getName()));
        System.out.println("\n\nDPT2:\n");
        Department d2 = departmentRepository.findById(2).orElse(new Department("EMPTY2", EmployeeUtils.LOCAL_DATE));
        System.out.println(d2.getName());
        d2.getChildrens().forEach(x -> System.out.println(x.getName()));

        System.out.println(DepartmentUtils.getTo(d));*/
    }
}
