package tt.hashtranslator.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tt.hashtranslator.dto.ApplicationIdResponse;
import tt.hashtranslator.dto.HashRequest;
import tt.hashtranslator.dto.HashResultResponse;
import tt.hashtranslator.exception.InvalidCredentialException;
import tt.hashtranslator.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private ApplicationService applicationService;
    private AccessRightValidator validator;

    @PostMapping
    public ResponseEntity<ApplicationIdResponse> create(@RequestBody HashRequest hashes, HttpServletRequest httpServletRequest) {
        log.info("Creating user with hashes= {}", hashes);
        boolean result = validator.check(httpServletRequest);
        if (!result) {
            throw new InvalidCredentialException("Invalid Credentials");
        }
        return ResponseEntity.accepted().body(applicationService.create(hashes));
    }

    @GetMapping("/{id}")
    public HashResultResponse findById(@PathVariable long id, HttpServletRequest httpServletRequest) {
        log.info("Finding user by id {}", id);
        boolean result = validator.check(httpServletRequest);
        if (!result) {
            throw new RuntimeException("Invalid Credentials");
        }
        return applicationService.findById(id);
    }
}
