package jp.ac.ecc.se.on_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class ProductRecycleAdapter extends RecyclerView.Adapter<ProductRecycleAdapter.ViewHolder> {
    public List<RowData> dataList;

    public ProductRecycleAdapter(@NotNull List<RowData> dataList) {
        Intrinsics.checkNotNullParameter(dataList, "dataset");
        this.dataList = dataList;
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
        holder.pointTxt.setText(rowData.getPoint());
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
