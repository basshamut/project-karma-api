package com.karma.controller;

import com.karma.service.mapper.dto.DataDTO;
import com.karma.service.mapper.dto.KarmaDTO;
import com.karma.service.KarmaService;
import com.karma.util.anotations.validators.OnlyLetters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.karma.util.Constants.URL_KARMA_SERVICE;

@RestController("karmaController")
@CrossOrigin(origins="*",
             maxAge=2000,
             allowedHeaders="header1,header2",
             exposedHeaders="header1",
             allowCredentials= "false")
@RequestMapping(value = URL_KARMA_SERVICE)
@Validated
public class KarmaController {

    private static final int MAX_AGE = 3600;

    @Autowired
    private KarmaService karmaService;

	@GetMapping("/")
    @ResponseBody
    public ResponseEntity<DataDTO<List<KarmaDTO>>> findAll() {
        final DataDTO<List<KarmaDTO>> dataDTO = karmaService.findAll();

        dataDTO.setCode(String.valueOf(HttpStatus.OK.value()));
        dataDTO.setMessage(HttpStatus.OK.name());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(MAX_AGE, TimeUnit.SECONDS).cachePublic())
                .body(dataDTO);
    }

    @GetMapping("/study")
    @ResponseBody
    public ResponseEntity<DataDTO<List<KarmaDTO>>> processName(@OnlyLetters @RequestParam(value = "name") String name) {
        final DataDTO<List<KarmaDTO>> dataDTO = karmaService.processName(name);

        dataDTO.setCode(String.valueOf(HttpStatus.OK.value()));
        dataDTO.setMessage(HttpStatus.OK.name());

	    return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(MAX_AGE, TimeUnit.SECONDS).cachePublic())
                .body(dataDTO);
    }

    @GetMapping("/{number}")
    @ResponseBody
    public DataDTO<KarmaDTO> findKarmaByNumber(@PathVariable(value = "number") int number) {
        return karmaService.findKarmaByNumber(number);
    }

    @RequestMapping("/date")
    @ResponseBody
    public ResponseEntity<DataDTO> processDate(@RequestParam(value = "day") String day,
                                               @RequestParam(value = "month") String month,
                                               @RequestParam(value = "year") String year){


    }
}
