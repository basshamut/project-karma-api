package com.karma.repository;

import com.karma.domain.Karma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KarmaRepository extends JpaRepository<Karma, Integer> {

    Optional<Karma> findKarmaByNumber(int number);

}
