package com.project;

import com.project.entity.ITest;
import com.project.entity.test;
import com.project.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProjectApplication{
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
