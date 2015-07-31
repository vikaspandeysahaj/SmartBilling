package app.smartBilling.com.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.smartBilling.com.R;
import app.smartBilling.com.SmartBillingApplication;
import app.smartBilling.com.adapter.ProductListAdapter;
import app.smartBilling.com.controller.ProductController;
import app.smartBilling.com.model.Product;

/**
 * Created by vikas on 31/07/15.
 */
public class ProductListActivity  extends Activity {

    private SmartBillingApplication application;
    private ProductListAdapter productListAdapter;
    private ProductController productController;
    private ListView productListView;
    private TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        init();
        setProductListAdapter();
    }

    private void init() {
        textViewTotal = (TextView)findViewById(R.id.txtTotal);
        application = (SmartBillingApplication)getApplicationContext();
        productController = application.getProductController();
        productListAdapter = new ProductListAdapter(getApplicationContext(),SmartBillingApplication.productList);
        productListView= (ListView)findViewById(R.id.productListView);
    }

    private void setProductListAdapter() {
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void startScanningProduct() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(ProductListActivity.this);
        scanIntegrator.initiateScan();
    }

    public void setTotalPrice(){
        double total = 0;
        for (Product product : SmartBillingApplication.productList){
            total = total+Double.parseDouble(product.getPrice());
        }
        textViewTotal.setText(getApplicationContext().getResources().getString(R.string.Rs)+String.valueOf(total));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode==100009){
            productListAdapter.notifyDataSetChanged();
            setTotalPrice();
        }
        else {
            String scanContent = "";
            String scanFormat = "";
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null) {
                scanContent = scanningResult.getContents();
                scanFormat = scanningResult.getFormatName();
                Product product = productController.getProductByBarcode(scanFormat, scanContent);
                if (product != null) {
                    Intent intent1 = new Intent(this, ProductDetailsActivity.class);
                    intent1.putExtra("productId", product.getUuid());
                    startActivityForResult(intent1, 100009);
                } else {
                    Toast.makeText(getApplicationContext(), "No Product found!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shoping, menu);
        MenuItem settings = menu.findItem(R.id.btnNewConversation);
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnNewConversation:
                startScanningProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
