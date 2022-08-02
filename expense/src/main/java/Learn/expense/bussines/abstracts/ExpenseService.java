package Learn.expense.bussines.abstracts;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Learn.expense.entities.concretes.Expense;

public interface ExpenseService {
	List<Expense> GetAll();

	Expense getExpenseId(Long id);
	

	void deleteExpenseId(Long id);

	Expense saveExpenseDetails(Expense expense);

	Expense updateExpenseDetails(Long id, Expense expense);

	Page<Expense> getAllExpensesPage(Pageable page);


	List<Expense> getByCategory(String category, Pageable page);

	List<Expense> getByName(String name, Pageable page);
	
	List<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
	
}
