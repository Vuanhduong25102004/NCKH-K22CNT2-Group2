package com.example.a2210900017_vuanhduong;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    String dbName = "QLSanpham.db";

    String dbPath = "/databases/";
    SQLiteDatabase db = null;

    SanPhamAdapter adapter;

    ListView lvSanPham;

    Button btnThem;
    Products sp;

    int posUpdate;

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
        xuLyCopy();
        addView();
        hienthiSanpham();
        addEvent();
        
    }

    private void addEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ThemSuaActivity.class);
                intent.putExtra("TRANGTHAI","THEM");
                startActivityForResult(intent,113);
            }
        });
        lvSanPham.setOnItemClickListener(((parent, view, position, id) -> {
            sp = adapter.getItem(position);
            posUpdate=position;
        }));
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context,menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        sp = adapter.getItem(info.position);
    }
    private void hienthiSanpham() {
        db=openOrCreateDatabase(dbName,MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from SanPham",null);
        adapter.clear();
        while (cursor.moveToNext()){
            int masp = cursor.getInt(0);
            String tensp = cursor.getString(1);
            int soluong = cursor.getInt(2);
            double dongia = cursor.getDouble(3);
            adapter.add(new Products(masp, tensp, soluong,dongia));
        }
        cursor.close();
    }

    private void addView() {
        lvSanPham = findViewById(R.id.lvSanPham);
        adapter = new SanPhamAdapter(MainActivity.this,R.layout.product_items);
        lvSanPham.setAdapter(adapter);
        btnThem = findViewById(R.id.btnThem);
        registerForContextMenu(lvSanPham);
    }

    private void xuLyCopy() {
        try {
            File dbFile = getDatabasePath(dbName);
            if (!dbFile.exists()) {
                copyDataFromAsset();
                Toast.makeText(this, "Database copied successfully", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void copyDataFromAsset() {
        try {
            InputStream myInput = getAssets().open(dbName);
            String outFileName = getApplicationInfo().dataDir + dbPath + dbName;
            File f = new File(getApplicationInfo().dataDir + dbPath);
            if (!f.exists()) f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (sp == null) {
            Toast.makeText(this, "Không có liên hệ nào được chọn", Toast.LENGTH_SHORT).show();
            return super.onContextItemSelected(item);
        }
        if(item.getItemId()==R.id.mnuSua){
            Intent intent = new Intent(MainActivity.this,ThemSuaActivity.class);
            intent.putExtra("TRANGTHAI","SUA");
            intent.putExtra("SANPHAM" , sp);
            startActivityForResult(intent,113);
        } else if (item.getItemId() == R.id.mnuXoa) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn muốn xóa liên hệ này không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        try {
                            db.delete("Contact", "Ma=?", new String[]{String.valueOf(sp.getMasp())});
                            adapter.remove(sp);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, "Đã xóa liên hệ", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("Database Error", e.toString());
                            Toast.makeText(this, "Không xóa được liên hệ", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 113) { // 113 là mã yêu cầu thêm liên lạc
            Products spNew = (Products) data.getSerializableExtra("SANPHAM");

            try {
                ContentValues values = new ContentValues();
                values.put("masp", spNew.getMasp());
                values.put("tensp", spNew.getTensp());
                values.put("soluong", spNew.getSoluong());
                values.put("dongia", spNew.getDongia());

                if ("THEM".equals(data.getStringExtra("TRANGTHAI"))) {
                    if (db.insert("SanPham", null, values) > 0) {
                        adapter.add(spNew);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else if ("SUA".equals(data.getStringExtra("TRANGTHAI"))) {
                    db.update("SanPham", values, "masp=?", new String[]{String.valueOf(spNew.getMasp())});
                    adapter.remove(adapter.getItem(posUpdate));
                    adapter.insert(spNew, posUpdate);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Đã cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Lỗi", e.toString());
            }
        }
    }
}