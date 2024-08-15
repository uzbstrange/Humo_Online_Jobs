package pdp.uz.humo_online_jobs.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.humo_online_jobs.authentication.AuthService;
import pdp.uz.humo_online_jobs.authentication.LoginDto;
import pdp.uz.humo_online_jobs.authentication.RegisterDto;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * User infarmatsiyalari user/usercontroller klasida
     */

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponse resp = authService.register(registerDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse resp = authService.login(loginDto);
        return ResponseEntity.ok(resp);
    }
}
