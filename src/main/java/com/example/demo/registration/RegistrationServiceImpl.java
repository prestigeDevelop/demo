package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    private final EmailValidator emailValidator;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserService appUserService;
    @Override
    public AppUser register(RegistrationRequest request) {

        if(emailValidator.test(request.getEmail())){
            AppUser appUser=new AppUser();
             appUser.setEmail(request.getEmail());
             appUser.setFirstName(request.getFirstName());
             appUser.setLastName(request.getLastName());
            appUser.setAppUserRole(AppUserRole.APP_USER_ROLE);
             appUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

                 return appUserService.signUpUser(appUser);

        }else{
            throw new IllegalStateException(String.format("The email : %s is not valid format",request.getEmail()));
        }

    }
}
