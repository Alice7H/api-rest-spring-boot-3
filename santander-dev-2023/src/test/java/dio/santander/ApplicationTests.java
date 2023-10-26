package dio.santander;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class ApplicationTests {

	@Test
	@Sql("/data-dev.sql")
	void contextLoads() {
	}

}
