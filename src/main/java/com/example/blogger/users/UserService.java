package com.example.blogger.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        private  UsersRepository usersRepository;
        public UserService (@Autowired UsersRepository usersRepository)
        {
            this.usersRepository=usersRepository;
        }

}
