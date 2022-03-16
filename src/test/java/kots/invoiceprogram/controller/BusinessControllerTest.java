package kots.invoiceprogram.controller;

import com.google.gson.Gson;
import kots.invoiceprogram.mapper.BusinessMapper;
import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.service.BusinessService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static kots.invoiceprogram.controller.GenerateDataBusiness.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(BusinessController.class)
class BusinessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private BusinessService service;

    @MockBean
    private BusinessMapper mapper;

    @Test
    void shouldFetchEmptyBusinessList() throws Exception {
        // given
        when(service.getBusinessList()).thenReturn(List.of());
        when(mapper.mapToBusinessDtoList(anyList())).thenReturn(List.of());

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/business")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchBusinessList() throws Exception {
        // given
        when(service.getBusinessList()).thenReturn(getBusinessList());
        when(mapper.mapToBusinessDtoList(anyList())).thenReturn(getBusinessDtoList());

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/business")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(TOTAL_ELEMENTS)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].fullName", Matchers.is(FULL_NAME)))
                .andExpect(jsonPath("$[0].address", Matchers.is(ADDRESS)))
                .andExpect(jsonPath("$[0].postalCode", Matchers.is(POSTAL_CODE)))
                .andExpect(jsonPath("$[0].city", Matchers.is(CITY)))
                .andExpect(jsonPath("$[0].taxId", Matchers.is(TAX_ID)))
                .andExpect(jsonPath("$[0].emailAddress", Matchers.is(EMAIL_ADDRESS)))
                .andExpect(jsonPath("$[0].country", Matchers.is(COUNTRY)));
    }

    @Test
    void shouldGetSingleBusiness() throws Exception {
        // given
        when(service.getSingleBusiness(anyLong())).thenReturn(getBusinessList().get(0));
        when(mapper.mapToBusinessDto(any(Business.class))).thenReturn(getBusinessDtoList().get(0));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/business/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.fullName", Matchers.is(FULL_NAME)))
                .andExpect(jsonPath("$.address", Matchers.is(ADDRESS)))
                .andExpect(jsonPath("$.postalCode", Matchers.is(POSTAL_CODE)))
                .andExpect(jsonPath("$.city", Matchers.is(CITY)))
                .andExpect(jsonPath("$.taxId", Matchers.is(TAX_ID)))
                .andExpect(jsonPath("$.emailAddress", Matchers.is(EMAIL_ADDRESS)))
                .andExpect(jsonPath("$.country", Matchers.is(COUNTRY)));
    }

    @Test
    void shouldCreateNewBusiness() throws Exception {
        // given
        when(service.createNewBusiness(any(Business.class))).thenReturn(getBusinessList().get(0));

        when(mapper.mapToBusiness(any(BusinessDto.class))).thenReturn(getBusinessList().get(0));
        when(mapper.mapToBusinessDto(any(Business.class))).thenReturn(getBusinessDtoList().get(0));

        String json = gson.toJson(getBusinessDtoList().get(0));


        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.fullName", Matchers.is(FULL_NAME)))
                .andExpect(jsonPath("$.address", Matchers.is(ADDRESS)))
                .andExpect(jsonPath("$.postalCode", Matchers.is(POSTAL_CODE)))
                .andExpect(jsonPath("$.city", Matchers.is(CITY)))
                .andExpect(jsonPath("$.taxId", Matchers.is(TAX_ID)))
                .andExpect(jsonPath("$.emailAddress", Matchers.is(EMAIL_ADDRESS)))
                .andExpect(jsonPath("$.country", Matchers.is(COUNTRY)));
    }

    @Test
    void shouldFetchUpdatedBusiness() throws Exception {
        // given
        when(service.updateBusinessInfo(any(BusinessDto.class))).thenReturn(getBusinessList().get(0));
        when(mapper.mapToBusinessDto(any(Business.class))).thenReturn(getBusinessDtoList().get(0));

        String json = gson.toJson(getBusinessDtoList().get(0));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/business/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.fullName", Matchers.is(FULL_NAME)))
                .andExpect(jsonPath("$.address", Matchers.is(ADDRESS)))
                .andExpect(jsonPath("$.postalCode", Matchers.is(POSTAL_CODE)))
                .andExpect(jsonPath("$.city", Matchers.is(CITY)))
                .andExpect(jsonPath("$.taxId", Matchers.is(TAX_ID)))
                .andExpect(jsonPath("$.emailAddress", Matchers.is(EMAIL_ADDRESS)))
                .andExpect(jsonPath("$.country", Matchers.is(COUNTRY)));
    }

    @Test
    void shouldDeleteBusiness() throws Exception {
        // given
        doNothing().when(service).deleteBusiness(anyLong());

        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/business/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteBusiness(1L);
    }

}