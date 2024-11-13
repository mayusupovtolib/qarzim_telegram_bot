package uz.qarzim.qarzim_uz.payload;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String fullname;
}
