package com.karma.repository;

import com.karma.domain.Karma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmaRepository extends CrudRepository<Karma, Integer>{

	Karma findKarmaById(int id);
	Karma findKarmaByNumber(int number);

}
