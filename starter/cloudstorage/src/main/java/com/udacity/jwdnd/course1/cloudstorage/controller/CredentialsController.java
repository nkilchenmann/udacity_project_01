package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {
    private CredentialService credentialService;

    public CredentialsController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String addCredential(@ModelAttribute("Credential") Credential credential) {
        if (credential.getCredentialId() != null) {
            credentialService.updateCredential(credential);
        } else {
            credentialService.addCredential(credential);
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam(name = "id") Integer credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }
}
