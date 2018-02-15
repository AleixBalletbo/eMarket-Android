package com.aleixballetbo.emarket.ProductList;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleixballetbo.emarket.ProductDetail.ProductDetailActivity;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForActivity;
import com.aleixballetbo.entities.Product;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final Context context;
    private List<Product> productList;
    private ProductListPresenter presenter;

    @Inject
    public ProductAdapter (@ForActivity Context context, ProductListPresenter presenter) {
        productList = new ArrayList<>();
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getName());
        String price = String.valueOf(productList.get(position).getPrice()) + "€";
        holder.productPrice.setText(price);
        holder.bindClick(productList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setData (List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.productPrice)
        TextView productPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindClick(final String id) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailActivity.actionOpenDetails(context, id, null);
                }
            });
        }
    }
}
