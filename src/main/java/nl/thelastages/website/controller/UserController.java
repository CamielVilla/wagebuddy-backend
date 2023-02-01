package nl.thelastages.website.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.thelastages.website.OnRegistrationCompleteEvent;
import nl.thelastages.website.exception.UserAlreadyExistException;
import nl.thelastages.website.model.dto.UserDto;
import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.service.UserService;
import nl.thelastages.website.model.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RequestMapping("thelastages")
public class UserController {
    private final UserService userService;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addemail")
    public ResponseEntity<Boolean> addEmail (@Valid @RequestBody CreateUserDto dto) {
        return ResponseEntity.ok(userService.addEmail(dto));
    }

}
