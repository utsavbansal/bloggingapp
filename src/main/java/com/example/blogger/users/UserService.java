package com.example.blogger.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class UserService {
        private  UsersRepository usersRepository;
        private ModelMapper modelMapper;
        private UserJwtService jwtService;
        public UserService (@Autowired UsersRepository usersRepository,ModelMapper modelMapper,UserJwtService jwtService)
        {
            this.jwtService=jwtService;
            this.modelMapper=modelMapper;
            this.usersRepository=usersRepository;
        }

    /**
     * Sigup user
     */
    public UserDTO.LoginUserResponse signupUser(UserDTO.CreateUserRequest user)
    {
        // todo : check for invalid inputs and non unique username/email
        UserEntity userEntity=modelMapper.map(user,UserEntity.class);
        UserEntity savedUser=usersRepository.save(userEntity);
        UserDTO.LoginUserResponse response=modelMapper.map(savedUser,UserDTO.LoginUserResponse.class);
        response.setToken(jwtService.createJwtToken(response.getUsername()));// TODO generate token for logged in users
        return response;
    }

    /**
     * Login user
     */

    public  UserDTO.LoginUserResponse loginUser(UserDTO.LoginUserRequest user)
    {
            //Optional<UserEntity> savedUser=usersRepository.findByUsername(user.getUsername());
            /*
            usersRepository.findByUsername(user.getUsername()).ifPresent(userEntity -> {
                // todo match passwords using hash algo
                if(userEntity.getPassword().equals(user.getPassword()))
                {

                }
            });
            */


            UserEntity userEntity=usersRepository.findByUsername(user.getUsername()).orElseThrow(
                    ()->new UserNotFoundException(user.getUsername())
            );
            // todo match passwords using hash algo
            if(userEntity.getPassword().equals(user.getPassword()))
            {
                UserDTO.LoginUserResponse response=modelMapper.map(userEntity,UserDTO.LoginUserResponse.class);
                response.setToken(jwtService.createJwtToken(response.getUsername()));// TODO generate token for logged in users
                return response;
            }
            else
            {
                throw new UserAuthenticationException();
            }
    }
    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String username)
        {
            super("No user found with username "+username );
        }
    }

    static class UserAuthenticationException extends SecurityException{
        public UserAuthenticationException()
        {
            super("Authentication Failed");
        }
    }

}
