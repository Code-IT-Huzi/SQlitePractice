package com.example.sqlitepractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editName, editPass, editText3, editText5, editText6;
    Button button, button2, button3, button4;

    myDbAdapter dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        editText3 = findViewById(R.id.editText3);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        dbHelper = new myDbAdapter(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = editName.getText().toString();
                String txtPassword = editPass.getText().toString();

                boolean checkinsertdata = dbHelper.insertdata(txtUsername,txtPassword);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = editText3.getText().toString();
                String txtPassword = editText5.getText().toString();
                boolean checkupdatedata = dbHelper.updatedata(txtUsername, txtPassword);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = editText6.getText().toString();

                boolean checkdeletedata = dbHelper.deletedata(txtUsername);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.getData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer Buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    Buffer.append("UserName :" + cursor.getString(0) + "\n");
                    Buffer.append("Password :" + cursor.getString(1) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Details");
                builder.setMessage(Buffer.toString());
                builder.show();
            }
        });
    }

}