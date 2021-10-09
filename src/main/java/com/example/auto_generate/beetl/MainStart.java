package com.example.auto_generate.beetl;

import com.example.auto_generate.generate.impl.MainGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainStart implements ApplicationRunner {
    @Autowired
    private MainGenerator mainGenerator;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        mainGenerator.doGenerate();
    }
}
