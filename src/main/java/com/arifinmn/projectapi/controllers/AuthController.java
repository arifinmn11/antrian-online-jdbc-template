package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.configs.JwtToken;
import com.arifinmn.projectapi.exceptions.ApplicationExceptions;
import com.arifinmn.projectapi.models.requests.JwtRequest;
import com.arifinmn.projectapi.models.responses.JwtResponse;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.services.IUserService;
import com.arifinmn.projectapi.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMessage<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService

                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtToken.generateToken(userDetails);

        return ResponseMessage.success(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {


            throw new ApplicationExceptions(HttpStatus.NOT_ACCEPTABLE, "Something wrong, user disable");

        } catch (BadCredentialsException e) {

            throw new ApplicationExceptions(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "Something wrong, INVALID_CREDENTIALS");

        }

    }

}