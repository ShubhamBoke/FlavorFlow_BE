package com.example.FlavorFlow.Controller;

import com.example.FlavorFlow.AuthConfig.JWTUtil;
import com.example.FlavorFlow.Dto.UserDto;
import com.example.FlavorFlow.Model.AuthRequest;
import com.example.FlavorFlow.Model.AuthResponse;
import com.example.FlavorFlow.Model.CustomUserDetails;
import com.example.FlavorFlow.Model.User;
import com.example.FlavorFlow.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        User user = customUserDetails.getUser();

        return ResponseEntity.ok(new AuthResponse(jwt, new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(), user.getRoles().stream().findFirst().get().getName())));
    }

}





