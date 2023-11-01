package dio.santander;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles(value = "test")
class ApplicationTests {

	@Test
	// @Sql("/data-test.sql")
	void contextLoads() {
	}

}
