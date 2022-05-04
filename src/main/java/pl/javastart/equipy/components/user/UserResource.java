<<<<<<< HEAD
package pl.javastart.equipy.components.user;
=======
package pl.javastart.equipy;
>>>>>>> 340ecb7048c0f48fff224acc2070c8b38fb83786

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    List<UserDto> findAll(){
        return userService.findAll();
    }
}
