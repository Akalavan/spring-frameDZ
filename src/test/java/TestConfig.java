import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.akalavan.jpa"})
@EntityScan(basePackages = {"ru.akalavan.objects"})
public class TestConfig {
}
