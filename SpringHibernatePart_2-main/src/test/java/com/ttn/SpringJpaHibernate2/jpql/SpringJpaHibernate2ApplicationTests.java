package com.ttn.SpringJpaHibernate2.jpql;


import com.ttn.SpringJpaHibernate2.jpql.entity.Employee;
import com.ttn.SpringJpaHibernate2.jpql.repository.EmployeeRepository;
import com.ttn.SpringJpaHibernate2.jpql.repository.EmployeeSort;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestEntityManager
class SpringJpaHibernate2ApplicationTests {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	EmployeeSort employeeSort;
	


	@Test
	void contextLoads() {
	}

	@Test
	@Order(value = 1)
	public void testCreate(){
		repository.save(new Employee(1,"Puneet","Kataria",32000,25));
		repository.save(new Employee(2,"lalit","Kataria24",332000,253));
		repository.save(new Employee(3,"lalit2","mohann",342000,235));
		repository.save(new Employee(4,"abhishek1","Grag",32000,25));
		repository.save(new Employee(5,"mukesh","Lama",332000,253));
		repository.save(new Employee(6,"nitesh","kumaar",342000,234));

		repository.save(new Employee(7,"Hitesh","Deswal",32000,25));
		repository.save(new Employee(8,"Rajesh","yadav",332000,253));
		repository.save(new Employee(9,"Mumbai","Kumar",342000,235));

	}


	@Test
	@Order(value = 2)
	public void testPrint()
	{

	List<Employee> emp = repository.findEmployeeByAvgByempSalery();
	for(Employee e:emp){
		System.out.println(e);
	}


	}

	@Test
	@Order(value = 3)
	@Transactional
	public void testEmployeeUpdateBySalary(){
	Optional<Double> avg = repository.findAverageSalary();
		if(avg.isPresent()){
			repository.findEmployeeSalaryLessThenAVG(3000,avg.get());
		}

		System.out.println("Updated Records");
		for (Employee employee : repository.findAll()) {
			System.out.println(employee);
		}


  	}

	@Test
	@Order(value = 4)
	@Transactional
	public void testDeleteEmployeeWithMinSalary(){
		Optional<Double> minSal = repository.findMinSalary();
		if(minSal.isPresent()){
			repository.findAndDeleteEmpWithMinimumSal(minSal.get());

			for (Employee employee : repository.findAll()) {
				System.out.println(employee);
			}

		}

	}

}