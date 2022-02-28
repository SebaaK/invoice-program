package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessMapper {

    public BusinessDto mapToBusinessDto(final Business business) {
        return new BusinessDto(
                business.getId(),
                business.getFullName(),
                business.getAddress(),
                business.getPostalCode(),
                business.getCity(),
                business.getTaxId(),
                business.getEmailAddress(),
                business.getCountry()
        );
    }

    public Business mapToBusiness(final BusinessDto businessDto) {
        return Business.builder()
                .id(businessDto.getId())
                .fullName(businessDto.getFullName())
                .address(businessDto.getAddress())
                .postalCode(businessDto.getPostalCode())
                .city(businessDto.getCity())
                .taxId(businessDto.getTaxId())
                .emailAddress(businessDto.getEmailAddress())
                .country(businessDto.getCountry())
                .build();
    }

    public List<BusinessDto> mapToBusinessDtoList(final List<Business> businessList) {
        return businessList.stream()
                .map(this::mapToBusinessDto)
                .collect(Collectors.toList());
    }

    public List<Business> mapToBusinessList(final List<BusinessDto> businessDtoList) {
        return businessDtoList.stream()
                .map(this::mapToBusiness)
                .collect(Collectors.toList());
    }
}
