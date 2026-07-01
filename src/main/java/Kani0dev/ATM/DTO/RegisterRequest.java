package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.User.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    private Role role;
}
