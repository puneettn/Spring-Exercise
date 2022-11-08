package com.ttn.SpringJpaHibernate2.nativesql;



 import com.ttn.SpringJpaHibernate2.nativesql.entity.Employee;
 import com.ttn.SpringJpaHibernate2.nativesql.repository.EmployeeRepository;
 import org.junit.jupiter.api.MethodOrderer;
 import org.junit.jupiter.api.Order;
 import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.annotation.Rollback;
 import org.springframework.test.context.junit.jupiter.SpringExtension;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestEntityManager
class SpringJpaHibernate2ApplicationTests {

	@Autowired
	EmployeeRepository repository;


	@Test
	void contextLoads() {
	}

	@Test
	@Order(value = 1)
	public void testCreate(){
		repository.save(new Employee(1,"Kunal","singh",32000,25));
		repository.save(new Employee(2,"Swapnil","Khanna",332000,19));
		repository.save(new Employee(3,"shyam","bisht",342000,22));
		repository.save(new Employee(4,"Aatif","Aslam",32000,25));
		repository.save(new Employee(5,"Piyush","gupta",332000,18));
		repository.save(new Employee(6,"Swagger","sharma",342000,44));

		repository.save(new Employee(7,"Monty","lala",32000,50));
		repository.save(new Employee(8,"Radhe","ram",332000,88));
		repository.save(new Employee(9,"Kammal","Hussain",342000,45));

	}

	@Test
	@Order(value = 2)
	public void testEmployeeByLastName(){

		List<Object[]> emp = repository.findEmployeeByLastName();
		for(Object[] e : emp){
			System.out.println(e[0] + " " + e[1] + " " + e[2]);
		}
	}


	@Test
	@Order(value=3)
	@Rollback(value = false)
	@Transactional
	public void testDeleteEmployeeByAge(){
		repository.findDeleteEmployeeByAge(45);
	}


}