package net.javaguides.sms.Config;

import net.javaguides.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var studentByUserName = studentRepository.getStudentByUserName(email);

        if(studentByUserName == null)
            throw new UsernameNotFoundException("Could not find user");
        var customStudentDetails = new CustomUserDetail(studentByUserName);
        return customStudentDetails;
    }
}
