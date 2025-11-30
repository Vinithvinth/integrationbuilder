package com.cloudeagle.integrationbuilder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fetched_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String appName;
}
