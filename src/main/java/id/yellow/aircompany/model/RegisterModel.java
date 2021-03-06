package id.yellow.aircompany.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterModel {

    private String username;
    private String password;
    private String role;
}
