package com.example.databsedemoapp;

import com.demodb.operation.EmployeeOperation;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateEmployeeDataActivity extends AppCompatActivity implements
		OnClickListener {
	private Button mBtnUpdate;
	private EditText mEdtEmpId, mEdtMobNo;
	private Toolbar mToolBar;
	private TextView mTxtTitle;
	private EmployeeOperation mEmpOperation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		initViews();
	}

	@SuppressLint("NewApi")
	private void initViews() {
		mEmpOperation = new EmployeeOperation(this);
		mToolBar = (Toolbar) findViewById(R.id.tool_bar);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		mEdtEmpId = (EditText) findViewById(R.id.edt_empId);
		mEdtMobNo = (EditText) findViewById(R.id.edt_emp_phno);
		mBtnUpdate = (Button) findViewById(R.id.btn_update);
		mBtnUpdate.setOnClickListener(this);
		if (mToolBar != null) {
			setSupportActionBar(mToolBar);
			mTxtTitle.setText("UpDate Empolyee MobileNo");
			mToolBar.setTitleTextColor(0xFFFFFFFF);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			final Drawable upArrow = getResources().getDrawable(
					R.drawable.abc_ic_ab_back_mtrl_am_alpha,
					getApplicationContext().getTheme());
			upArrow.setColorFilter(getResources().getColor(R.color.white),
					PorterDuff.Mode.SRC_ATOP);
			getSupportActionBar().setHomeAsUpIndicator(upArrow);
		} else {
			final Drawable upArrow = getResources().getDrawable(
					R.drawable.abc_ic_ab_back_mtrl_am_alpha);
			upArrow.setColorFilter(getResources().getColor(R.color.white),
					PorterDuff.Mode.SRC_ATOP);
			getSupportActionBar().setHomeAsUpIndicator(upArrow);
		}

		mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_update:
			if ((mEdtEmpId != null && mEdtEmpId.length() > 0)
					&& (mEdtMobNo != null && mEdtMobNo.length() > 0)) {
				String response = mEmpOperation.updateMobileNo(mEdtEmpId
						.getText().toString(), mEdtMobNo.getText().toString());
				if (response != null && !response.equalsIgnoreCase("0")) {
					Toast.makeText(getApplicationContext(), "Update Success",
							Toast.LENGTH_SHORT).show();
				}
				if (response != null && response.equalsIgnoreCase("0")) {
					Toast.makeText(getApplicationContext(),
							"No Record Match For the Update",
							Toast.LENGTH_SHORT).show();
				}
				Log.i(UpdateEmployeeDataActivity.class.toString(),
						"UpdateEmployeeDataActiivty Response" + response);
			}
			break;

		default:
			break;
		}

	}
}
