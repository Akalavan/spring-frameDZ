package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.akalavan.jpa"})
@EntityScan(basePackages = {"ru.akalavan.objects"})
@ComponentScan(basePackages = {"ru.akalavan.service"})
public class TestConfig {
}
