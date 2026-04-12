package com.karma.service;

import com.karma.exceptions.ServiceException;
import com.karma.persistance.entity.*;
import com.karma.persistance.repository.*;
import com.karma.service.mapper.dto.DataDTO;
import com.karma.service.mapper.dto.PastLifeDTO;
import com.karma.util.enums.Month;
import com.karma.util.enums.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.karma.util.Constants.NOT_FOUND_CODE;
import static com.karma.util.Constants.NOT_FOUND_MESSAGE;

@Component("pastLifeService")
public class PastLifeService {

    @Autowired private BirthYearRepository birthYearRepository;
    @Autowired private BirthMounthRepository birthMounthRepository;
    @Autowired private BirthDayRepository birthDayRepository;
    @Autowired private EncarnationDateRepository encarnationDateRepository;
    @Autowired private EncarnationLocationRepository encarnationLocationRepository;
    @Autowired private ProfessionLookupRepository professionLookupRepository;
    @Autowired private PersonalityLookupRepository personalityLookupRepository;

    public DataDTO<PastLifeDTO> getPastLife(int day, Month month, int year, Sex sex) {
        String yearPreffix = String.valueOf(year).substring(0, 3);
        String yearSuffix  = String.valueOf(year).substring(3);

        // PASO 1: letra del año
        BirthYear birthYear = birthYearRepository
                .findByYearPreffixAndYearSuffixAndCurrentSex(yearPreffix, yearSuffix, sex.name())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        // PASO 2: datos del mes
        BirthMounth birthMounth = birthMounthRepository
                .findByMounthAndLetterCodeAndCurrentSex(month.name(), birthYear.getLetterCode(), sex.name())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        // PASO 3: datos del día
        BirthDay birthDay = birthDayRepository
                .findBySimbolTypeAndDayAndSexInPastAndCurrentSex(
                        birthMounth.getSimbolType(),
                        String.valueOf(day),
                        birthMounth.getSexInPast(),
                        sex.name())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        // PASO 4: año de la encarnación anterior
        // Los orientation_type masculinos (MMM-XXX) no existen en encarnationdate;
        // se normalizan restando 12 posiciones alfabéticas para obtener el grupo equivalente (AAA-LLL).
        String orientationType = normalizeOrientationType(birthMounth.getOrientationType());
        EncarnationDate encarnationDate = encarnationDateRepository
                .findByOrientationTypeAndLetterCode(orientationType, birthMounth.getLetterCode())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        // PASO 5: país / región
        EncarnationLocation encarnationLocation = encarnationLocationRepository
                .findByLocation(birthDay.getLocation())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        // PASO 6: descripción de la profesión
        ProfessionLookup professionLookup = professionLookupRepository
                .findByCode(birthMounth.getProfesion())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        String professionDescription = Sex.M.name().equals(birthMounth.getSexInPast())
                ? professionLookup.getDescriptionMale()
                : professionLookup.getDescriptionFemale();

        // PASO 7: descripción de la personalidad (día impar → odd, día par → even)
        PersonalityLookup personalityLookup = personalityLookupRepository
                .findBySimbolCode(birthMounth.getSimbolType())
                .orElseThrow(() -> new ServiceException(NOT_FOUND_MESSAGE, NOT_FOUND_CODE));

        String personalityDescription = (day % 2 != 0)
                ? personalityLookup.getDescriptionOdd()
                : personalityLookup.getDescriptionEven();

        PastLifeDTO dto = new PastLifeDTO();
        dto.setSexInPastLife(Sex.M.name().equals(birthMounth.getSexInPast()) ? "Masculino" : "Femenino");
        dto.setCountry(encarnationLocation.getLocationName());
        dto.setYearApprox(encarnationDate.getYearEncarnation());
        dto.setProfession(professionDescription);
        dto.setPersonalitySymbol(personalityLookup.getSimbolName());
        dto.setPersonality(personalityDescription);

        return new DataDTO<>(dto);
    }

    // Los meses masculinos en birthmounth usan letras M-X (posiciones 13-24).
    // encarnationdate solo tiene grupos A-L (posiciones 1-12).
    // Restando 12 posiciones se obtiene el grupo correcto.
    private String normalizeOrientationType(String orientationType) {
        if (orientationType == null || orientationType.isEmpty()) {
            return orientationType;
        }
        char letter = orientationType.charAt(0);
        if (letter >= 'M') {
            letter = (char) (letter - 12);
            return String.valueOf(letter) + String.valueOf(letter) + String.valueOf(letter);
        }
        return orientationType;
    }
}
