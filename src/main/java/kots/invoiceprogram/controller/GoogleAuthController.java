package kots.invoiceprogram.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import kots.invoiceprogram.model.dto.GoogleAuthTokenDto;
import kots.invoiceprogram.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
class GoogleAuthController {

    private final GoogleAuthService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GoogleIdToken.Payload> authorize(@RequestBody GoogleAuthTokenDto token) {
        return ResponseEntity.ok(service.verifyingToken(token.getTokenId()));
    }
}
