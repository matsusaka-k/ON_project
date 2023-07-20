package jp.ac.ecc.se.on_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class ProductRecycleAdapter extends RecyclerView.Adapter<ProductRecycleAdapter.ViewHolder>{
    public List<RowData> dataList;
    public ImageView iv;
    public AppCompatActivity activity;

    public ProductRecycleAdapter(@NotNull List<RowData> dataList, ImageView iv, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(dataList, "dataset");
        this.dataList = dataList;
        this.iv = iv;
        this.activity = activity;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rw, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RowData rowData = dataList.get(position);
        System.out.println(rowData.getItemName());
        System.out.println(holder.itemNameTxt);
        holder.itemNameTxt.setText(rowData.getItemName());
        holder.pointTxt.setText(""+rowData.getPoint());

        holder.itemNameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resId = activity.getResources().getIdentifier(rowData.getItemImage(), "drawable", activity.getPackageName());
                System.out.println(resId);
                System.out.println(rowData.getItemImage());
                System.out.println(rowData.getItemName());
                iv.setImageResource(resId);
            }
        });
    }

    public int getItemCount() {
        return this.dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemNameTxt;
        public TextView pointTxt;

        public ViewHolder(@NonNull View view){
            super(view);
            itemNameTxt = view.findViewById(R.id.txtItemName);
            pointTxt = view.findViewById(R.id.txtItemPoint);
        }
    }

}
