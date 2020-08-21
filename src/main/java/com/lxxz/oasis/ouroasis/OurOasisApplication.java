package com.lxxz.oasis.ouroasis;

import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OurOasisApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurOasisApplication.class, args);
    }

}
