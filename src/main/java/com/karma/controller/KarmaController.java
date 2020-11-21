package com.karma.controller;

import com.karma.dto.DataDTO;
import com.karma.service.KarmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.karma.util.Constants.URL_KARMA_SERVICE;

@RestController("karmaController")
@CrossOrigin(origins="*",
             maxAge=2000,
             allowedHeaders="header1,header2",
             exposedHeaders="header1",
             allowCredentials= "false")
@RequestMapping(value = URL_KARMA_SERVICE)
public class KarmaController {

    @Autowired
    private KarmaService karmaService;

	@GetMapping("/")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public DataDTO findAll() {
        return karmaService.findAll();
    }

    @GetMapping("/{name}/study")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public DataDTO processName(@PathVariable(value = "name") String name) {
        return karmaService.processName(name);
    }

    @GetMapping("/{number}")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public DataDTO findKarmaByNumber(@PathVariable(value = "number") int number) {
        return karmaService.findKarmaByNumber(number);
    }
}
