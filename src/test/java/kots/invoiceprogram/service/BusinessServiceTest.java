package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.repository.BusinessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {

    public static final String FULL_NAME = "ExampleName";
    public static final String ADDRESS = "ExampleAddress";
    public static final String POSTAL_CODE = "81-847";
    public static final String CITY = "ExampleCity";
    public static final String COUNTRY = "ExampleCountry";
    public static final int TAX_ID = 1234567890;
    public static final String EMAIL_ADDRESS = "exampleMail@Mail.test";
    public static final int TOTAL_GENERATED_ELEMENTS = 10;
    private List<Business> generatedBusinessList;

    @BeforeEach
    void setUpData() {
        generatedBusinessList = new ArrayList<>();
        for(long i = 1; i <= TOTAL_GENERATED_ELEMENTS; i++) {
            generatedBusinessList.add(Business.builder()
                    .id(i)
                    .fullName(FULL_NAME)
                    .address(ADDRESS)
                    .postalCode(POSTAL_CODE)
                    .city(CITY)
                    .country(COUNTRY)
                    .taxId(TAX_ID)
                    .emailAddress(EMAIL_ADDRESS)
                    .build());
        }
    }

    @InjectMocks
    private BusinessService service;

    @Mock
    private BusinessRepository repository;

    @Test
    void shouldFetchEmptyList() {
        // given
        when(repository.findAll()).thenReturn(List.of());

        // when
        List<Business> resultList = service.getBusinessList();

        // then
        assertThat(resultList).isEmpty();
    }

    @Test
    void shouldFetchListWithData() {
        // given
        when(repository.findAll()).thenReturn(generatedBusinessList);

        // when
        List<Business> resultList = service.getBusinessList();

        // then
        assertThat(resultList).hasSize(TOTAL_GENERATED_ELEMENTS);
        assertThat(resultList).hasOnlyElementsOfType(Business.class);
    }

    @Test
    void shouldGetSingleBusiness() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.of(generatedBusinessList.get(0)));

        // when & then
        assertDoesNotThrow(() -> service.getSingleBusiness(1L));
        Business result = service.getSingleBusiness(1L);
        assertThat(result).isInstanceOf(Business.class);
    }

    @Test
    void shouldThrowNotFoundBusiness() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // when & then
        assertThrows(
                ResponseStatusException.class,
                () -> service.getSingleBusiness(1L),
                "Business not found");
    }

    @Test
    void shouldSaveNewBusiness() {
        // given
        Business business = generatedBusinessList.get(0);
        when(repository.save(any(Business.class))).thenReturn(business);

        // when & then
        assertDoesNotThrow(() -> service.createNewBusiness(business));
        Business newBusiness = service.createNewBusiness(business);
        assertThat(newBusiness).isNotNull();
        assertAll(
                () -> assertNotNull(newBusiness.getId()),
                () -> assertEquals(FULL_NAME, newBusiness.getFullName()),
                () -> assertEquals(ADDRESS, newBusiness.getAddress()),
                () -> assertEquals(POSTAL_CODE, newBusiness.getPostalCode()),
                () -> assertEquals(CITY, newBusiness.getCity()),
                () -> assertEquals(COUNTRY, newBusiness.getCountry()),
                () -> assertEquals(TAX_ID, newBusiness.getTaxId()),
                () -> assertEquals(EMAIL_ADDRESS, newBusiness.getEmailAddress())
        );
    }

    @Test
    void shouldUpdateBusiness() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.of(generatedBusinessList.get(0)));
        BusinessDto businessDto = new BusinessDto(
                1L,
                FULL_NAME + " UPDATED",
                ADDRESS + " UPDATED",
                POSTAL_CODE + " UPDATED",
                CITY + " UPDATED",
                1987456321,
                EMAIL_ADDRESS + " UPDATED",
                COUNTRY + " UPDATED");

        // when & then
        assertDoesNotThrow(() -> service.getSingleBusiness(anyLong()));
        Business result = service.updateBusinessInfo(businessDto);
        assertThat(result).isInstanceOf(Business.class);
        assertAll(
                () -> assertNotNull(result.getId()),
                () -> assertEquals(FULL_NAME + " UPDATED", result.getFullName()),
                () -> assertEquals(ADDRESS + " UPDATED", result.getAddress()),
                () -> assertEquals(POSTAL_CODE + " UPDATED", result.getPostalCode()),
                () -> assertEquals(CITY + " UPDATED", result.getCity()),
                () -> assertEquals(COUNTRY + " UPDATED", result.getCountry()),
                () -> assertEquals(Integer.valueOf(1987456321), result.getTaxId()),
                () -> assertEquals(EMAIL_ADDRESS + " UPDATED", result.getEmailAddress())
        );
    }

    @Test
    void shouldThrowExceptionNotFoundBusinessWhenUpdateBusiness() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        BusinessDto businessDto = new BusinessDto(
                1L,
                FULL_NAME + " UPDATED",
                ADDRESS + " UPDATED",
                POSTAL_CODE + " UPDATED",
                CITY + " UPDATED",
                1987456321,
                EMAIL_ADDRESS + " UPDATED",
                COUNTRY + " UPDATED");

        // when & then
        assertThrows(
                ResponseStatusException.class,
                () -> service.updateBusinessInfo(businessDto),
                "Business not found");
    }

    @Test
    void shouldDeleteBusiness() {
        // given
        doNothing().when(repository).deleteById(anyLong());
        service.deleteBusiness(1L);

        // when & then
        verify(repository, times(1)).deleteById(1L);
    }
}