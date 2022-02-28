package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.repository.BusinessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {

    public static final String NAME = "Name #";
    public static final String ADDRESS = "Address #";
    public static final String POSTAL_CODE = "88-88";
    public static final String CITY = "City #";
    public static final String COUNTRY = "Country #";
    public static final String MAIL = "example@";
    public static final int TAX_ID = 1111111111;

    @InjectMocks
    private BusinessService service;

    @Mock
    private BusinessRepository repository;

    private List<Business> businessList;

    @BeforeEach
    void generateData() {
        businessList = new ArrayList<>();
        for(long i = 0; i < 5; i++) {
            businessList.add(Business.builder()
                    .id(i)
                    .fullName(NAME)
                    .address(ADDRESS)
                    .postalCode(POSTAL_CODE)
                    .city(CITY)
                    .country(COUNTRY)
                    .taxId(TAX_ID)
                    .emailAddress(MAIL)
                    .build());
        }
    }

    @Test
    void shouldGetBusinessList() {
        // given
        when(repository.findAll()).thenReturn(businessList);

        // when
        List<Business> resultList = service.getBusinessList();
        Business business = resultList.get(0);

        // then
        assertThat(resultList).hasSize(5);
        assertAll(
                () -> assertThat(business.getId()).isEqualTo(0L),
                () -> assertThat(business.getFullName()).isEqualTo(NAME),
                () -> assertThat(business.getAddress()).isEqualTo(ADDRESS),
                () -> assertThat(business.getPostalCode()).isEqualTo(POSTAL_CODE),
                () -> assertThat(business.getCity()).isEqualTo(CITY),
                () -> assertThat(business.getTaxId()).isEqualTo(TAX_ID),
                () -> assertThat(business.getEmailAddress()).isEqualTo(MAIL)
        );
    }

    @Test
    void shouldGetSingleBusiness() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(businessList.get(1)));

        // when
        Business result = service.getSingleBusiness(1L);

        // then
        assertNotNull(result);
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(1L),
                () -> assertThat(result.getFullName()).isEqualTo(NAME),
                () -> assertThat(result.getAddress()).isEqualTo(ADDRESS),
                () -> assertThat(result.getPostalCode()).isEqualTo(POSTAL_CODE),
                () -> assertThat(result.getCity()).isEqualTo(CITY),
                () -> assertThat(result.getTaxId()).isEqualTo(TAX_ID),
                () -> assertThat(result.getEmailAddress()).isEqualTo(MAIL)
        );
    }

    @Test
    void getSingleBusinessShouldReturnNull() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        // when
        // then
        assertThrows(NoSuchElementException.class, () -> service.getSingleBusiness(1L));
    }

    @Test
    void shouldCreateNewBusiness() {
        // given
        Business business = businessList.get(1);
        when(repository.save(any(Business.class))).thenReturn(business);

        // when
        Business result = service.createNewBusiness(business);

        // then
        assertNotNull(result);
    }

    @Test
    void shouldUpdateBusinessInfo() {
        // given
        BusinessDto toData = getBusinessDtoExample();
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(businessList.get(1)));

        // when
        Business result = service.updateBusinessInfo(toData);

        // then
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(1L),
                () -> assertThat(result.getFullName()).isEqualTo(toData.getFullName()),
                () -> assertThat(result.getAddress()).isEqualTo(toData.getAddress()),
                () -> assertThat(result.getPostalCode()).isEqualTo(toData.getPostalCode()),
                () -> assertThat(result.getCity()).isEqualTo(toData.getCity()),
                () -> assertThat(result.getTaxId()).isEqualTo(toData.getTaxId()),
                () -> assertThat(result.getEmailAddress()).isEqualTo(toData.getEmailAddress()),
                () -> assertThat(result.getCountry()).isEqualTo(toData.getCountry())
        );
    }

    private BusinessDto getBusinessDtoExample() {
        return new BusinessDto(
                1L,
                "Test Name",
                "Test address",
                "77-777",
                "Test city",
                1234567890,
                "example@test.ee",
                "POLAND"
        );
    }
}