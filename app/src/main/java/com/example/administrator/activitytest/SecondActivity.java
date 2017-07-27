package com.example.administrator.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log.d(TAG, "onCreate: " + this.toString());  // singleTop模式
        // Log.d(TAG, "onCreate: Task id is " + getTaskId());  // singleInstance模式
        setContentView(R.layout.second_layout);

        // 获取数据
        Intent intent = getIntent();
        String data = intent.getStringExtra("param1");
        Log.d(TAG, "data: " + data);

        Button button2 = (Button) findViewById(R.id.button_2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // (new SecondActivity()).button2ClickCB(new Intent()); // 使用这种方式，数据并不能被 FirstActivity 获取，尽管不会报错
                /*Intent intent = new Intent();
                intent.putExtra("data_return", "hello FirstActivity");
                setResult(RESULT_OK, intent);
                finish();*/

                // 研究singleTop模式
                // Intent intent = new Intent(SecondActivity.this, FirstActivity.class);

                // 研究singleInstance模式
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                startActivity(intent);
            }
        });
    }

    protected void button2ClickCB(Intent intent) {
        intent.putExtra("data_return", "hello FirstActivity");
        setResult(RESULT_OK, intent);
    }

    @Override
    public void onBackPressed() {
        this.button2ClickCB(new Intent());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: singleTask");
    }

    /**
     * 设置SecondActivity中获取的参数数据
     * @param context   启动者上下文
     * @param data1     数据1
     * @param data2     数据2
     */
    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

}
