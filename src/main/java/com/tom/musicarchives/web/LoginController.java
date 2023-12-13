package com.tom.musicarchives.web;


import com.tom.musicarchives.model.User;
import com.tom.musicarchives.model.UserRepository;
import com.tom.musicarchives.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String homePage() {
        return "redirect:/bands";
    }
}
