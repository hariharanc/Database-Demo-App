package com.example.databsedemoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demodb.operation.EmployeeOperation;
import com.demodb.utils.EmpolyeeDetails;

public class AddEmployeeActivity extends AppCompatActivity implements
		OnClickListener {
	private Toolbar mToolBar;
	private EditText mEdtWriteName, mEdtWriteEmpId, mEdtWriteEmpMobNo;
	private Button mBtnAdd;
	private TextView mTxtTitle;
	private String mStrEmail;
	private EmployeeOperation mEmpOperation;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_employee_data);
		initViews();
	}

	@SuppressLint("NewApi")
	private void initViews() {
		mEmpOperation = new EmployeeOperation(this);
		mToolBar = (Toolbar) findViewById(R.id.tool_bar);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		if (mToolBar != null) {
			setSupportActionBar(mToolBar);
			mTxtTitle.setText("Insert Operation");
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
		mEdtWriteName = (EditText) findViewById(R.id.edt_write_name);
		mEdtWriteEmpId = (EditText) findViewById(R.id.edt_write_empId);

		mEdtWriteEmpMobNo = (EditText) findViewById(R.id.edt_write_emp_mobNo);
		mBtnAdd = (Button) findViewById(R.id.btn_add);
		mBtnAdd.setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(AddEmployeeActivity.class.toString(), "tag onactivity result"
				+ resultCode);
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				finish();
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			if ((mEdtWriteName != null && mEdtWriteName.length() > 0)
					&& (mEdtWriteEmpId != null && mEdtWriteEmpId.length() > 0)
					&& (mEdtWriteEmpMobNo != null && mEdtWriteEmpMobNo.length() > 0)) {
				EmpolyeeDetails empDetails = new EmpolyeeDetails(mEdtWriteEmpId
						.getText().toString(), mEdtWriteName.getText()
						.toString(), mEdtWriteEmpMobNo.getText().toString());
				String response = mEmpOperation.insertEmpData(empDetails);
				if (response != null && response.equalsIgnoreCase("success")) {
					Toast.makeText(getApplicationContext(), "Added",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "Insertion failed",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				showAlertDialog("Empty",
						"Edit Text Feild Should Not Be Empty!...");
			}

			break;
		default:
			break;
		}

	}

	private void showAlertDialog(String title, String msg) {
		new AlertDialog.Builder(this).setTitle(title).setMessage(msg)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}
}
