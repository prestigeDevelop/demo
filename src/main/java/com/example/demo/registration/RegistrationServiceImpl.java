package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.email.EmailService;
import com.example.demo.registration.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private  EmailValidator emailValidator;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  AppUserService appUserService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;

    @Override
    public AppUser register(RegistrationRequest request) throws IllegalStateException{
     AppUser  user = null;
        if (emailValidator.test(request.getEmail())) {
            AppUser appUser = new AppUser();
            appUser.setEmail(request.getEmail());
            appUser.setFirstName(request.getFirstName());
            appUser.setLastName(request.getLastName());
            appUser.setAppUserRole(AppUserRole.APP_USER_ROLE);
            appUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            user= appUserService.signUpUser(appUser);
            //send confirmation email
            //emailService.sendEmail(user.getEmail(),tokenService.findTokenByUser(user).get().getToken() );
        } else {
            throw new IllegalStateException(String.format("The email : %s is not valid format", request.getEmail()));
        }
        return user;
    }
}
