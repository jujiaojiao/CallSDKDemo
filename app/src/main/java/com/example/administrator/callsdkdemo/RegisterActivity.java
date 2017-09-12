package com.example.administrator.callsdkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EventHandler eventHandler;
    private EditText userPhone;
    private EditText check_code;
    private Button send_code;
    private Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initSMSSD();
        initView();
        initListener();
    }
    private void initView(){
        userPhone = ((EditText) findViewById(R.id.edit_user_phone));
        check_code = ((EditText) findViewById(R.id.check_code));
        send_code = ((Button) findViewById(R.id.send_code));
        commit = ((Button) findViewById(R.id.commit));
    }
    private void initListener(){
        send_code.setOnClickListener(this);
        commit.setOnClickListener(this);
    }
    private void initSMSSD(){
         eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Toast.makeText(RegisterActivity.this, "发生范德萨发", Toast.LENGTH_SHORT).show();
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    String msg = throwable.getMessage();
                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
            // 处理你自己的逻辑
                        Toast.makeText(RegisterActivity.this, "处理逻辑", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "验证错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

//        // 注册监听器
//        SMSSD
    @Override
    public void onClick(View v) {
        String  phoneNum = userPhone.getText().toString();
        String code = check_code.getText().toString();
        switch (v.getId()) {
            case R.id.send_code:
                if (phoneNum==null||phoneNum.length()==0){
                    Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode("86",phoneNum, null);
                break;
            case R.id.commit:
                SMSSDK.submitVerificationCode("86", phoneNum, code);
                break;
        }
    }
    protected void onDestroy() {
         super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
