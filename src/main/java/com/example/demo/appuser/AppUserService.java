package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository userRepository;
    private static final String USER_NOT_FOUND="User with email %s not Found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public  AppUser signUpUser(AppUser appUser){

        if(userRepository.findByEmail(appUser.getEmail()).isPresent()){
            throw new IllegalStateException(String.format("User with email : %s already exist",appUser.getEmail()));
        }
        return  userRepository.save(appUser);
    }

}
