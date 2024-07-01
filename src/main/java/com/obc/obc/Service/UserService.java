package com.obc.obc.Service;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obc.obc.Entity.User;
import com.obc.obc.Repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public User savUser(String firstname,String lastname,String  moblie,String address,String password,LocalDate regdate,String role)
    {
        logger.info("Saving user: {} {} {} {} {} {} {}", firstname, lastname, moblie , address , passwordEncoder.encode(password), regdate, role);
        User newUser=new User();
        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setMoblie(moblie);
        newUser.setAddress(address);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRegdate(regdate);
        newUser.setRole(role);
        System.out.println("password : "+passwordEncoder.encode(password));
        return userRepository.save(newUser);
        
    }

    public boolean ValidateLogin(String firstname,String password)
    {
        
        User user=userRepository.findByFirstname(firstname);
        logger.info("User found with firstname '{}' and password '{}'", user);
        return user !=null&& passwordEncoder.matches(password, user.getPassword());
    }
    public boolean isvalidUser (String firstname,String password)
    {
        User user=userRepository.findByFirstname(firstname);
        return user !=null && user.getPassword().equals(password);
    }    
}
