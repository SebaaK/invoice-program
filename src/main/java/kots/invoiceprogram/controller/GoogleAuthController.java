package kots.invoiceprogram.controller;

import kots.invoiceprogram.model.dto.GoogleAuthTokenDto;
import kots.invoiceprogram.model.dto.GooglePayload;
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
    ResponseEntity<GooglePayload> authorize(@RequestBody GoogleAuthTokenDto token) {
        return ResponseEntity.ok(service.verifyingToken(token.getTokenId()));
    }
}
