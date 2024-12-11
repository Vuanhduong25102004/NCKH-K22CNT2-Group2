package com.example.lab06_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.SanPham;

public class chi_tiet extends AppCompatActivity {
    EditText editMa, editTen, editGia;
    Button btndelete,btnback;
    Intent intent;
    SanPham sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }

    private void addEvent() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("SANPHAM",sp);
                setResult(115,intent);
                finish();
            }
        });
    }

    private void addView() {
        editMa = findViewById(R.id.editMaSP);
        editTen = findViewById(R.id.editTenSP);
        editGia = findViewById(R.id.editGiaSP);
        btndelete = findViewById(R.id.btnDeleteSP);
        btnback = findViewById(R.id.btnback);

        intent=getIntent();
        sp = (SanPham)
        intent.getSerializableExtra("SANPHAM");
        editMa.setText(sp.getMa());
        editTen.setText(sp.getTen());
        editGia.setText(sp.getGia()+"");

    }
}