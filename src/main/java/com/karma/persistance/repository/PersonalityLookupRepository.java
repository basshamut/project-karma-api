package com.karma.persistance.repository;

import com.karma.persistance.entity.PersonalityLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalityLookupRepository extends JpaRepository<PersonalityLookup, Integer> {

    Optional<PersonalityLookup> findBySimbolCode(String simbolCode);
}
