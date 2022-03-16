package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository repository;

    public List<Business> getBusinessList() {
        return repository.findAll();
    }

    public Business getSingleBusiness(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business not found"));
    }

    public Business createNewBusiness(Business business) {
        return repository.save(business);
    }

    @Transactional
    public Business updateBusinessInfo(BusinessDto business) {
        Business businessEdited = getSingleBusiness(business.getId());
        businessEdited.setFullName(business.getFullName());
        businessEdited.setAddress(business.getAddress());
        businessEdited.setPostalCode(business.getPostalCode());
        businessEdited.setCity(business.getCity());
        businessEdited.setCountry(business.getCountry());
        businessEdited.setTaxId(business.getTaxId());
        businessEdited.setEmailAddress(business.getEmailAddress());
        return businessEdited;
    }

    public void deleteBusiness(Long id) {
        repository.deleteById(id);
    }
}
