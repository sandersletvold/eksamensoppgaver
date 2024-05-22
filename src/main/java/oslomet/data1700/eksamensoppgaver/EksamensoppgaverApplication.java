package oslomet.data1700.eksamensoppgaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class EksamensoppgaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EksamensoppgaverApplication.class, args);
    }

}
