package vicente.marti.jwtbackend.security.controller;

import vicente.marti.jwtbackend.global.dto.MessageDto;
import vicente.marti.jwtbackend.global.exceptions.AttributeException;
import vicente.marti.jwtbackend.security.dto.CreateUserDto;
import vicente.marti.jwtbackend.security.dto.JwtTokenDto;
import vicente.marti.jwtbackend.security.dto.LoginUserDto;
import vicente.marti.jwtbackend.security.entity.UserEntity;
import vicente.marti.jwtbackend.security.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserEntityService userEntityService;

    @PostMapping("/create")
    public ResponseEntity<MessageDto> create(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.create(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "user " + userEntity.getUsername() + " has been created"));
    }

    @PostMapping("/create-admin")
    public ResponseEntity<MessageDto> createAdmin(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.createAdmin(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "admin " + userEntity.getUsername() + " has been created"));
    }

    @PostMapping("/create-user")
    public ResponseEntity<MessageDto> createUser(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.createUser(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "user " + userEntity.getUsername() + " has been created"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@Valid @RequestBody LoginUserDto dto) throws AttributeException {
        JwtTokenDto jwtTokenDto = userEntityService.login(dto);
        return ResponseEntity.ok(jwtTokenDto);
    }
}
