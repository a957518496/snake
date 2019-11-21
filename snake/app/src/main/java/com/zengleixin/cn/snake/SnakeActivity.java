package com.zengleixin.cn.snake;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.zengleixin.cn.snake.SnakeView;

import java.util.HashMap;


public class SnakeActivity extends Activity {
    private static final String tag = "SnakeActivity";
    private SoundPool soundpool;
    private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();
    SnakeView view;
    DBManager dbManager = new DBManager(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundpool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 0);
        soundmap.put(1, soundpool.load(this, R.raw.ding, 1));
        soundmap.put(2, soundpool.load(this, R.raw.lose, 1));
        view = new SnakeView(this);
        setContentView(view);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        view.onKeyDown(keyCode, event);
        Intent intent = getIntent();
        String newname = intent.getStringExtra("name");
        if (view.isLose){
            soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);
            dbManager.Update(newname,Integer.toString(view.socre));
        Toast.makeText(this, "游戏结束用户"+newname+"得分为"+view.socre, Toast.LENGTH_LONG).show();}
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        view.onKeyUp(keyCode, event);
        Intent intent = getIntent();
        String newname = intent.getStringExtra("name");
        soundpool.play(soundmap.get(1), 1, 1, 0, 0, 1);
        if (view.isLose){
            soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);
            Intent intent2 = new Intent(SnakeActivity.this,gg.class);
            intent2.putExtra("name",newname);
            startActivity(intent2);
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        view.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(0, Menu.FIRST+1, 0, "开始").setShortcut('3', 'a');//设置快捷键
        menu.add(0, Menu.FIRST, 0, "重新开始").setShortcut('1', 'b');//设置快捷键
        menu.add(0, Menu.FIRST+2, 0, "背景音乐关闭").setShortcut('2', 'c');//设置快捷键
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        view.isPaused=true;//显示菜单时暂停游戏
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // TODO Auto-generated method stub
        view.isPaused=false;//关闭菜单时继续游戏
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case Menu.FIRST:
                if(view!=null){
                    view.setFocusable(true);
                    view.init();
                }
                break;
            case Menu.FIRST+1:
                view.isPaused=false;
                break;
            case Menu.FIRST+2:
                Music.stop(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}