package app.smartBilling.com.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import app.smartBilling.com.R;


public class ScanningActivity extends Activity {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        initiateScanning();
    }

    private void initiateScanning() {
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(ScanningActivity.this);
                scanIntegrator.initiateScan();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String scanContent="";
        String scanFormat="";
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            scanContent = scanningResult.getContents();
            scanFormat= scanningResult.getFormatName();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

        formatTxt.setText("FORMAT: " + scanFormat);
        contentTxt.setText("CONTENT: " + scanContent);
    }
}
