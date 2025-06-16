package com.Etiqa.TechAssessment;

import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TechAssessmentApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getBaseUrl(String path) {
		return "http://localhost:" + port + path;
	}

	private static Long customerId;
	private static Long productId;

	// ----------------- CUSTOMER TESTS ---------------------

	@Test
	@Order(1)
	void createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmailOffice("john.doe@office.com");
		customer.setEmailPersonal("john.doe@gmail.com");
		customer.setFamilyMembers(4);

		ResponseEntity<Customer> response = restTemplate.postForEntity(
				getBaseUrl("/api/customers/saveCustomer"),
				customer,
				Customer.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		customerId = response.getBody().getId();
	}

	@Test
	@Order(2)
	void getCustomerById() {
		ResponseEntity<Customer> response = restTemplate.getForEntity(
				getBaseUrl("/api/customers/" + customerId),
				Customer.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getFirstName()).isEqualTo("John");
	}

	@Test
	@Order(3)
	void updateCustomer() {
		Customer updated = new Customer();
		updated.setFirstName("Johnny");
		updated.setLastName("Doe");
		updated.setEmailOffice("johnny.doe@office.com");
		updated.setEmailPersonal("johnny.doe@gmail.com");
		updated.setFamilyMembers(5);

		HttpEntity<Customer> entity = new HttpEntity<>(updated);
		ResponseEntity<Customer> response = restTemplate.exchange(
				getBaseUrl("/api/customers/" + customerId),
				HttpMethod.PUT,
				entity,
				Customer.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getFirstName()).isEqualTo("Johnny");
	}

	@Test
	@Order(4)
	void deleteCustomer() {
		restTemplate.delete(getBaseUrl("/api/customers/" + customerId));

		ResponseEntity<String> response = restTemplate.getForEntity(
				getBaseUrl("/api/customers/" + customerId),
				String.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ----------------- PRODUCT TESTS ---------------------

	@Test
	@Order(5)
	void createProduct() {
		Product product = new Product();
		product.setBookTitle("Effective Java");
		product.setBookPrice(59.99);
		product.setBookQuantity(10);

		ResponseEntity<Product> response = restTemplate.postForEntity(
				getBaseUrl("/api/products"),
				product,
				Product.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		productId = response.getBody().getId();
	}

	@Test
	@Order(6)
	void getProductById() {
		ResponseEntity<Product> response = restTemplate.getForEntity(
				getBaseUrl("/api/products/" + productId),
				Product.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getBookTitle()).isEqualTo("Effective Java");
	}

	@Test
	@Order(7)
	void updateProduct() {
		Product updated = new Product();
		updated.setBookTitle("Clean Code");
		updated.setBookPrice(45.50);
		updated.setBookQuantity(20);

		HttpEntity<Product> entity = new HttpEntity<>(updated);
		ResponseEntity<Product> response = restTemplate.exchange(
				getBaseUrl("/api/products/" + productId),
				HttpMethod.PUT,
				entity,
				Product.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getBookTitle()).isEqualTo("Clean Code");
	}

	@Test
	@Order(8)
	void deleteProduct() {
		restTemplate.delete(getBaseUrl("/api/products/" + productId));

		ResponseEntity<String> response = restTemplate.getForEntity(
				getBaseUrl("/api/products/" + productId),
				String.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
