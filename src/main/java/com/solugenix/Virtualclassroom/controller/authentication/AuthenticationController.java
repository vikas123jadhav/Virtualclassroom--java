package com.solugenix.Virtualclassroom.controller.authentication;

import com.solugenix.Virtualclassroom.model.AuthenticationRequest;
import com.solugenix.Virtualclassroom.model.AuthenticationResponse;
import com.solugenix.Virtualclassroom.service.credential.CredentialService;
import com.solugenix.Virtualclassroom.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
        public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private CredentialService credentialService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException ex){
            throw new Exception("invalid username/password");
        }
        final UserDetails userDetails = credentialService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @GetMapping("/current-user/{userName}")
    public Object getCurrentUser(@PathVariable String userName){
        return  credentialService.loadUserByUsernameForCredentials(userName);
    }

}
