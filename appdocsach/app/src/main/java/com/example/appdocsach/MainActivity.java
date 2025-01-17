package com.example.appdocsach;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etuser,etpwd;
    Button btnLogin;

    DBHelper dbHelper;
    TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        etuser = findViewById(R.id.etUsername);
        etpwd = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister=findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId = dbHelper.checkUser(etuser.getText().toString(),etpwd.getText().toString());
                if(isLoggedId){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Sai mật khẩu hoặc tên đăng nhập",Toast.LENGTH_LONG).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ActivityRegister.class);
                startActivity(i);
            }
        });
    }


}