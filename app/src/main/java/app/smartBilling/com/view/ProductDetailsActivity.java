package app.smartBilling.com.view;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import app.smartBilling.com.R;
import app.smartBilling.com.SmartBillingApplication;
import app.smartBilling.com.controller.ProductController;
import app.smartBilling.com.model.Product;
import app.smartBilling.com.model.ScannedProduct;

/**
 * Created by vikas on 01/08/15.
 */
public class ProductDetailsActivity extends Activity {

    private SmartBillingApplication application;
    private ProductController productController;
    private ScannedProduct product;
    private TextView txtProductTitle ;
    private TextView txtMrp;
    private TextView txtPrice;
    private TextView txtQuantity;
    private Button btnAddToList;
    private ImageButton btnAddOneMore;
    private ImageButton btnRemoveOneMore;

    @Override
    public void onCreate(Bundle view){
        super.onCreate(view);
        setContentView(R.layout.view_product_details);
        init();
        setProduct();
    }

    private void setProduct() {
        Product p = productController.getProductByUUID(getIntent().getStringExtra("productId"));
        product = new ScannedProduct(p,1);
        txtProductTitle.setText(product.getProduct().getTitle());
        txtMrp.setText(getApplicationContext().getResources().getString(R.string.Rs) + product.getProduct().getPrice());
        txtMrp.setPaintFlags(txtMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txtPrice.setText(getApplicationContext().getResources().getString(R.string.Rs) + product.getProduct().getPrice());
        txtQuantity.setText("1");

        btnAddOneMore = (ImageButton)findViewById(R.id.btnAddOneMore);
        btnAddOneMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(txtQuantity.getText().toString());
                count++;
                int mrp = Integer.parseInt(txtMrp.getText().toString()
                        .replace(getApplicationContext().getResources().getString(R.string.Rs),""));
                txtMrp.setText(getApplicationContext().getResources().getString(R.string.Rs)+String.valueOf(mrp*count));

                int price = Integer.parseInt(txtPrice.getText().toString()
                        .replace(getApplicationContext().getResources().getString(R.string.Rs), ""));
                txtPrice.setText(getApplicationContext().getResources().getString(R.string.Rs)+String.valueOf(price*count));
                txtQuantity.setText(String.valueOf(count));
            }
        });

        btnRemoveOneMore = (ImageButton)findViewById(R.id.btnRemoveOneMore);
        btnRemoveOneMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(txtQuantity.getText().toString());

                int mrp = Integer.parseInt(txtMrp.getText().toString()
                        .replace(getApplicationContext().getResources().getString(R.string.Rs), ""));
                txtMrp.setText(getApplicationContext().getResources().getString(R.string.Rs) + String.valueOf(mrp / count));

                int price = Integer.parseInt(txtPrice.getText().toString()
                        .replace(getApplicationContext().getResources().getString(R.string.Rs), ""));
                txtPrice.setText(getApplicationContext().getResources().getString(R.string.Rs) + String.valueOf(price / count));
                if (count > 1) {
                    count--;
                    txtQuantity.setText(String.valueOf(count));
                }

            }
        });

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                product.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));
                SmartBillingApplication.productList.add(product);
                finish();
            }
        });

    }

    private void init() {
        application = (SmartBillingApplication)getApplicationContext();
        productController = application.getProductController();
        txtProductTitle = (TextView)findViewById(R.id.txtProductMessageTitle);
        txtMrp = (TextView)findViewById(R.id.txtProductMessageMrp);
        txtPrice = (TextView)findViewById(R.id.txtProductMessagePrice);
        txtQuantity = (TextView)findViewById(R.id.txtProductQuantity);
        btnAddToList = (Button)findViewById(R.id.btnAdd);
    }
}
