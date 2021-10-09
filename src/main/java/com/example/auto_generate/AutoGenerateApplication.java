package com.example.auto_generate;

import com.example.auto_generate.beetl.MainStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoGenerateApplication.class, args);
    }

}
