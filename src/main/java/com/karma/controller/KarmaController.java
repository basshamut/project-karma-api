package com.karma.controller;

import com.karma.dto.DataDTO;
import com.karma.dto.KarmaDTO;
import com.karma.service.KarmaService;
import com.karma.util.anotations.validators.OnlyLetters;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import java.util.List;

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

    @Autowired
    private KarmaService karmaService;

	@GetMapping("/")
    @ResponseBody
    public DataDTO<List<KarmaDTO>> findAll() {
        return karmaService.findAll();
    }

    @GetMapping("/{name}/study")
    @ResponseBody
    public DataDTO<List<KarmaDTO>> processName(@NotNull @NotBlank @OnlyLetters @PathVariable(value = "name") String name) {
        return karmaService.processName(name);
    }

    @GetMapping("/{number}")
    @ResponseBody
    public DataDTO<KarmaDTO> findKarmaByNumber(@PathVariable(value = "number") int number) {
        return karmaService.findKarmaByNumber(number);
    }
}
