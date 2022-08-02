package Learn.expense.bussines.concretes;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Learn.expense.bussines.abstracts.UserService;
import Learn.expense.core.utilities.excaptions.ItemAlreadyExistsExcaption;
import Learn.expense.core.utilities.excaptions.ResourceNotFountExcaption;
import Learn.expense.dataAccess.abstracts.UserDao;
import Learn.expense.entities.concretes.Expense;
import Learn.expense.entities.concretes.User;
import Learn.expense.entities.concretes.UserModel;

@Service
public class UserManager implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(UserModel user) {
		if (userDao.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsExcaption("User is already register with email:" + user.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return userDao.save(newUser);
	}

	@Override
	public User getByUserId() {
		Long usetId=getLoggedInUser().getId();
		return userDao.findById(usetId).orElseThrow(() -> new ResourceNotFountExcaption("User not found id " + usetId));
	}

	@Override
	public User update(User user) {
		User oUser = getByUserId();
		oUser.setName(user.getName() != null ? user.getName() : oUser.getName());
		oUser.setEmail(user.getEmail() != null ? passwordEncoder.encode(user.getPassword()) : oUser.getEmail());
		oUser.setPassword(user.getPassword() != null ? user.getEmail() : oUser.getPassword());
		oUser.setAge(user.getAge() != null ? user.getAge() : oUser.getAge());

		return userDao.save(oUser);
	}

	@Override
	public void delete() {
		User user = getByUserId();
		userDao.delete(user);
	}

	@Override
	public User getLoggedInUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found the email" + email));

	}

}
