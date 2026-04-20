package fr.didiersenou.defimeningesapi.service;

import fr.didiersenou.defimeningesapi.dto.auth.AuthResponse;
import fr.didiersenou.defimeningesapi.dto.auth.LoginRequest;
import fr.didiersenou.defimeningesapi.dto.auth.RefreshTokenRequest;
import fr.didiersenou.defimeningesapi.dto.auth.RegisterRequest;
import fr.didiersenou.defimeningesapi.entity.RefreshToken;
import fr.didiersenou.defimeningesapi.entity.User;
import fr.didiersenou.defimeningesapi.enums.Role;
import fr.didiersenou.defimeningesapi.repository.UserRepository;
import fr.didiersenou.defimeningesapi.security.CustomUserDetails;
import fr.didiersenou.defimeningesapi.security.JwtService;
import fr.didiersenou.defimeningesapi.security.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(Role.PLAYER);
        user.setTotalScore(0);

        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(new CustomUserDetails(savedUser));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser.getId());

        return new AuthResponse(
                jwtToken,
                refreshToken.getToken(),
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return new AuthResponse(
                jwtToken,
                refreshToken.getToken(),
                user.getId(),
                user.getUsername(),
                user.getRole().name());
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.refreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwtToken = jwtService.generateToken(new CustomUserDetails(user));

                    return new AuthResponse(
                            jwtToken,
                            request.refreshToken(),
                            user.getId(),
                            user.getUsername(),
                            user.getRole().name());
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }
}