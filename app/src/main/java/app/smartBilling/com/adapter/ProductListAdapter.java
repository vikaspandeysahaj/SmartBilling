package app.smartBilling.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.smartBilling.com.R;
import app.smartBilling.com.model.Product;
import app.smartBilling.com.model.ScannedProduct;

/**
 * Created by vikas on 31/07/15.
 */
public class ProductListAdapter extends BaseAdapter {

    private Context context;
    private List<ScannedProduct> products = new ArrayList<ScannedProduct>();
    private static LayoutInflater inflater=null;

    public ProductListAdapter(Context context, List<ScannedProduct> productList) {
        this.context = context;
        this.products = productList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public ScannedProduct getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {
            view = inflater.inflate(R.layout.view_product_list, parent, false);
        }
        TextView txtProduct = (TextView)view.findViewById(R.id.txtProduct);
        TextView txtCompany = (TextView)view.findViewById(R.id.txtCompany);
        TextView txtPrice = (TextView)view.findViewById(R.id.txtPrice);
        TextView txtQuantity = (TextView)view.findViewById(R.id.txtQuantity);

        Product product = products.get(i).getProduct();

        txtProduct.setText(product.getTitle());
        txtCompany.setText(product.getCompany());
        txtPrice.setText(context.getResources().getString(R.string.Rs) +" "+product.getPrice());
        txtQuantity.setText(String.valueOf(products.get(i).getQuantity()));

        return view;
    }
}
