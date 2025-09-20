package in.utkarshit.controller;


import in.utkarshit.DTO.LoginRequestDTO;
import in.utkarshit.DTO.UserRequestDTO;
import in.utkarshit.DTO.UserResponseDTO;
import in.utkarshit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO userRequestDTO){
        return userService.register(userRequestDTO);
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp){
        return userService.verifyOtp(email, otp);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO);
    }
}

