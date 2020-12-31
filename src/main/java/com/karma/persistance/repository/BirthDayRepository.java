package com.karma.persistance.repository;

import com.karma.persistance.entity.BirthDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthDayRepository extends JpaRepository<BirthDay, Integer> {
}
