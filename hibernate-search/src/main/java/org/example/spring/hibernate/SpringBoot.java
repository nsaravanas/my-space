package org.example.spring.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.lucene.search.Query;
import org.example.spring.hibernate.model.Address;
import org.example.spring.hibernate.model.Employee;
import org.example.spring.hibernate.repository.EmployeeRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot implements CommandLineRunner {

	// @Autowired
	// private EntityManagerFactory factory;

	@Autowired
	DataSource dataSource;

	@Autowired
	private EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		System.out.println(dataSource);
		Employee e1 = createEmployee1();
		Employee e2 = createEmployee2();
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		// this.factory.createEntityManager().persist(e1);
		// this.factory.createEntityManager().persist(e2);
		List<Employee> e = this.repository.save(employees);
		System.out.println(e);
		System.err.println("Begin Hibernate Search...");
		// hibernateSearch();
		System.err.println("End Hibernate Search...");
		System.out.println(this.repository.findByHobbiesIn(Arrays.asList("reading")));

		System.out.println("waiting...");
	}

	private Employee createEmployee2() {

		Employee e = new Employee();
		e.setAge(26);
		e.setFirstName("Saravana");
		e.setLastName("N");
		e.setHobbies(Arrays.asList("reading", "sleeping"));
		e.setSince(LocalDate.now());

		Address a1 = new Address();
		a1.setCountry("Germany");
		a1.setState("Munich");
		a1.setZipCode(59241);
		a1.setStreet("Strabasef");
		a1.setEmployee(e);

		Address a2 = new Address();
		a2.setCountry("Singapore");
		a2.setState("KL");
		a2.setZipCode(15248);
		a2.setStreet("New Street");
		a2.setEmployee(e);

		List<Address> addresses = new ArrayList<Address>();
		addresses.add(a1);
		addresses.add(a2);
		e.setAddresses(addresses);

		return e;
	}

	private Employee createEmployee1() {
		Employee e = new Employee();
		e.setHobbies(Arrays.asList("reading"));
		e.setAge(26);
		e.setFirstName("Saravanakumar");
		e.setLastName("Nagarajan");
		e.setSince(LocalDate.now());

		Address a1 = new Address();
		a1.setCountry("India");
		a1.setState("Tamilnadu");
		a1.setZipCode(600100);
		a1.setStreet("Pillaiyar Kovil Street");
		a1.setEmployee(e);

		Address a2 = new Address();
		a2.setCountry("IND");
		a2.setState("TN");
		a2.setZipCode(600001);
		a2.setStreet("Broad Way");
		a2.setEmployee(e);

		List<Address> addresses = new ArrayList<Address>();
		addresses.add(a1);
		addresses.add(a2);
		e.setAddresses(addresses);

		return e;
	}

	// private void hibernateSearch() {
	// EntityManager entityManager = factory.createEntityManager();
	// FullTextEntityManager fullTextEntityManager =
	// Search.getFullTextEntityManager(entityManager);
	// entityManager.getTransaction().begin();
	// QueryBuilder queryBuilder =
	// fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Employee.class).get();
	// Query luceneQuery = queryBuilder.keyword().onFields("firstName",
	// "lastName", "addresses.street").matching("Singapore").createQuery();
	// javax.persistence.Query jpaQuery =
	// fullTextEntityManager.createFullTextQuery(luceneQuery, Employee.class);
	// @SuppressWarnings("unchecked")
	// List<Employee> result = jpaQuery.getResultList();
	// System.out.println(result);
	// entityManager.getTransaction().commit();
	// entityManager.close();
	// }
}
