package springboot1909.springboot_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication(scanBasePackages = "com.qf")
@MapperScan(basePackages = "com.qf.dao")

public class SpringbootShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShopApplication.class, args);
	}

}
