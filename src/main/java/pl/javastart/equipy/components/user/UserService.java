<<<<<<< HEAD
package pl.javastart.equipy.components.user;
=======
<<<<<<< HEAD
package pl.javastart.equipy.components.user;
=======
package pl.javastart.equipy;
>>>>>>> 340ecb7048c0f48fff224acc2070c8b38fb83786
>>>>>>> b075bdc27629da6a3979fb3617d9e69ec56745b4

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
