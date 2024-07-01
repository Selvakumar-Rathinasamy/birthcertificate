package com.obc.obc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obc.obc.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>
{
    User findByFirstname(String firstname);

} 
    

