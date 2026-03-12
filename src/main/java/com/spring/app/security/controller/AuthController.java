package com.spring.app.security.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.security.auth.AuthService;
import com.spring.app.security.jwt.JwtToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("memberid") String memberid,
                                   @RequestParam("passwd") String passwd,
                                   @RequestParam("loginType") String loginType,
                                   HttpServletRequest request) {
    	 try {
             JwtToken jwtToken = authService.login(memberid, passwd, loginType);

             HttpSession session = request.getSession();
             session.setAttribute(
                     HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                     SecurityContextHolder.getContext()
             );
             return ResponseEntity.ok(jwtToken);
        } catch (DisabledException e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("success", false);
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
        } catch (BadCredentialsException e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("success", false);
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        } catch (LockedException e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("success", false);
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.LOCKED).body(body);
        }
    }
    
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestParam("refreshToken") String refreshToken) {

        try {

            JwtToken jwtToken = authService.reissue(refreshToken);

            return ResponseEntity.ok(jwtToken);

        } catch (Exception e) {

            Map<String,Object> body = new LinkedHashMap<>();
            body.put("success", false);
            body.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
    }
    
    
    @GetMapping("/check")
    public ResponseEntity<?> check() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean authenticated = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", authenticated);

        if (!authenticated) {
            body.put("error", "UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }

        body.put("memberId", authentication.getName());
        return ResponseEntity.ok(body);
    }

    
    
}