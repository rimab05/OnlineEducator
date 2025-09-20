package in.utkarshit.service;


import in.utkarshit.DTO.LoginRequestDTO;
import in.utkarshit.DTO.UserRequestDTO;
import in.utkarshit.DTO.UserResponseDTO;

public interface UserService {

    UserResponseDTO register(UserRequestDTO userRequestDTO);
    String verifyOtp(String email, String otp);
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
}
