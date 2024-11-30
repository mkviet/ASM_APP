package com.example.asmdemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import database.DataBaseHelper;
import database.ExpenseEntity;

public class NewExpense extends AppCompatActivity {

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        public EditText editText;
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(requireContext(),this,year,month,day);
        }

        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
            editText.setText(dayOfMonth + "/" + month + "/" + year);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText expenseDate = findViewById(R.id.edtDate);

        expenseDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.editText = expenseDate;
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });


        Button btSave = findViewById(R.id.button);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText expenseNameControl = findViewById(R.id.edtname);
                String expenseName = expenseNameControl.getText().toString();

                Spinner expenseTypeControl = findViewById(R.id.expenseType);
                String expenseType  = expenseTypeControl.getSelectedItem().toString();

                EditText expenseAmountControl = findViewById(R.id.edtamount);
                String expenseAmount = expenseAmountControl.getText().toString();

                EditText expenseDateControl = findViewById(R.id.edtDate);
                String expenseDate = expenseDateControl.getText().toString();

                ExpenseEntity expense = new ExpenseEntity();
                expense.expenseName = expenseName;
                expense.amount = expenseAmount;
                expense.getExpenseType = expenseType;
                expense.expenseDate = expenseDate;

                DataBaseHelper dbHelper = new DataBaseHelper(getApplication());
                long id = dbHelper.insertExpense(expense);
                Toast.makeText(getApplication(),String.valueOf(id),Toast.LENGTH_LONG).show();

            }
        });
    }

    // Thanh Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.listExpense) {
            Intent intent = new Intent(NewExpense.this, activity_list_expenses.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.newExpense){
            Toast.makeText(getApplicationContext(), "You already here", Toast.LENGTH_LONG).show();
        }
        return true;
    }


}