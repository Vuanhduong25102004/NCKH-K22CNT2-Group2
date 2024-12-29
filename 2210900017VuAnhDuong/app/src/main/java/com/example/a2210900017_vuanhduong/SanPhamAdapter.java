package com.example.a2210900017_vuanhduong;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SanPhamAdapter extends ArrayAdapter<Products> {
    Activity context;
    int resource;
    public SanPhamAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(resource, null);

        // Khởi tạo các TextView
        TextView tvMa = customView.findViewById(R.id.tv_ma);
        TextView tvTen = customView.findViewById(R.id.tv_ten);
        TextView tvSoLuongValue = customView.findViewById(R.id.tv_soluong_value);
        TextView tvDonGiaValue = customView.findViewById(R.id.tv_dongia_value);
        TextView tvThanhTienValue = customView.findViewById(R.id.tv_thanhtien_Value);

        // Lấy đối tượng Products từ danh sách
        Products products = getItem(position);

        // Gán giá trị cho các TextView
        tvMa.setText(products.getMasp()+"");
        tvTen.setText(products.getTensp());
        tvSoLuongValue.setText(String.valueOf(products.getSoluong()));
        tvDonGiaValue.setText(String.format("%.2f VND", products.getDongia()));

        double thanhTien = products.getThanhTien();
        tvThanhTienValue.setText(String.format("%.2f VND", thanhTien));

        // Trả về customView đã cập nhật dữ liệu
        return customView;
    }

}
