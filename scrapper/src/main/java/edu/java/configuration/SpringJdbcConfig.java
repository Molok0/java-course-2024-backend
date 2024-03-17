package edu.java.configuration;

import jakarta.validation.constraints.NotEmpty;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "spring.datasource", ignoreUnknownFields = false)
@EnableTransactionManagement
public record SpringJdbcConfig(@NotEmpty String url, @NotEmpty String password, @NotEmpty String username,
                               @NotEmpty String driverClassName) {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
