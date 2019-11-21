package com.zengleixin.cn.snake;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class gg extends Activity {
    DBManager dbManager = new DBManager(this);


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg);
//        Intent intent = getIntent();
//        final String newname = intent.getStringExtra("name");
        Button returngame = (Button) findViewById(R.id.returngame);
        Button delete = (Button) findViewById(R.id.bt_delete);

        final DBManager dbManager = new DBManager(this);
        String[] data = dbManager.allSql();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(gg.this,android.R.layout.simple_list_item_1,data);
        ListView lv_data=(ListView)findViewById(R.id.lv_data);
        lv_data.setAdapter(adapter);

        returngame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gg.this,Sign.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String newname = intent.getStringExtra("name");
               dbManager.delete(newname);
            }
        });
    }
}
