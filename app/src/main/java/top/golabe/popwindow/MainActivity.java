package top.golabe.popwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.golabe.popwindowsview.PopWindowView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void leftTop(View view) {
        List<String> dataList = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            dataList.add(String.valueOf(i));
        }
        new PopWindowView.Builder(this,view)
                .setItems(dataList)
                .setItemClickListener(new PopWindowView.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopWindowView pop, AdapterView<?> parent, View v, int position, long id) {
                        pop.dismiss();
                    }
                })
                .show();
    }

    public void rightTop(View view) {
        List<String> dataList = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            dataList.add(String.valueOf(i));
        }
        new PopWindowView.Builder(this,view)
                .setItems(dataList)
                .setItemClickListener(new PopWindowView.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopWindowView pop, AdapterView<?> parent, View v, int position, long id) {
                        pop.dismiss();
                    }
                })
                .show();
    }

    public void leftBottom(View view) {
        List<String> dataList = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            dataList.add(String.valueOf(i));
        }
        new PopWindowView.Builder(this,view)
                .setItems(dataList)
                .setItemClickListener(new PopWindowView.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopWindowView pop, AdapterView<?> parent, View v, int position, long id) {
                        pop.dismiss();
                    }
                })
                .show();
    }

    public void rightBottom(View view) {
        List<String> dataList = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            dataList.add(String.valueOf(i));
        }
        new PopWindowView.Builder(this,view)
                .setItems(dataList)
                .setItemClickListener(new PopWindowView.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopWindowView pop, AdapterView<?> parent, View v, int position, long id) {
                        Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
