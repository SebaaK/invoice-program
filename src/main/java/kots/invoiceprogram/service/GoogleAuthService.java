package kots.invoiceprogram.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import kots.invoiceprogram.config.AdminConfig;
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

    public GoogleIdToken.Payload verifyingToken(String idTokenString) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(adminConfig.getClientIdGoogle()))
                .build();

        try {
            return verifier.verify(idTokenString).getPayload();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is not valid");
        }
    }
}
