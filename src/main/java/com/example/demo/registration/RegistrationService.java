package com.example.demo.registration;

import com.example.demo.appuser.AppUser;

public interface RegistrationService {

    AppUser register(RegistrationRequest registrationRequest);
}
