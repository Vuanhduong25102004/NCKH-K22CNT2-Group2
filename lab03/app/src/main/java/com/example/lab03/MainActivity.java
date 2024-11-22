    package com.example.lab03;

    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.Button;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class MainActivity extends AppCompatActivity {

        Button btnBaiTap1, btnBaiTap2, btnBaiTap3;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnBaiTap1 = findViewById(R.id.btnBaiTap1);
            btnBaiTap2 = findViewById(R.id.btnBaiTap2);
            btnBaiTap3 = findViewById(R.id.btnBaiTap3);

            btnBaiTap1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this , lab3_1Activity.class)));
            btnBaiTap2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this , lab3_2Activity.class)));
            btnBaiTap3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this , lab3_3Activity.class)));
        }
    }