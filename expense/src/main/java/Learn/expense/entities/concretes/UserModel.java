package Learn.expense.entities.concretes;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {

	@NotBlank(message = "Name Should not be empty ")
	private String name;
	
	@NotNull(message = "Plase enter  password")
	@Size(min=5 , message="Password min 5 characters")
	private String password;
	
	private Long age= 0L;
	
	@NotNull(message = "Plase enter  email")
	@Email(message = "Plase enter valid email")
	private String email;
}
