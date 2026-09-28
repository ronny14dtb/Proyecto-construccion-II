package application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TiendaApplication.class)
@ActiveProfiles("test")
class TiendaApplicationTests {

	@Test
	void contextLoads() {
	}

}
