package com.karma.service;

import com.karma.service.mapper.KarmaMapper;
import com.karma.persistance.entity.Karma;
import com.karma.service.mapper.dto.DataDTO;
import com.karma.service.mapper.dto.KarmaDTO;
import com.karma.exceptions.ServiceException;
import com.karma.persistance.repository.KarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.karma.util.Constants.NOT_FOUND_CODE;
import static com.karma.util.Constants.NOT_FOUND_MESSAGE;

@Component("karmaService")
public class KarmaService {

    @Autowired
    private KarmaRepository karmaRepository;

    public DataDTO<List<KarmaDTO>> findAll() {
        List<Karma> karmas = karmaRepository.findAll();
        return new DataDTO<>(KarmaMapper.makeKarmaDTOList(karmas));
    }

    public DataDTO<KarmaDTO> findKarmaByNumber(final int number) {
        return new DataDTO<>(KarmaMapper.makeKarmaDTO(karmaRepository.findKarmaByNumber(number)
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE))));
    }

    public DataDTO<List<KarmaDTO>> processName(final String name) {
        final List<Karma> karmas = new ArrayList<>();
        final HashMap<Integer, Integer> numericFrecuence = process(name);
        for (int ind = 1; ind < 10; ind++) {
            if (numericFrecuence.get(ind) == 0) {
                karmaRepository.findKarmaByNumber(ind).ifPresent(karmas::add);
            }
        }
        return new DataDTO<>(KarmaMapper.makeKarmaDTOList(karmas));
    }

    private HashMap<Integer, Integer> process(final String name) {
        final String nameFormatted = getNameFormatted(name);
        return getLetterCountInName(nameFormatted);
    }

    private HashMap<Integer, Integer> getLetterCountInName(String nameFormatted) {
        final HashMap<Integer, Integer> numericFrecuence = createNumericFrecuenceMap(nameFormatted);
        return fillNumericFrecuenceMap(numericFrecuence);
    }

    private String getNameFormatted(String name) {
        return name.toLowerCase().replaceAll("\\s+", "");
    }

    private HashMap<Integer, Integer> createNumericFrecuenceMap(String nameFormatted) {
        final HashMap<Integer, Integer> numericFrecuence = new HashMap<>();

        for (int ind = 0; ind < nameFormatted.length(); ind++) {
            this.getNumericFrequence(numericFrecuence, nameFormatted.substring(ind, ind + 1));
        }
        return numericFrecuence;
    }

    private HashMap<Integer, Integer> fillNumericFrecuenceMap(final HashMap<Integer, Integer> numericFrecuence) {
        for (int ind = 1; ind < 10; ind++) {
            numericFrecuence.putIfAbsent(ind, 0);
        }
        return numericFrecuence;
    }

    private void getNumericFrequence(final HashMap<Integer, Integer> numericFrecuence, final String letterName) {
        final HashMap<String, Integer> pitagoricTable = getPythagoreanTable();
        final Integer numericLetter = pitagoricTable.get(letterName);

        numericFrecuence.merge(numericLetter, 1, Integer::sum);

    }

    private HashMap<String, Integer> getPythagoreanTable() {
        final HashMap<String, Integer> letters = new HashMap<>();
        letters.put("a", 1);
        letters.put("j", 1);
        letters.put("s", 1);
        letters.put("b", 2);
        letters.put("k", 2);
        letters.put("t", 2);
        letters.put("c", 3);
        letters.put("l", 3);
        letters.put("u", 3);
        letters.put("d", 4);
        letters.put("m", 4);
        letters.put("v", 4);
        letters.put("e", 5);
        letters.put("n", 5);
        letters.put("w", 5);
        letters.put("f", 6);
        letters.put("o", 6);
        letters.put("x", 6);
        letters.put("g", 7);
        letters.put("p", 7);
        letters.put("y", 7);
        letters.put("h", 8);
        letters.put("q", 8);
        letters.put("z", 8);
        letters.put("i", 9);
        letters.put("r", 9);
        return letters;
    }

}
