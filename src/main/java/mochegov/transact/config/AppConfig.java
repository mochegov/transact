package mochegov.transact.config;

import mochegov.transact.load.DataLoad;
import mochegov.transact.repositories.UserRepository;
import mochegov.transact.services.AccountService;
import mochegov.transact.services.EntryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public CommandLineRunner runner(AccountService accountService,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder) {
        return args -> {
            DataLoad.loadData(accountService, userRepository, passwordEncoder);
        };
    }

    @Bean
    @ConfigurationProperties(prefix = "transact")
    public TransactionProperties transactionProperties() {
        return new TransactionProperties();
    }
}
