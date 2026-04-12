package com.karma.persistance.repository;

import com.karma.persistance.entity.EncarnationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncarnationDateRepository extends JpaRepository<EncarnationDate, Integer> {

    Optional<EncarnationDate> findByOrientationTypeAndLetterCode(String orientationType, String letterCode);
}
