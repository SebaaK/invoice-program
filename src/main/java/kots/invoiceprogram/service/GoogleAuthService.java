package kots.invoiceprogram.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import kots.invoiceprogram.config.AdminConfig;
import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.GooglePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final NetHttpTransport httpTransport;
    private final JsonFactory jsonFactory;
    private final AdminConfig adminConfig;
    private final BusinessService businessService;

    public GooglePayload verifyingToken(String idTokenString) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(adminConfig.getClientIdGoogle()))
                .build();

        try {
            GoogleIdToken.Payload payload = verifier.verify(idTokenString).getPayload();
            Business business = businessService.getBusinessByEmailAddress(payload.getEmail());
            return new GooglePayload(business.getId(), (String) payload.get("picture"), (String) payload.get("name"), payload.getEmail());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is not valid");
        }
    }
}
