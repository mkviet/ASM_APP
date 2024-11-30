public class ExpenseEntity {
    public int id;
    public String expenseName;
    public String expenseDate;
    public String getExpenseType;
    public String amount;


    public ExpenseEntity() {
    }

    public ExpenseEntity(String amount, String getExpenseType, String expenseDate, String expenseName, int id) {
        this.amount = amount;
        this.getExpenseType = getExpenseType;
        this.expenseDate = expenseDate;
        this.expenseName = expenseName;
        this.id = id;
    }
}
