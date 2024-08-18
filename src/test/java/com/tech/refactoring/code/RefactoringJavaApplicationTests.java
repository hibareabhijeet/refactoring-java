package com.tech.refactoring.code;

import com.tech.refactoring.code.controller.RentalController;
import com.tech.refactoring.code.service.RentalInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RefactoringJavaApplicationTests {

	@Autowired
	RentalController rentalController;

	@Autowired
	RentalInfoService rentalInfoService;

	@Test
	void contextLoads() {
		assertThat(rentalController).isNotNull();
		assertThat(rentalInfoService).isNotNull();
	}

}
