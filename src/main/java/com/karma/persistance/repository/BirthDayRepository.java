package com.karma.persistance.repository;

import com.karma.persistance.entity.BirthDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BirthDayRepository extends JpaRepository<BirthDay, Integer> {

    Optional<BirthDay> findBySimbolTypeAndDayAndSexInPastAndCurrentSex(String simbolType, String day, String sexInPast, String currentSex);
}
