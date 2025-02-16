package com.example.core;

import com.example.core.plot.SortingTimeComplexityPlot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class SortApplication {
    public static void main(String[] args) {
        // Run Spring Boot application
        SpringApplication.run(SortApplication.class, args);

        System.setProperty("java.awt.headless", "false");
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless mode detected. GUI cannot be displayed.");
        } else {
            System.out.println("GUI mode enabled. Starting application...");
            new SortingTimeComplexityPlot().setVisible(true);
        }
    }
}
