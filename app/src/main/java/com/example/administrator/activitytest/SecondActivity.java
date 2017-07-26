package com.example.administrator.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        // 获取数据
        /*Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d("SecondActivity", data);*/

        Button button2 = (Button) findViewById(R.id.button_2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // (new SecondActivity()).button2ClickCB(new Intent()); // 使用这种方式，数据并不能被 FirstActivity 获取，尽管不会报错
                Intent intent = new Intent();
                intent.putExtra("data_return", "hello FirstActivity");
                setResult(RESULT_OK, intent);
                finish();
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
}
