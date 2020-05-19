package com.codegym.controller;


import com.codegym.repository.BlogRepository;
import com.codegym.repository.CategoryRepository;
import com.codegym.repository.UserRepository;
import com.codegym.service.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.codegym")
public class BlogControllerTestConfig {

    @Bean
    public BlogService blogService(){
        return Mockito.mock(BlogServiceImpl.class);
    }

    @Bean
    public UserService userService(){
        return Mockito.mock(UserServiceImpl.class);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("blog")
                .build();
    }

    @Bean
    public CategoryService categoryService() {
        return Mockito.mock(CategoryServiceImpl.class);
    }

    @Bean
    public BlogRepository blogRepository(){
        return Mockito.mock(BlogRepository.class);
    }

    @Bean
    public CategoryRepository categoryRepository(){
        return Mockito.mock(CategoryRepository.class);
    }

    @Bean
    public UserRepository userRepository(){
        return Mockito.mock(UserRepository.class);
    }
}
