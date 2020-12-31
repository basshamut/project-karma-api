package com.karma.persistance.repository;

import com.karma.persistance.entity.BirthYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthYearRepository extends JpaRepository<BirthYear, Integer> {
}
