package com.example.shopping.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.FilterProductUser;
import com.example.shopping.R;
import com.example.shopping.models.FilterProduct;
import com.example.shopping.models.ModelProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller> implements Filterable {

    private Context context;
    public ArrayList<ModelProduct> productList, filterList;
    private FilterProduct filter;

    public AdapterProductSeller(Context context, ArrayList<ModelProduct> productList) {
        this.context = context;
        this.productList = productList;
        this.filterList = productList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_seller, parent, false);
        return new HolderProductSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {
        //get data
        ModelProduct modelProduct = productList.get(position);
        String id= modelProduct.getProduct();
        String uid= modelProduct.getUid();
        String discountAvailable = modelProduct.getDiscountAvailable();
        String discountNote= modelProduct.getDiscountNote();
        String discountPrice = modelProduct.getDiscountPrice();
        String productCategory = modelProduct.getProductCategory();
        String productDescription = modelProduct.getProductDescription();
        String icon = modelProduct.getProductIcon();
        String quantity = modelProduct.getProductQuantity();
        String title = modelProduct.getProductTitle();
        String timestamp = modelProduct.getTimestamp();
        String originalPrice = modelProduct.getOriginalPrice();

        //set data

        holder.titleEt.setText(title);
        holder.quantityEt.setText(quantity);
        holder.DiscountedNoteEt.setText("$"+discountNote);
        holder.DiscountedPriceEt.setText("$"+discountPrice);
        holder.originalPriceTv.setText("$"+originalPrice);
        if(discountAvailable.equals("true")){
            holder.DiscountedPriceEt.setVisibility(View.VISIBLE);
            holder.DiscountedNoteEt.setVisibility(View.VISIBLE);
            holder.originalPriceTv.setPaintFlags(holder.originalPriceTv.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.DiscountedPriceEt.setVisibility(View.GONE);
            holder.DiscountedNoteEt.setVisibility(View.GONE);
            holder.originalPriceTv.setPaintFlags(0);
        }
        try{
            Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_add_shopping_primary).into(holder.productIcon1);
        }
        catch (Exception e){
            holder.productIcon1.setImageResource(R.drawable.ic_baseline_add_shopping_primary);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter = new FilterProduct(this, filterList);
        }
        return filter;
    }

    class HolderProductSeller extends RecyclerView.ViewHolder{

        private ImageView productIcon1,nextIv;
        private TextView DiscountedNoteEt, titleEt,quantityEt,DiscountedPriceEt,originalPriceTv;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);

            productIcon1 = itemView.findViewById(R.id.productIcon1);
            DiscountedNoteEt = itemView.findViewById(R.id.DiscountedNoteEt);
            titleEt = itemView.findViewById(R.id.titleEt);
            quantityEt = itemView.findViewById(R.id.quantityEt);
            DiscountedPriceEt = itemView.findViewById(R.id.DiscountedPriceEt);
            originalPriceTv = itemView.findViewById(R.id.originalPriceTv);
            //nextIv = itemView.findViewById(R.id.nextIv);
        }
    }
}
