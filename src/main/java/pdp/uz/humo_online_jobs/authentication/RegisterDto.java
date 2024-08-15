package pdp.uz.humo_online_jobs.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pdp.uz.humo_online_jobs.user.enums.UserType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String email;
    private String username;
    private String password;
    private UserType userType;
}
