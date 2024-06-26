package com.spring.BookReservations.config;

import com.spring.BookReservations.BookReservationsApplication;
import com.spring.BookReservations.BookReservationsApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BookReservationsApplication.class);
	}

}
