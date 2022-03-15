package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessMapperTest {

    @Autowired
    private BusinessMapper businessMapper;

    @Test
    void shouldReturnBusinessDto() {
        // given
        Business business = Business.builder()
                .id(1L)
                .fullName("Test XX")
                .address("Kielecka 2")
                .postalCode("23-412")
                .city("Kielce")
                .taxId(1234567890)
                .emailAddress("example@test.test")
                .country("POLAND")
                .build();

        // when
        BusinessDto resultDto = businessMapper.mapToBusinessDto(business);

        // then
        assertAll(
                () -> assertEquals(1L, resultDto.getId()),
                () -> assertEquals("Test XX", resultDto.getFullName()),
                () -> assertEquals("Kielecka 2", resultDto.getAddress()),
                () -> assertEquals("23-412", resultDto.getPostalCode()),
                () -> assertEquals("Kielce", resultDto.getCity()),
                () -> assertEquals(1234567890, resultDto.getTaxId()),
                () -> assertEquals("example@test.test", resultDto.getEmailAddress()),
                () -> assertEquals("POLAND", resultDto.getCountry())
        );
    }

    @Test
    void shouldReturnBusiness() {
        // given
        BusinessDto businessDto = new BusinessDto(1L, "TestBusiness", "Kielecka 2/3", "22-222", "Kielce", 1234567890, "example@example.test", "POLAND");

        // when
        Business result = businessMapper.mapToBusiness(businessDto);

        // then
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("TestBusiness", result.getFullName()),
                () -> assertEquals("Kielecka 2/3", result.getAddress()),
                () -> assertEquals("22-222", result.getPostalCode()),
                () -> assertEquals("Kielce", result.getCity()),
                () -> assertEquals(1234567890, result.getTaxId()),
                () -> assertEquals("example@example.test", result.getEmailAddress()),
                () -> assertEquals("POLAND", result.getCountry()));
    }

    @Test
    void shouldReturnBusinessDtoList() {
        // given
        List<Business> businessList = getBusinessList();

        // when
        List<BusinessDto> result = businessMapper.mapToBusinessDtoList(businessList);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(9);
    }

    private List<Business> getBusinessList() {
        List<Business> businessList = new ArrayList<>();
        for(long i = 1; i < 10; i++) {
            businessList.add(Business.builder()
                    .id(i)
                    .fullName("Test #" + i)
                    .address("Kielecka " + i)
                    .postalCode("23-41" + i)
                    .city("Kielce")
                    .taxId((int) (1234567890 + i))
                    .emailAddress("example@test.test #" + i)
                    .country("POLAND")
                    .build()
            );
        }
        return businessList;
    }
}