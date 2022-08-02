package Learn.expense.bussines.concretes;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Learn.expense.bussines.abstracts.ExpenseService;
import Learn.expense.bussines.abstracts.UserService;
import Learn.expense.core.utilities.excaptions.ResourceNotFountExcaption;
import Learn.expense.dataAccess.abstracts.ExpenseDao;
import Learn.expense.entities.concretes.Expense;

@Service
public class ExpenseManager implements ExpenseService {

	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private UserService userService;

	
	

	@Override
	public List<Expense> GetAll() {

		return this.expenseDao.findAll();
	}

//	@Override
//	public Expense getExpenseId(Long id) {
//
//		Optional<Expense> expense = expenseDao.findById(id);
//		if (expense.isPresent()) {
//			return expense.get();
//		}
//		throw new ResourceNotFountExcaption("Expense is not fount the id" + id);
//
//	}
	@Override
	public Expense getExpenseId(Long id) {

		Optional<Expense> expense = expenseDao.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFountExcaption("Expense is not fount the id" + id);

	}


	@Override
	public void deleteExpenseId(Long id) {
		Expense expense=getExpenseId(id);
		expenseDao.delete(expense);

	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		expense.setUser(userService.getLoggedInUser());	
		return expenseDao.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpenseId(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		existingExpense
				.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		existingExpense.setDescription(
				expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());

		return expenseDao.save(existingExpense);

	}

	//Review 
	@Override
	public Page<Expense> getAllExpensesPage(Pageable page) {
		return expenseDao.findByUserId(userService.getLoggedInUser().getId(),page);
	}

	public int calculateFactorial(int number) {
		return number * calculateFactorial(number - 1);
	}

	@Override
	public List<Expense> getByCategory(String category, Pageable page) {
		return expenseDao.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category, page).toList();
	}

	@Override
	public List<Expense> getByName(String keyword, Pageable page) {
		return expenseDao.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword, page).toList();
	}

	@Override
	public List<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page) {
		if (startDate == null) {
			startDate = new Date(0);
		}
		if (endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		Page<Expense> pages = expenseDao.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate, page);
		
		return pages.toList();
	}

	//findByUserIdAndNameContaining
}
