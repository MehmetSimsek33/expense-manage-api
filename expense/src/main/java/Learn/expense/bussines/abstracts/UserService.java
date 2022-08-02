package Learn.expense.bussines.abstracts;

import Learn.expense.entities.concretes.User;
import Learn.expense.entities.concretes.UserModel;

public interface UserService {
	
	
	User getLoggedInUser();
	
	User createUser(UserModel user);

	User getByUserId();

	User update(User user);

	void delete();
}
