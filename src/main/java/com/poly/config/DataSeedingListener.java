package com.poly.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.poly.dao.GenderDAO;
import com.poly.dao.RoleUserDAO;
import com.poly.dao.UserDAO;
import com.poly.entity.RoleUser;
import com.poly.entity.User;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	
	
    @Autowired
    private UserDAO usDAO;
    @Autowired
    private GenderDAO gdDAO;

    @Autowired
    private RoleUserDAO roleDAO;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleDAO.findByNameRole("ROLE_ADMIN") == null) {
        	roleDAO.save(new RoleUser(1, "ROLE_ADMIN", null));
        }

        if (roleDAO.findByNameRole("ROLE_MEMBER") == null) {
        	roleDAO.save(new RoleUser(1, "ROLE_MEMBER", null));
        }

        // Admin account
        if (usDAO.findByEmailUser("muoilvph11464@fpt.edu.vn") == null) {
            User admin = new User();
            admin.setEmailUser("muoilvph11464@fpt.edu.vn");
            admin.setPasswordUser(passwordEncoder.encode("12345678"));
            admin.setAddressUser("hà nội ");
            admin.setGender(gdDAO.findById("GD01").orElse(null));
            admin.setNameUser("Muoi");
            HashSet<RoleUser> roles = new HashSet<>();
            roles.add(roleDAO.findByNameRole("ROLE_ADMIN"));
            roles.add(roleDAO.findByNameRole("ROLE_MEMBER"));
            admin.setRoleUsers(roles);
            usDAO.save(admin);
        }

        // Member account
        if (usDAO.findByEmailUser("lapunsang@gmail.com") == null) {
            User user = new User();
            user.setEmailUser("lapunsang@gmail.com");
            user.setPasswordUser(passwordEncoder.encode("12345678"));
            user.setAddressUser("hà nội ");
            user.setGender(gdDAO.findById("GD02").orElse(null));
            user.setNameUser("Huyền");
            HashSet<RoleUser> roles = new HashSet<>();
            roles.add(roleDAO.findByNameRole("ROLE_MEMBER"));
            user.setRoleUsers(roles);
            usDAO.save(user);
        }
    }

}
