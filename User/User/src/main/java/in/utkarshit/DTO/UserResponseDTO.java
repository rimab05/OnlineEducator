package in.utkarshit.DTO;


import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private boolean verified;
}

