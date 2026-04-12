package com.karma.persistance.repository;

import com.karma.persistance.entity.BirthYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BirthYearRepository extends JpaRepository<BirthYear, Integer> {

    Optional<BirthYear> findByYearPreffixAndYearSuffixAndCurrentSex(String yearPreffix, String yearSuffix, String currentSex);
}
