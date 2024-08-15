package pdp.uz.humo_online_jobs.user;

import org.springframework.stereotype.Service;
import pdp.uz.humo_online_jobs.custom_responses.ApiResponse;
import pdp.uz.humo_online_jobs.custom_responses.exceptions.NotFoundException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse editUser(String username, UserDto dto) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("There is no user with username: " + username));
        if (dto.getUsername()!=null){
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail()!=null){
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword()!=null){
            user.setPassword(dto.getPassword());
        }
        userRepository.save(user);

        return new ApiResponse("Your data updated successfully", true);
    }

    public UserEntity getUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found on " + id)
        );
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}
