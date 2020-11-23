package com.karma.repository;

import com.karma.domain.Karma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmaRepository extends JpaRepository<Karma, Integer> {

    Karma findKarmaByNumber(int number);

}
