package app.smartBilling.com.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.smartBilling.com.R;
import app.smartBilling.com.SmartBillingApplication;
import app.smartBilling.com.adapter.BillingListAdapter;

/**
 * Created by vikas on 01/08/15.
 */
public class ProductBillingActivity extends Activity {

    private BillingListAdapter billingListAdapter;
    private ListView listViewBilling;
    private TextView textViewTotal;

    @Override
    public void onCreate(Bundle view){
        super.onCreate(view);
        setContentView(R.layout.activity_billing);
        init();

    }

    private void init() {
        textViewTotal = (TextView)findViewById(R.id.txtBillingTotal);
        listViewBilling = (ListView)findViewById(R.id.listViewBilling);
        billingListAdapter = new BillingListAdapter(getApplicationContext(), SmartBillingApplication.productList);
        listViewBilling.setAdapter(billingListAdapter);
        setTotalPrice();

    }

    public void setTotalPrice(){
        double total = 0;
        for (int i = 0; i< SmartBillingApplication.productList.size();++i){
            total = total+Double.parseDouble(SmartBillingApplication.productList.get(i).getProduct().getPrice());
        }

        textViewTotal.setText(getApplicationContext().getResources().getString(R.string.Rs) + String.valueOf(total));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_billing, menu);
        MenuItem settings = menu.findItem(R.id.btnPayBill);
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnPayBill:
                startPayBill();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startPayBill() {

    }
}
