package com.example.blogger.users;

import com.example.blogger.common.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UserService userService;
    public UsersController(UserService userService)
    {
        this.userService=userService;
    }
    @PostMapping("")
    ResponseEntity<UserDTO.LoginUserResponse> signUser(@RequestBody UserDTO.CreateUserRequest request){
        var response=userService.signupUser(request);
        return ResponseEntity.created(URI.create("/users/"+response.getId())).body(response);
    }
    @PostMapping("/login")
    ResponseEntity<UserDTO.LoginUserResponse> loginUser(@RequestBody UserDTO.LoginUserRequest request)
    {
        var response=userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/@{username}")
    ResponseEntity<UserDTO.GetUserResponse> getUser(@PathVariable ("username") String username)
    {
        var response=userService.getUserByUsername(username);
        return ResponseEntity.ok(response);


    }
    @ExceptionHandler
    ResponseEntity<ErrorDTO> exceptionHandler(Exception e)
    {
        if(e instanceof UserService.UserNotFoundException)
        {
            return ResponseEntity.status(404).body(new ErrorDTO(e.getMessage()));
        }
        if( e instanceof UserService.UserAuthenticationException)
            return ResponseEntity.status(401).body(new ErrorDTO(e.getMessage()));
        return ResponseEntity.status(500).body(new ErrorDTO(e.getMessage()));
    }
}
