package org.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.delivery.db")  //엔티티가 있는곳을 지정
@EnableJpaRepositories(basePackages = "org.delivery.db") //레파지토리도 다 사용함!
public class JpaConfig {


}
