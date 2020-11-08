package ru.akalavan.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SecurityUserDetailsManager implements UserDetailsManager {
    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (!userName.equals("user"))
            return null;

        List<SecurityPermission> permissions = new ArrayList<>();
        permissions.add(new SecurityPermission("car.read"));
        permissions.add(new SecurityPermission("car.findById"));
        permissions.add(new SecurityPermission("car.crateCar"));
        permissions.add(new SecurityPermission("car.deleteCar"));

        permissions.add(new SecurityPermission("rents.read"));
        permissions.add(new SecurityPermission("rents.findById"));

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new SecurityUser("user", encoder.encode("123"), permissions);
    }
}
