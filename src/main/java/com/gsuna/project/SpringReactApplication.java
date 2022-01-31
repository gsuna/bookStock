package com.gsuna.project;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gsuna.project.dto.CustomerDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.entity.User;
import com.gsuna.project.enums.GenderType;
import com.gsuna.project.service.BookService;
import com.gsuna.project.service.CustomerService;
import com.gsuna.project.service.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
@SecurityScheme(name = "bookstockapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "Book Stock API", version = "2.0", description = "Book Stock Information"))
public class SpringReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
	}
	
	@Bean
	CommandLineRunner createInitialUsers(UserService userService,CustomerService customerService, BookService bookService ) {
		return (args) -> {
			User user = new User();
			user.setUsername("admin");
			user.setDisplayName("admin");
			user.setPassword("admin");
			userService.save(user);
			
			CustomerDto customer = new CustomerDto();
			customer.setName("Ayse");
			customer.setSurname("Gul");
			customer.setBirthDate(new Date());
			customer.setGender(GenderType.FEMALE);
			customerService.saveDto(customer);

			Book book = new Book();
			book.setAuthor("Ali");
			book.setCount(15);
			book.setPrice(BigDecimal.TEN);
			bookService.save(book);
			
			Book book2 = new Book();
			book2.setAuthor("Veli");
			book2.setCount(15);
			book2.setPrice(BigDecimal.valueOf(23.12));
			bookService.save(book2);

		};
	}
	
}
