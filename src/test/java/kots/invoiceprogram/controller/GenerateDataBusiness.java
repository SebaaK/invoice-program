package kots.invoiceprogram.controller;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;

import java.util.ArrayList;
import java.util.List;

class GenerateDataBusiness {

    public static final int TOTAL_ELEMENTS = 10;
    public static final String FULL_NAME = "BusinessName";
    public static final String ADDRESS = "ExampleAddress";
    public static final String POSTAL_CODE = "12-345";
    public static final String CITY = "ExampleCity";
    public static final String COUNTRY = "ExampleCountry";
    public static final int TAX_ID = 1234567890;
    public static final String EMAIL_ADDRESS = "example@mail.test";

    public static List<Business> getBusinessList() {
        List<Business> businessList = new ArrayList<>();
        for(long i = 1; i <= TOTAL_ELEMENTS; i++) {
            businessList.add(Business.builder()
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
        return businessList;
    }

    public static List<BusinessDto> getBusinessDtoList() {
        List<BusinessDto> businessDtoList = new ArrayList<>();
        for(long i = 1; i <= TOTAL_ELEMENTS; i++) {
            businessDtoList.add(new BusinessDto(
                    i,
                    FULL_NAME,
                    ADDRESS,
                    POSTAL_CODE,
                    CITY,
                    TAX_ID,
                    EMAIL_ADDRESS,
                    COUNTRY
            ));
        }
        return businessDtoList;
    }
}
