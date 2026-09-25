package com.audit.asset.config;

import com.audit.asset.model.Asset;
import com.audit.asset.model.User;
import com.audit.asset.repository.AssetRepository;
import com.audit.asset.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            AssetRepository assetRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("password123"))
                        .email("admin@audit.com")
                        .role(User.Role.ADMIN)
                        .build());
            }

            if (assetRepository.count() == 0) {
                assetRepository.save(Asset.builder()
                        .serialNumber("SN-10001")
                        .name("Multimeter Fluke 87V")
                        .category("Electrical Testing")
                        .status(Asset.AssetStatus.AVAILABLE)
                        .nextCalibrationDate(LocalDate.now().plusMonths(6))
                        .build());

                assetRepository.save(Asset.builder()
                        .serialNumber("SN-10002")
                        .name("Torque Wrench 1/2in")
                        .category("Mechanical")
                        .status(Asset.AssetStatus.IN_CALIBRATION)
                        .nextCalibrationDate(LocalDate.now().plusWeeks(2))
                        .build());
            }
        };
    }
}