package com.example.demo.controller;

import com.example.demo.appuser.AppUser;
import com.example.demo.registration.RegistrationRequest;
import com.example.demo.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger( RegistrationController.class );
     private RegistrationService registrationService;
    @PostMapping ( value = "/user-registered", produces = {
            MediaType.APPLICATION_JSON_VALUE
    } )
    ResponseEntity<AppUser> register(@RequestBody RegistrationRequest registrationRequest){
              return new ResponseEntity<>( registrationService.register(registrationRequest), HttpStatus.CREATED );
    }

    @GetMapping( value = "/userConfirmed/{token}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    } )
    ResponseEntity<String> confirmEmail(@PathVariable String token){
        String url="http://localhost:8080/api/v1/registration/userConfirmed/"+token;
        return new ResponseEntity<>( url, HttpStatus.CREATED );
    }


// --------------------------------------------------------------------------------------------------------------//
    //
    // exception handlers
    //
    // --------------------------------------------------------------------------------------------------------------//

//    @ExceptionHandler
//    @ResponseStatus ( HttpStatus.BAD_REQUEST )
//    public ErrorResponse handleException ( MethodArgumentNotValidException ex ) {
//        log.error( "Validation failed.", ex );
//
//        ErrorCode error = ErrorCode.INVALID_REQUEST;
//        String details = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map( DefaultMessageSourceResolvable::getDefaultMessage )
//                .findFirst()
//                .orElse( ex.getMessage() );
//
//        return new ErrorResponse( LocalDateTime.now(), error.name(), error.getDescription(), details );
//    }
//
//    @ExceptionHandler
//    @ResponseStatus ( HttpStatus.BAD_REQUEST )
//    public ErrorResponse handleException ( InvalidRequestException ex ) {
//        log.error( "Validation failed.", ex );
//
//        ErrorResponse resp = new ErrorResponse( LocalDateTime.now(), ex.getCode(), ex.getMessage(), ex.getDetails() );
//        return resp;
//    }
//
//    @ExceptionHandler
//    @ResponseStatus ( HttpStatus.BAD_REQUEST )
//    public ErrorResponse handleException ( ServiceException ex ) {
//        log.error( "Validation failed.", ex );
//
//        ErrorResponse resp = new ErrorResponse( LocalDateTime.now(), ex.getCode(), ex.getMessage(), ex.getDetails() );
//        return resp;
//    }
//
//    @ExceptionHandler
//    @ResponseStatus ( HttpStatus.NOT_FOUND )
//    public ErrorResponse handleException ( ResourceNotFoundException ex ) {
//        log.info( "Not Found: {}", ex.getMessage() );
//
//        ErrorResponse resp = new ErrorResponse( LocalDateTime.now(), ex.getCode(), ex.getMessage(), ex.getDetails() );
//        return resp;
//    }

    @ExceptionHandler
    @ResponseStatus ( HttpStatus.INTERNAL_SERVER_ERROR )
    public ErrorResponse handleException ( Exception ex ) {

        if ( ex instanceof ClientAbortException) {
            log.info( "Client closed the connection." );
        } else {
            log.error( "System Error.", ex );
        }

       // ErrorCode error = ErrorCode.SYSTEM_ERROR;
        ErrorResponse resp =
                new ErrorResponse( LocalDateTime.now(), "500 Internal Server Error", ex.toString(), ex.getMessage() );

        return resp;
    }





}
