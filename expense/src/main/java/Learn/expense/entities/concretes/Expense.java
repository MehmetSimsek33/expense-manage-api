package Learn.expense.entities.concretes;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="expenses")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="expense_id")
	private Long id;
	
	@Column(name="created_at",nullable =false ,updatable = false)
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	
	@NotNull(message = "Expense name must be not null")
	@Size(min=3, message = "Expense name must bu atleast 3 characters")
	@NotBlank
	@Column(name="name")
	private String name;
	
	@Column(name="expense_description")
	private String description;
	
	@Column(name="category")
	@NotBlank(message = "Category should not be null")
	private String category;
	
	@Column(name="expense_date")
	@NotNull(message= "Date  not be null")
	private Date date;
	
   @Column(name="expense_amount")
   @NotNull(message = "Plase amount should not be null")
	private BigDecimal amount;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="user_id" , nullable = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JsonIgnore
   private User user;
}
