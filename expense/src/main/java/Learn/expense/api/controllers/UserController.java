package Learn.expense.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Learn.expense.bussines.abstracts.UserService;
import Learn.expense.entities.concretes.User;
import Learn.expense.entities.concretes.UserModel;

@RestController
@RequestMapping("api")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public ResponseEntity<User> getByUserId() {
		return new ResponseEntity<User>(userService.getByUserId(), HttpStatus.OK);
	}

	@PutMapping("/profile")
	public ResponseEntity<User> updateUserDetails(@RequestBody User user) {

		return new ResponseEntity<User>(userService.update(user), HttpStatus.OK);
	}

	@DeleteMapping("/deactivate")
	public ResponseEntity<User> deleteUserDetails() {
		userService.delete();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

	}
}
