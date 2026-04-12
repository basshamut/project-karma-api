package com.karma.persistance.repository;

import com.karma.persistance.entity.EncarnationLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncarnationLocationRepository extends JpaRepository<EncarnationLocation, Integer> {

    Optional<EncarnationLocation> findByLocation(String location);
}
