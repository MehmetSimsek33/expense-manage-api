package Learn.expense.api.controllers;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Learn.expense.bussines.abstracts.ExpenseService;
import Learn.expense.entities.concretes.Expense;

@RestController
@RequestMapping("api/expense")
public class ExpensesController {

	ExpenseService expenseSevice;

	@Autowired
	public ExpensesController(ExpenseService expenseSevice) {
		super();
		this.expenseSevice = expenseSevice;
	}

	@GetMapping("/getAll")
	public List<Expense> getAll() {
		return this.expenseSevice.GetAll();
	} 

	@GetMapping("/getAllPage")
	public List<Expense> getAllExpensesPage(Pageable page) {
		return expenseSevice.getAllExpensesPage(page).toList();
	}

	@GetMapping("/getById/{id}")
	public Expense getExpensesId(@PathVariable("id") Long id) {
		return expenseSevice.getExpenseId(id);
	}


	@DeleteMapping("/expenses")
	public void deleteExpensesId(@RequestParam Long id) {
		expenseSevice.deleteExpenseId(id);
	}

	@PostMapping("/add")
	public Expense saveExpenceDetails(@Valid @RequestBody Expense expense) {
		return expenseSevice.saveExpenseDetails(expense);
	}

	@PutMapping("/update/{id}")
	public Expense updateExpenseDetails(@PathVariable Long id, @RequestBody Expense expense) {
		return expenseSevice.updateExpenseDetails(id, expense);
	}
	@GetMapping("/getByCategory")
	public List<Expense> getByCategory(@RequestParam String category, Pageable page){
		return expenseSevice.getByCategory(category, page);
	}
	@GetMapping("/getByName")
	public List<Expense> getByName(@RequestParam String name, Pageable page){
		return expenseSevice.getByCategory(name, page);
	}
	@GetMapping("/getByDate")
	public List<Expense> findByDateBetween(@RequestParam Date startDate,
											@RequestParam Date endDate,
											Pageable page){
		return expenseSevice.findByDateBetween(startDate,endDate, page);
	}
 
}
