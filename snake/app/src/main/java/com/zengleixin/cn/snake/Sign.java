package com.zengleixin.cn.snake;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign extends Activity {


    static String name;
    private Button bt_register,bt_check;
    private EditText et_name,et_password;
    private SharedPreferences sp;
    public static final int REGISTER = 0;
    private static final String PREFS_NAME = "MyPrefsFile";
    DBManager dbManager = new DBManager(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        Music.play(this,R.raw.bg);
        bt_register= findViewById(R.id.bt_register);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        bt_check = findViewById(R.id.bt_check);



        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign.this,Register.class);
                startActivity(intent);
            }
        });


        bt_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname = et_name.getText().toString();
                String password = et_password.getText().toString();

                if (newname.equals("") || password.equals("")){
                    Toast.makeText(Sign.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Cursor cursor = dbManager.query(newname, password);
                    if (cursor.moveToNext()) {
                        Toast.makeText(Sign.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Sign.this, SnakeActivity.class);
                        intent.putExtra("name",newname);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Sign.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
