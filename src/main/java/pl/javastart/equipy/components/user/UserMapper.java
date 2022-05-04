<<<<<<< HEAD
package pl.javastart.equipy.components.user;
=======
<<<<<<< HEAD
package pl.javastart.equipy.components.user;
=======
package pl.javastart.equipy;
>>>>>>> 340ecb7048c0f48fff224acc2070c8b38fb83786
>>>>>>> b075bdc27629da6a3979fb3617d9e69ec56745b4

public class UserMapper {

    static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPesel(user.getPesel());
        return dto;
    }

    static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPesel(dto.getPesel());
        return user;
    }
}
