package com.example.demo.registration.token;

import com.example.demo.appuser.AppUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {


    private  TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveToken(Token token){
      tokenRepository.save(token);
    }

    public Optional<Token> findTokenByUser(AppUser appUser) {
      return  tokenRepository.findByAppUser(appUser);
    }
}
