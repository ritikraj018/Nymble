package com.example.Nymble.repo;

import com.example.Nymble.models.CustomerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerActivityRepo extends JpaRepository<CustomerActivity,Long> {
}
