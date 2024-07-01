package com.obc.obc.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.obc.obc.Entity.Admin;
public interface AdminRepository extends JpaRepository<Admin,Long>
{
    Admin findByUsername(String username);   
}