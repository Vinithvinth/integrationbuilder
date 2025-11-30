package com.cloudeagle.integrationbuilder.controller;

import com.cloudeagle.integrationbuilder.service.ApiIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integration")
public class IntegrationController {

    @Autowired
    private ApiIntegrationService apiIntegrationService;

    @GetMapping("/fetch-users/{app}")
    public ResponseEntity<String> fetchUsers(@PathVariable String app) {
        try {
            apiIntegrationService.fetchUsers(app);
            return ResponseEntity.ok("Users fetched and saved successfully for app: " + app);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
