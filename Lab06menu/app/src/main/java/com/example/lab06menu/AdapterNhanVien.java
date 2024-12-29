import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab06menu.R;


public class AdapterNhanVien extends ArrayAdapter<NhanVien> {
    private Activity context;
    private int resource;
    public AdapterNhanVien(@NonNull Activity context, int resource){
        super(context,resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=this.context.getLayoutInflater().inflate(this.resource,null);
        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        TextView txtMa = view.findViewById(R.id.txtMa);
        TextView txtTen = view.findViewById(R.id.txtTen);
        NhanVien nv = getItem(position);
        if (nv.isNam())
            imgHinh.setImageResource(R.drawable.img);
        else
            imgHinh.setImageResource(R.drawable.img_1);
        txtMa.setText(nv.getMa());
        txtTen.setText(nv.getTen());
        return view;
    }
}
