package com.karma.persistance.repository;

import com.karma.persistance.entity.BirthMounth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BirthMounthRepository extends JpaRepository<BirthMounth, Integer> {

    Optional<BirthMounth> findByMounthAndLetterCodeAndCurrentSex(String mounth, String letterCode, String currentSex);
}
