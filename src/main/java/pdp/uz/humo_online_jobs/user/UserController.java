package pdp.uz.humo_online_jobs.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
    Login va Registratsiya auth/controller classida
     */

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity
                .ok(userService.getUsers());
    }

    @GetMapping("/")
    public ResponseEntity<UserEntity> getUserById(@RequestParam Long id) {
        return ResponseEntity
                .ok(userService.getUserEntityById(id));
    }
}
