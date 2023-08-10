package kageflo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestKagefloApplication {

	public static void main(String[] args) {
		SpringApplication.from(KagefloApplication::main).with(TestKagefloApplication.class).run(args);
	}

}
