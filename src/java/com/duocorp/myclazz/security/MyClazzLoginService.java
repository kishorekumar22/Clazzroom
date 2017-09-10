package com.duocorp.myclazz.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.duocorp.myclazz.beans.Student;
import com.duocorp.myclazz.repo.StudentRepository;

/**
 * The Class MyClazzLoginService.
 */
@Service
public class MyClazzLoginService implements UserDetailsService {

	/** The student repository. */
	@Autowired
	StudentRepository studentRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Student student = studentRepository.findByEmailId(emailId);
		if (student != null) {
			Set<GrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new User(emailId, student.getPassword(), authorities);
		}
		throw new UsernameNotFoundException(emailId + " not found for login");
	}

}
