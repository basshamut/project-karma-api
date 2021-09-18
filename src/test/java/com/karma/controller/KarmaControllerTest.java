package com.karma.controller;

import com.karma.service.KarmaService;
import com.karma.service.mapper.dto.DataDTO;
import com.karma.service.mapper.dto.KarmaDTO;
import com.karma.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(KarmaController.class)
public class KarmaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KarmaService karmaService;

    @Test
    public void shouldHTTPCodeOk200() throws Exception{
        final DataDTO<List<KarmaDTO>> dataDTO = new DataDTO<>();

        Mockito.when(karmaService.findAll()).thenReturn(dataDTO);

        mockMvc.perform(get( "/" + Constants.URL_KARMA_SERVICE + "/").header("Origin","*"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());    }
}
