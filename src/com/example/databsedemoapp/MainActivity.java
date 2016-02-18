package com.example.databsedemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.demodb.operation.EmployeeOperation;

public class MainActivity extends AppCompatActivity implements OnClickListener {
	private Toolbar mToolBar;
	private TextView mTxtToolBarTile;
	private Button mbtnInsert, mbtnSelect, mBtnUpdate, mBtnDelete,
			mBtnDelSpecific;
	private EmployeeOperation mEmpOperation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEmpOperation = new EmployeeOperation(this);

		initViews();
	}

	private void initViews() {
		mToolBar = (Toolbar) findViewById(R.id.toolBar);
		mbtnInsert = (Button) findViewById(R.id.btn_Insert);
		mbtnSelect = (Button) findViewById(R.id.btn_select);
		mBtnUpdate = (Button) findViewById(R.id.btn_update);
		mBtnDelete = (Button) findViewById(R.id.btn_delete);
		mBtnDelSpecific = (Button) findViewById(R.id.btn_del_specific);

		mbtnInsert.setOnClickListener(this);
		mbtnSelect.setOnClickListener(this);
		mBtnUpdate.setOnClickListener(this);
		mBtnDelete.setOnClickListener(this);
		mBtnDelSpecific.setOnClickListener(this);
		mTxtToolBarTile = (TextView) findViewById(R.id.txt_title);
		if (mToolBar != null) {
			setSupportActionBar(mToolBar);
			mTxtToolBarTile.setText("DB Operation");
			mToolBar.setTitleTextColor(0xFFFFFFFF);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
	}

	@Override
	public void onClick(View v) {
		Intent i = null;
		switch (v.getId()) {
		case R.id.btn_select:
			i = new Intent(MainActivity.this, GetEmployeeActivity.class);
			startActivity(i);
			break;
		case R.id.btn_Insert:
			i = new Intent(MainActivity.this, AddEmployeeActivity.class);
			startActivity(i);
			break;
		case R.id.btn_update:
			i = new Intent(MainActivity.this, UpdateEmployeeDataActivity.class);
			startActivity(i);
			break;
		case R.id.btn_delete:
			try {
				String response = mEmpOperation.deleteAllEmpData();
				if (response != null && response.equalsIgnoreCase("success")) {
					Toast.makeText(getApplicationContext(), "Deleted Success",
							Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				Log.e(MainActivity.class.toString(),
						"MainActivity Delete exp is" + e.getMessage());
			}
			break;

		case R.id.btn_del_specific:
			i = new Intent(MainActivity.this, DeleteEmployeeActivity.class);
			startActivity(i);

			break;
		default:
			break;
		}
	}

}
