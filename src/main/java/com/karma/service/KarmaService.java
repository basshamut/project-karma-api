package com.karma.service;

import com.karma.controller.mapper.KarmaMapper;
import com.karma.domain.Karma;
import com.karma.dto.DataDTO;
import com.karma.exceptions.ServiceException;
import com.karma.repository.KarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("karmaService")
public class KarmaService {

	@Autowired
	private KarmaRepository karmaRepository;


	/*
	 * This method returns the base table (pythagorean table) main.karma.to obtain the number of repetitions of each letter
	 */
	private HashMap<String, Integer> getPythagoreanTable() {
		HashMap<String, Integer> letters = new HashMap<>();
		letters.put("a", 1); letters.put("j", 1); letters.put("s", 1);
		letters.put("b", 2); letters.put("k", 2); letters.put("t", 2);
		letters.put("c", 3); letters.put("l", 3); letters.put("u", 3);
		letters.put("d", 4); letters.put("m", 4); letters.put("v", 4);
		letters.put("e", 5); letters.put("n", 5); letters.put("w", 5);
		letters.put("f", 6); letters.put("o", 6); letters.put("x", 6);
		letters.put("g", 7); letters.put("p", 7); letters.put("y", 7);
		letters.put("h", 8); letters.put("q", 8); letters.put("z", 8);
		letters.put("i", 9); letters.put("r", 9);
		return letters;
	}

	/*
	 * This method returns the count of each letter of the name based on the pythagorean table
	 */
	private HashMap<Integer, Integer> getNumericFrequence(HashMap<Integer, Integer> numericFrecuence, String letterName){
		HashMap<String, Integer> pitagoricTable = getPythagoreanTable();
		Integer numericLetter = pitagoricTable.get(letterName);
		Integer value = numericFrecuence.get(numericLetter);
		if(value == null) {
			value = 1;
			numericFrecuence.put(numericLetter, value);
		}else {
			numericFrecuence.put(numericLetter, value + 1);
		}
		return numericFrecuence;
	}

	/*
	 *This method process the name. First format the string without spaces and transform all in lower case, then count the repetitions
	 *for each letter and generate a map with the result
	 */
	private HashMap<Integer, Integer> process(String name) {
		HashMap<Integer, Integer> numericFrecuence = new HashMap<>();

		name = name.toLowerCase().replaceAll("\\s+", "");
		for(int ind = 0;  ind < name.length() ; ind++) {
			this.getNumericFrequence(numericFrecuence, name.substring(ind, ind+1));
		}

		Integer value;
		for(int ind = 1; ind < 10 ; ind++) {
			value = numericFrecuence.get(ind);
			if(value == null) {
				numericFrecuence.put(ind, 0);
			}
		}

		return numericFrecuence;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataDTO findAll() {
		List<Karma> karmas = (List<Karma>) karmaRepository.findAll();
		return new DataDTO(KarmaMapper.makeKarmaDTOList(karmas));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataDTO findKarmaByNumber(int number){
		Karma karma = null;
		try {
			karma =  karmaRepository.findKarmaByNumber(number);
        }catch (Exception e) {
			throw new ServiceException();
		}
		return new DataDTO(KarmaMapper.makeKarmaDTO(karma));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataDTO processName(String name){
		Karma karma;
		List<Karma> karmas = new ArrayList<>();
		HashMap<Integer, Integer> numericFrecuence = process(name);
		try {
			for(int ind = 1; ind < 10 ; ind++) {
				if(numericFrecuence.get(ind) == 0){
					karma = karmaRepository.findKarmaByNumber(ind);
					karmas.add(karma);
				}
			}
        }catch (Exception e) {
			throw new ServiceException();
		}
		return new DataDTO(KarmaMapper.makeKarmaDTOList(karmas));
	}
}
