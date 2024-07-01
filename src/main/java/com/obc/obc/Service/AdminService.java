package com.obc.obc.Service;

import org.springframework.stereotype.Service;

import com.obc.obc.Entity.Admin;
import com.obc.obc.Repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository)
    {
        this.adminRepository=adminRepository;
    }
    public boolean ValidateAdmin(String username,String password)
    {
        Admin admin=adminRepository.findByUsername(username);
        return admin !=null && admin.getPassword().equals(password);
    }
    public boolean isvalidAdmin(String username,String password)
    {
        Admin admin=adminRepository.findByUsername(username);
        return admin !=null && admin.getPassword().equals(password);
    }
    public void deleteUser(Long id)
    {
        adminRepository.deleteById(id);
    }
}
