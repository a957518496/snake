package com.zengleixin.cn.snake;


import android.app.Activity;
import android.content.Intent;;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    private Button bt_sign, bt_cancel;
    private EditText et_register_name, et_register_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final DBManager dbManager = new DBManager(this);

        et_register_name = findViewById(R.id.et_register_name);
        et_register_password = findViewById(R.id.et_register_password);
        bt_sign = findViewById(R.id.bt_sign);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_name = et_register_name.getText().toString();
                String et_word = et_register_password.getText().toString();

                Cursor cursor = dbManager.requery(et_name);
                if (cursor.moveToNext()){
                    Toast.makeText(Register.this,"该用户已被注册！",Toast.LENGTH_LONG).show();
                }else {
                    dbManager.insert(et_name, et_word, "0");
                    Intent intent = new Intent(Register.this, Sign.class);
                    startActivity(intent);
                }
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Sign.class);
                startActivity(intent);
            }
        });

    }
}
