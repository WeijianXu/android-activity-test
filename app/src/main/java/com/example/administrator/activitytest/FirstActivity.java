package com.example.administrator.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log.d(TAG, "onCreate: " + this.toString());  // singleTop模式
        // Log.d(TAG, "onCreate: Task id is " + getTaskId());  // singleInstance模式
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(FirstActivity.this, "You clicked button 1", Toast.LENGTH_SHORT).show();
                // finish();    // 结束活动，返回主界面，相当于按 home 键

                // Intent intent = new Intent(FirstActivity.this, SecondActivity.class); // （显式Intent）启动第二个活动

                /*Intent intent = new Intent("com.example.administrator.activitytest.ACTION_START"); // （隐式Intent）启动指定活动，活动注册在Manifest中
                intent.addCategory("com.example.administrator.activitytest.MY_CATEGORY");*/

                /*Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));*/

                // 调用系统打电话功能
                /*Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));*/

                // 传递数据
                /*String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);*/

                // 研究standard模式
                // Intent intent = new Intent(FirstActivity.this, FirstActivity.class);

                // 研究singleTop模式
                // Intent intent = new Intent(FirstActivity.this, SecondActivity.class);

                // startActivity(intent);

                /*Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);*/

                // 利用SecondActivity活动的actionStart方法来启动，并传递参数，使之代码精简，逻辑清晰
                // 推荐以后开发使用这种模式
                SecondActivity.actionStart(FirstActivity.this, "data1", "data2");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d(TAG, "onActivityResult: " + returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: singleTask");
    }

}
