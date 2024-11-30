package com.example.asmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import database.DataBaseHelper;
import database.ExpenseEntity;

public class activity_list_expenses extends AppCompatActivity {

    List<ExpenseEntity> allExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_expenses);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());

        allExpense = dataBaseHelper.getAllExpenses();
        ArrayAdapter adapter = new ArrayAdapter<ExpenseEntity>(this,
                R.layout.listview_item, allExpense);
        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);

    }

    // Thanh Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.newExpense) {
            Intent intent = new Intent(activity_list_expenses.this, NewExpense.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.listExpense){
            Toast.makeText(getApplicationContext(), "You already here", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}