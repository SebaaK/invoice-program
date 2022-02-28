package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class BusinessMapperTest {

    @Autowired
    private BusinessMapper businessMapper;

    @Test
    void mapToBusinessDto() {
        // given
        Business business = Business.builder()
                .id(1L)
                .fullName("Example company")
                .address("Example 2")
                .postalCode("88-888")
                .city("Poland")
                .taxId(1234567890)
                .emailAddress("example@test.test")
                .country("POLAND")
                .build();

        // when
        BusinessDto result = businessMapper.mapToBusinessDto(business);

        // then
        assertAll(
                () -> assertSame(business.getId(), result.getId()),
                () -> assertSame(business.getFullName(), result.getFullName()),
                () -> assertSame(business.getAddress(), result.getAddress()),
                () -> assertSame(business.getPostalCode(), result.getPostalCode()),
                () -> assertSame(business.getCity(), result.getCity()),
                () -> assertSame(business.getTaxId(), result.getTaxId()),
                () -> assertSame(business.getEmailAddress(), result.getEmailAddress()),
                () -> assertSame(business.getCountry(), result.getCountry())
        );
    }

    @Test
    void mapToBusiness() {
        // given
        BusinessDto business = new BusinessDto(
                1L,
                "John Smith",
                "Example 2/3",
                "88-888",
                "Poland",
                1234567890,
                "example@test.test",
                "POLAND"
        );

        // when
        Business result = businessMapper.mapToBusiness(business);

        // then
        assertAll(
                () -> assertSame(business.getId(), result.getId()),
                () -> assertSame(business.getFullName(), result.getFullName()),
                () -> assertSame(business.getAddress(), result.getAddress()),
                () -> assertSame(business.getPostalCode(), result.getPostalCode()),
                () -> assertSame(business.getCity(), result.getCity()),
                () -> assertSame(business.getTaxId(), result.getTaxId()),
                () -> assertSame(business.getEmailAddress(), result.getEmailAddress()),
                () -> assertSame(business.getCountry(), result.getCountry())
        );
    }

    @Test
    void mapToBusinessDtoList() {
        // given
        List<Business> businessList = new ArrayList<>();
        for(long i = 0; i < 5; i++) {
            businessList.add(Business.builder()
                    .id(i)
                    .fullName("Example Name " + i)
                    .address("Example address " + i)
                    .postalCode("88-88" + i)
                    .city("City #" + i)
                    .taxId(1234567890 + (int) i)
                    .emailAddress("exampleMail" + i + "@test")
                    .country("Country #" + i)
                    .build());
        }

        // when
        List<BusinessDto> resultList = businessMapper.mapToBusinessDtoList(businessList);

        // then
        assertThat(resultList).hasSize(5);
    }

    @Test
    void mapToBusinessList() {
        // given
        List<BusinessDto> businessList = new ArrayList<>();
        for(long i = 0; i < 5; i++) {
            businessList.add(new BusinessDto(
                    i,
                    "Example Name " + i,
                    "Example address " + i,
                    "88-88" + i,
                    "City #" + i,
                    1234567890 + (int) i,
                    "exampleMail" + i + "@test",
                    "Country #" + i
            ));
        }

        // when
        List<Business> resultList = businessMapper.mapToBusinessList(businessList);
        System.out.println(resultList);

        // then
        assertThat(resultList).hasSize(5);
    }
}