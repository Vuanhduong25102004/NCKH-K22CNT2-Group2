package com.example.lab06_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.SanPham;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    ArrayAdapter<SanPham> adtSanPham;
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
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = adtSanPham.getItem(position);
                Intent intent = new Intent(MainActivity.this,chi_tiet.class);
                intent.putExtra("SANPHAM",sp);
                startActivityForResult(intent,113);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 113 && resultCode == 115){
            SanPham sp = (SanPham) data.getSerializableExtra("SANPHAM");
            for(int i = 0 ; i < adtSanPham.getCount() ; i++){
                if(sp.getMa().equals(adtSanPham.getItem(i).getMa())){
                    adtSanPham.remove(adtSanPham.getItem(i));
                    break;
                }
            }
        }
    }

    private void addView() {
        lvSanPham=findViewById(R.id.lvSanPham);
        adtSanPham = new ArrayAdapter<SanPham>(MainActivity.this,android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(adtSanPham);
        adtSanPham.add(new SanPham("001","Kem ly",50000));
        adtSanPham.add(new SanPham("002","Kem que",6000));
        adtSanPham.add(new SanPham("003","Kem ốc quế",20000));
    }
}