package com.cloudeagle.integrationbuilder.repository;

import com.cloudeagle.integrationbuilder.model.FetchedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FetchedUserRepository extends JpaRepository<FetchedUser, Long> {
}
