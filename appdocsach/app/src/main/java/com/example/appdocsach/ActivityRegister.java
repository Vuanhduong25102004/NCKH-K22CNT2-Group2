package com.example.appdocsach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser,etPwd,etRePwd;
    Button btnRegister,btnGotoLogin;

    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser=findViewById(R.id.etUsername);
        etPwd=findViewById(R.id.etPassword);
        etRePwd=findViewById(R.id.etRePassword);
        btnRegister=findViewById(R.id.btnRegister);
        dbHelper = new DBHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user,pwd,repwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                repwd = etRePwd.getText().toString();
                if(user.equals("") || pwd.equals("") || repwd.equals("")){
                    Toast.makeText(ActivityRegister.this,"Hãy nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }else {
                    if (pwd.equals(repwd)){
                        if(dbHelper.checkUsername(user)){
                            Toast.makeText(ActivityRegister.this,"Tên đăng nhập đã tồn tại",Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registeredSuccess = dbHelper.insertData(user,pwd);
                        if(registeredSuccess){
                            Toast.makeText(ActivityRegister.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(ActivityRegister.this,"Đăng ký thất bại",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ActivityRegister.this,"Mật khẩu không trùng khớp",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnGotoLogin=findViewById(R.id.btnLogin);
    }
}
