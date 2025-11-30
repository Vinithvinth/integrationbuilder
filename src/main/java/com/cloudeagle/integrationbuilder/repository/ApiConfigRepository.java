package com.cloudeagle.integrationbuilder.repository;

import com.cloudeagle.integrationbuilder.model.ApiConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiConfigRepository extends JpaRepository<ApiConfig, Long> {
    Optional<ApiConfig> findByAppNameAndEndpointName(String appName, String endpointName);
}
