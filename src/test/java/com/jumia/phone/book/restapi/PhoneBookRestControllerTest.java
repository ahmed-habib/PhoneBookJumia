package com.jumia.phone.book.restapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jumia.phone.book.dtos.RestProvider;
import com.jumia.phone.book.services.PhoneBookService;

@WebMvcTest
@AutoConfigureMockMvc
public class PhoneBookRestControllerTest {

    
    @MockBean
    PhoneBookService phoneBookService;
    
    
	@MockBean
	private RestProvider restProvider;

    @Mock
	ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;
    
    
    @Test
    public void whenGetRequestToLoadAllPhones_thenCorrectResponse() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void whenGetRequestToLoadCountryPhones_thenCorrectResponse() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/+212/valid"))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
