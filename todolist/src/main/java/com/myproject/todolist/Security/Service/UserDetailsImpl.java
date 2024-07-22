package com.myproject.todolist.Security.Service;

import com.myproject.todolist.Models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public UserDetailsImpl(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword());
    }
}
