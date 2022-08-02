package Learn.expense.core.utilities.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Learn.expense.dataAccess.abstracts.UserDao;
import Learn.expense.entities.concretes.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao; 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	User existingUser =	userDao.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User nor found for the email: "+email));
	
	return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
	}

}
