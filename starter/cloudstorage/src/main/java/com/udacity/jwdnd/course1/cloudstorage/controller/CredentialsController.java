package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialsController {
    private CredentialService credentialService;

    public CredentialsController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String addCredential(@ModelAttribute("Credential") Credential credential, Model model) {
        if (credential.getCredentialId() != null) {
            try {
                credentialService.updateCredential(credential);
                model.addAttribute("credentialsUploadStatus", "ok");
            } catch (Exception e) {
                model.addAttribute("credentialsUploadStatus", "failure");
            }
        } else {
            try {
                credentialService.addCredential(credential);
                model.addAttribute("credentialsUploadStatus", "ok");
            } catch (Exception e) {
                model.addAttribute("credentialsUploadStatus", "failure");
            }
        }
        return "result";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam(name = "id") Integer credentialId, Model model) {
        try {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("credentialsUploadStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("credentialsUploadStatus", "failure");
        }
        return "result";
    }
}
