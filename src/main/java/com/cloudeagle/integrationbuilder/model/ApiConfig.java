package com.cloudeagle.integrationbuilder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;
    private String endpointName;
    private String url;
    private String method;
    private String headers;
    private String authType;
}
