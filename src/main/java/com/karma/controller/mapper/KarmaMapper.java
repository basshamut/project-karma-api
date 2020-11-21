package com.karma.controller.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.karma.domain.Karma;
import com.karma.dto.KarmaDTO;

public class KarmaMapper {
    public static Karma makeKarma(KarmaDTO karmaDTO) {
    	Karma karma = new Karma();
    	if(karmaDTO.getId() == 0) {
    		karma.setId(null);
    	}else {
    		karma.setId(karmaDTO.getId());
    	}
        karma.setNumber(karmaDTO.getNumber());
        karma.setImprove(karmaDTO.getImprove());
        karma.setMissing(karmaDTO.getMissing());
        karma.setSituation(karmaDTO.getSituation());
        return karma;
    }

    public static KarmaDTO makeKarmaDTO(Karma karma) {
        KarmaDTO karmaDTO = new KarmaDTO();
        karmaDTO.setId(karma.getId());
        karmaDTO.setNumber(karma.getNumber());
        karmaDTO.setImprove(karma.getImprove());
        karmaDTO.setMissing(karma.getMissing());
        karmaDTO.setSituation(karma.getSituation());
        return karmaDTO;
    }

    public static List<KarmaDTO> makeKarmaDTOList(Collection<Karma> karmas) {
        return karmas.stream()
                .map(KarmaMapper::makeKarmaDTO)
                .collect(Collectors.toList());
    }

    public static List<Karma> makeKarmaList(Collection<KarmaDTO> karmasDTO) {
        if (karmasDTO == null) {
            return Collections.emptyList();
        }
        return karmasDTO.stream()
                .map(KarmaMapper::makeKarma)
                .collect(Collectors.toList());
    }
}
