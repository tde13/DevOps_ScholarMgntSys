package net.javaguides.sms.Config;

import net.javaguides.sms.entity.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private Student student;

    public CustomUserDetail(Student student) {
        super();
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var simpleGrantedAuthority = new SimpleGrantedAuthority(student.getRole());

        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    public Student getUser() {
        return student;
    }
    public String getRole() {
        return student.getRole();
    }

    @Override
    public String getUsername() {
        return student.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
