package cn.hanamizu.campushelp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.hanamizu.campushelp.mapper")
@SpringBootApplication
public class CampusHelpApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusHelpApplication.class, args);
    }

}
