package com.example.databsedemoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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

public class DeleteEmployeeActivity extends AppCompatActivity implements
		OnClickListener {
	private Toolbar mToolBar;
	private EditText mEdtWriteEmpId;
	private Button mBtnDel;
	private TextView mTxtTitle;
	private EmployeeOperation mEmpOperation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_del_employee);
		initViews();
	}

	@SuppressLint("NewApi")
	private void initViews() {
		mEmpOperation = new EmployeeOperation(this);
		mToolBar = (Toolbar) findViewById(R.id.tool_bar);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		if (mToolBar != null) {
			setSupportActionBar(mToolBar);
			mTxtTitle.setText("Delete Operation");
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

		mEdtWriteEmpId = (EditText) findViewById(R.id.edt_write_empId);

		mBtnDel = (Button) findViewById(R.id.btn_del);
		mBtnDel.setOnClickListener(this);

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
		case R.id.btn_del:
			if ((mEdtWriteEmpId != null && mEdtWriteEmpId.length() > 0)) {
				String response = mEmpOperation.deleteEmployee(mEdtWriteEmpId
						.getText().toString());

				Log.i(DeleteEmployeeActivity.class.toString(),
						"DeleteEmployeeActivity Operation response" + response);
				if (response != null && response.equalsIgnoreCase("1")) {
					Toast.makeText(getApplicationContext(), "Delete Success",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"No Data Match For Delete Operation",
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
