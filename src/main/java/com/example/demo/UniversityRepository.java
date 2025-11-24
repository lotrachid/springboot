package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the University entity.
 * Spring Data JPA automatically provides CRUD methods (save, find, etc.).
 */
@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    // No implementation needed. Inherits all necessary methods.
}