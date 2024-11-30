package database;

import androidx.annotation.NonNull;

public class ExpenseEntity {
    public int id;
    public String expenseName;
    public String expenseDate;
    public String getExpenseType;
    public String amount;


    public ExpenseEntity() {
    }

    @NonNull
    @Override
    public String toString(){
        return this.expenseName + "\n" + this.expenseDate + "\n" + this.amount;

    }

    public ExpenseEntity(String amount, String getExpenseType, String expenseDate, String expenseName, int id) {
        this.amount = amount;
        this.getExpenseType = getExpenseType;
        this.expenseDate = expenseDate;
        this.expenseName = expenseName;
        this.id = id;
    }
}
