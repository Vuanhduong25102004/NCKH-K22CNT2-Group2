package com.example.a2210900017_vuanhduong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThemSuaActivity extends AppCompatActivity {

    EditText edtMasp, edtTensp, edtSoluong, edtDongia;
    Button btnThemSua, btnThoat;
    String trangthai;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_sua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.themsuaactivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
        
    }

    private void addEvent() {
        btnThemSua.setOnClickListener(view -> {
            Products sp = new Products(
                    Integer.parseInt(edtMasp.getText().toString()),
                    edtTensp.getText().toString(),
                    Integer.parseInt(edtSoluong.getText().toString()),
                    Double.parseDouble(edtDongia.getText().toString())
            );
            intent.putExtra("SANPHAM", sp);
            setResult(RESULT_OK, intent);
            finish();
        });

        btnThoat.setOnClickListener(view -> finish());
    }

    private void addView() {
        intent = getIntent();
        trangthai = intent.getStringExtra("TRANGTHAI");

        edtMasp = findViewById(R.id.edtMasp);
        edtTensp = findViewById(R.id.edtTensp);
        edtSoluong = findViewById(R.id.edtSoluong);
        edtDongia = findViewById(R.id.edtDongia);
        btnThemSua = findViewById(R.id.btnThemSua);
        btnThoat = findViewById(R.id.btnThoat);

        if("SUA".equals(trangthai)){
            Products sp =(Products) intent.getSerializableExtra("SANPHAM");
            edtMasp.setText(String.valueOf(sp.getMasp()));
            edtMasp.setEnabled(false);
            edtTensp.setText(sp.getTensp());
            edtSoluong.setText(String.valueOf(sp.getSoluong()));
            edtDongia.setText(String.valueOf(sp.getDongia()));
            btnThemSua.setText("Cập nhật");
        }else{
            btnThemSua.setText("Thêm");
        }
    }
}