package com.android.debtor;

import com.android.debtor.R.string;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DebtorActivity extends Activity {

	private Button btnTinhTien;
	private TextView tvResult;
	private EditText etSoNguoi, etTongTien;
	private static final String KEY_INDEX = "debtor_management";
	private static final String TAG = "debtor";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtor);
        
        btnTinhTien = (Button)findViewById(R.id.btnTinhTien);
        tvResult = (TextView)findViewById(R.id.tvRestult);
        etSoNguoi = (EditText)findViewById(R.id.etSoNguoi);
        etTongTien = (EditText)findViewById(R.id.etTongTien);
        
        
        
        btnTinhTien.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int tongTien = Integer.parseInt(etTongTien.getText().toString());				
				int soNguoi = Integer.parseInt(etSoNguoi.getText().toString());
				
				double x;
				int tongHeSo = 0;
				
				for (int i = 1; i <= soNguoi; i++) {
					tongHeSo += i;
				}
				
				x =  ((double)100/tongHeSo);
				
				int tong = 0;
				String ketQua = "";
				for (int j = 1; j <= soNguoi; j++) {
					int thanhTien = (int) (j * x * tongTien/100);
					int thanhTien_lamTronXuong = thanhTien/1000;//Chia 1000 de lam bo phan le
					thanhTien_lamTronXuong *= 1000;
					int phanLe = thanhTien - thanhTien_lamTronXuong;
//					if(phanLe <= 250)
//						thanhTien = thanhTien_lamTronXuong;
//					else if(phanLe >= 750) {
//						thanhTien = thanhTien_lamTronXuong + 1000;//Lam tron len 1000
//					} else
//						thanhTien = thanhTien_lamTronXuong + 500;
					ketQua += getString(R.string.prefix_result_detail) + " " + j  + " - " + String.format("%,d", thanhTien) + "\n";
					tong += thanhTien;
				}
				
				tvResult.setText(ketQua + "\n" + tong + "\nX: " + x + "\n" + (tongTien - tong));
				
			}
		});
        
        if(savedInstanceState != null) {
        	String result = savedInstanceState.getString(KEY_INDEX, "");
        	tvResult.setText(result);
        	
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
    	super.onSaveInstanceState(saveInstanceState);
    	Log.i(TAG, "onSaveInstanceState");
    	saveInstanceState.putString(KEY_INDEX, tvResult.getText().toString());
    	saveInstanceState.putString(KEY_INDEX, "value test");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.debtor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
