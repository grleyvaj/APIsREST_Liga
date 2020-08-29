package pe.avanzza;

import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManualFlywayMigrationIntegrationTest {

    @Autowired
    private Flyway flyway;

    @Test
    public void skipAutomaticAndTriggerManualFlywayMigration() {
        flyway.migrate();
    }
}
