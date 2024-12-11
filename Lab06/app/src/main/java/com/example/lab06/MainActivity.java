package com.example.lab06;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.DanhMuc;
import com.example.model.SanPham;

public class MainActivity extends AppCompatActivity {
    Spinner spnDanhMuc;
    ArrayAdapter<DanhMuc> danhMucAdapter;
    ArrayAdapter<SanPham> sanPhamAdapter;
    EditText editMaSP, editTenSP, editGia;
    ListView lvSanPham;
    Button btnaddSP;

    DanhMuc selectedDanhMuc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }

    private void addEvent() {
        btnaddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyNhapSanPham();
            }
        });
        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDanhMuc=danhMucAdapter.getItem(position);
                sanPhamAdapter.clear();
                sanPhamAdapter.addAll(selectedDanhMuc.getSanphams());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addView() {
        // anh xa view
        editMaSP=findViewById(R.id.editMaSP);
        editTenSP=findViewById(R.id.editTenSP);
        editGia=findViewById(R.id.editGiaSP);
        btnaddSP=findViewById(R.id.btnaddSP);
        lvSanPham=findViewById(R.id.lvSanPham);
        spnDanhMuc = findViewById(R.id.spnSanPham);

        // thiet lap adapter cho spinner
        danhMucAdapter = new ArrayAdapter<DanhMuc>(MainActivity.this, android.R.layout.simple_spinner_item);
        sanPhamAdapter=new ArrayAdapter<SanPham>(MainActivity.this, android.R.layout.simple_list_item_1);
        spnDanhMuc.setAdapter(danhMucAdapter);
        lvSanPham.setAdapter(sanPhamAdapter);

        // them danh muc mau
        danhMucAdapter.add(new DanhMuc("1","bear"));
        danhMucAdapter.add(new DanhMuc("2","rượu"));
        danhMucAdapter.add(new DanhMuc("3","thuốc lá"));
        danhMucAdapter.add(new DanhMuc("4","nước ngọt"));
    }
    private void xulyNhapSanPham() {
        SanPham sanPham = new SanPham(editMaSP.getText().toString(),editTenSP.getText().toString(),Integer.parseInt(editGia.getText().toString()));
        selectedDanhMuc.getSanphams().add(sanPham);
        sanPhamAdapter.add(sanPham);

    }
}