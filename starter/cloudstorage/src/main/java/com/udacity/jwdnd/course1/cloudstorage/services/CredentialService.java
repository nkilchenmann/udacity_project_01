package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;
    private Utilities utilities;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, Utilities utilities) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.utilities = utilities;
    }

    public List<Credential> getCredentials() {
        List<Credential> credentialList = credentialMapper.getCredentials(utilities.getCurrentUserId());
        for (Credential cred : credentialList) {
            cred.setUnencryptedPassword(encryptionService.decryptValue(cred.getPassword(), cred.getKey()));
        }
        return credentialList;
    }

    public void addCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
        credential.setUserId(utilities.getCurrentUserId());
        credentialMapper.addCredential(credential);
    }

    public void updateCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
        credentialMapper.updateCredential(credential);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId, utilities.getCurrentUserId());
    }
}
