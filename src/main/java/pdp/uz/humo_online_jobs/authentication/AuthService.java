package pdp.uz.humo_online_jobs.authentication;

import org.springframework.stereotype.Service;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.custom_responses.exceptions.NotFoundException;
import pdp.uz.humo_online_jobs.user.UserEntity;
import pdp.uz.humo_online_jobs.user.UserRepository;
import pdp.uz.humo_online_jobs.user.enums.UserType;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse register(RegisterDto dto){
        if (userRepository.existsByEmailIgnoreCase(dto.getEmail())){
            return new ApiResponse("This email already registered ",false);
        }
        if (userRepository.existsByUsernameIgnoreCase(dto.getUsername())){
            return new ApiResponse("This username already exist try another username ",false);
        }
        if (dto.getPassword().length() < 6 || dto.getPassword().length() > 16){
            return new ApiResponse("Password must be between 6 and 16 characters ",false);
        }
        if (dto.getUserType() != UserType.JOB_SEEKER && dto.getUserType() != UserType.EMPLOYER) {
            return new ApiResponse("Invalid user type. Only JOB_SEEKER or EMPLOYER can register.", false);
        }
        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        userRepository.save(user);

        return new ApiResponse("Successfully registered",true);
    }

    public ApiResponse login(LoginDto dto){
        if (!userRepository.existsByEmailIgnoreCase(dto.getEmail())){
            return new ApiResponse("Not Registered from this email",false);
        }
        UserEntity user = userRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword()).orElseThrow(() -> new NotFoundException("User Not Found"));
        return new ApiResponse("Successfully logged in",true);
    }
}
