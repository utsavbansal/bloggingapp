package com.example.blogger.users;

public class UserDTO {
    private  UserDTO(){}
    public static class CreateUserRequest{
        private String username;
        private String password;
        private String email;
        private String bio;
    }
    public static class LoginUserRequest{
        private String username;
        private String password;
    }
    public static class LoginUserResponse{

    }
}
