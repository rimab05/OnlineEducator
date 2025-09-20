package in.utkarshit.serviceImpl;


import in.utkarshit.DTO.LoginRequestDTO;
import in.utkarshit.DTO.UserRequestDTO;
import in.utkarshit.DTO.UserResponseDTO;
import in.utkarshit.Exception.InvalidCredentialsException;
import in.utkarshit.Exception.InvalidOtpException;
import in.utkarshit.Exception.UserNotFoundException;
import in.utkarshit.entity.User;
import in.utkarshit.repository.UserRepository;
import in.utkarshit.service.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    // Register user and send OTP
    @Override
    public UserResponseDTO register(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        user.setOtp(otp);
        user.setVerified(false);

        userRepository.save(user);

        // Send OTP email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);

        return mapToResponse(user);
    }

    // Verify OTP
    @Override
    public String verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        if(!user.getOtp().equals(otp)) {
            throw new InvalidOtpException("Invalid OTP entered");
        }
        user.setVerified(true);
        user.setOtp(null);
        userRepository.save(user);
        return "Verified Successfully";
    }

    // Login user
    @Override
    public UserResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if(user == null) {
            throw new UserNotFoundException("User not found with email: " + dto.getEmail());
        }
        if(!user.getPassword().equals(dto.getPassword()) || !user.isVerified()) {
            throw new InvalidCredentialsException("Invalid credentials or user not verified");
        }
        return mapToResponse(user);
    }

    // Mapper method
    private UserResponseDTO mapToResponse(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole());
        responseDTO.setVerified(user.isVerified());
        return responseDTO;
    }
}

