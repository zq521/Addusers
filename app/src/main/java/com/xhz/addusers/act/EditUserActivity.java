package com.xhz.addusers.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.xhz.addusers.R;

/**
 * 数据库操作处理
 */

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 因为数据库中的_id自增列的值不可能为0，它是从1开始自增的，
     * 所以如果获取到的_id值是0，则代表外界并没有传入_id的值，外界可认定为是新增，而不是修改
     */
    private static final int USER_ID_DEFAULT_VALUE = 0;
    private EditText nameInput, ageInput;
    public static final int RESULT_CLOSE = 2;
    public static final int RESULT_SAVE = 3;
    public static final String KEY_USER_NAME = "nameInput";
    public static final String KEY_USER_AGE = "ageInput";
    public static final String KEY_USER_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameInput = (EditText) findViewById(R.id.input_name);
        ageInput = (EditText) findViewById(R.id.input_age);

        //接收传过来的数据
        nameInput.setText(getIntent().getStringExtra(KEY_USER_NAME));
        int age=getIntent().getIntExtra(KEY_USER_AGE,-1);
        if (age>-1){
            ageInput.setText(String.valueOf(age));
        }

        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                setResultTOCloseState();
                finish();
                break;
            case R.id.btn_save:

                Intent data = new Intent();
                data.putExtra(KEY_USER_ID,getIntent().getIntExtra(KEY_USER_ID,USER_ID_DEFAULT_VALUE));
                data.putExtra(KEY_USER_NAME, nameInput.getText().toString());
                data.putExtra(KEY_USER_AGE, Integer.parseInt(ageInput.getText().toString()));
                setResult(RESULT_SAVE, data);
                finish();
                break;
        }

    }

    private void setResultTOCloseState() {
        setResult(RESULT_CLOSE);
    }

    @Override
    public void onBackPressed() {
        setResultTOCloseState();
        super.onBackPressed();
    }
}
