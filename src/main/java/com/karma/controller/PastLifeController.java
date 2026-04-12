package com.karma.controller;

import com.karma.service.PastLifeService;
import com.karma.service.mapper.dto.DataDTO;
import com.karma.service.mapper.dto.PastLifeDTO;
import com.karma.util.enums.Month;
import com.karma.util.enums.Sex;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static com.karma.util.Constants.URL_PAST_LIFE_SERVICE;

@RestController("pastLifeController")
@CrossOrigin(origins = "*",
             maxAge = 2000,
             allowedHeaders = "header1,header2",
             exposedHeaders = "header1",
             allowCredentials = "false")
@RequestMapping(value = URL_PAST_LIFE_SERVICE)
@Validated
public class PastLifeController {

    private static final int MAX_AGE = 3600;

    @Autowired
    private PastLifeService pastLifeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<DataDTO<PastLifeDTO>> getPastLife(
            @RequestParam @Min(1) @Max(31) int day,
            @RequestParam Month month,
            @RequestParam @Min(1900) @Max(2100) int year,
            @RequestParam Sex sex) {

        final DataDTO<PastLifeDTO> dataDTO = pastLifeService.getPastLife(day, month, year, sex);

        dataDTO.setCode(String.valueOf(HttpStatus.OK.value()));
        dataDTO.setMessage(HttpStatus.OK.name());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(MAX_AGE, TimeUnit.SECONDS).cachePublic())
                .body(dataDTO);
    }
}
