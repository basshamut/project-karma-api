package com.karma.persistance.repository;

import com.karma.persistance.entity.ProfessionLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionLookupRepository extends JpaRepository<ProfessionLookup, Integer> {

    Optional<ProfessionLookup> findByCode(String code);
}
