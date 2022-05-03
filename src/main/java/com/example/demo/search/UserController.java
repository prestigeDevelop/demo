package com.example.demo.search;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserCrudRepository;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.registration.RegistrationRequest;
import com.example.demo.util.RegistrationException;
import com.vaadin.flow.shared.Registration;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin
@RequestMapping(path="api/v1/user")
@AllArgsConstructor
@RestController
public class UserController {

    @Autowired
    private LocalDateTime getTime;
    private AppUserRepository appUserRepository;
    private AppUserCrudRepository appUserCrudRepository;
    @GetMapping(value = "/search/email",produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<AppUser>  searchByEmail(@RequestParam String email){
        Optional<AppUser> appUserResult=appUserRepository.findByEmail(email);
        appUserCrudRepository.findByEmail(email).stream().forEach(user-> System.out.println(user.getFirstName()));
        appUserCrudRepository.findAll().stream().forEach(user-> System.out.println(user.getFirstName()));
        System.out.println(getTime);
        if( appUserResult.isPresent()){
            return new ResponseEntity<AppUser>(appUserResult.get(), HttpStatus.OK);
        };
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/update",produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<AppUser> updateUser(@RequestBody RegistrationRequest registrationRequest) {
//     return   appUserRepository.findByEmail(registrationRequest.getEmail()).map(u -> {
//            u.setFirstName(registrationRequest.getFirstName());
//            appUserRepository.save(u);
//            return new ResponseEntity<AppUser>(u, HttpStatus.OK);
//        }).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        Optional<AppUser> appUserResult=appUserRepository.findByEmail(registrationRequest.getEmail());
          if( appUserResult.isPresent()){
              AppUser user=appUserResult.get();
              user.setFirstName(registrationRequest.getFirstName());
              user.setLastName((registrationRequest.getLastName()));
            appUserRepository.save(user);
              return new ResponseEntity<AppUser>(user, HttpStatus.OK);
           };

     return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
