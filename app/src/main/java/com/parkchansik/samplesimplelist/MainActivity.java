package com.parkchansik.samplesimplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;// 어답터배열을 만드는것
    EditText inputView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (EditText) findViewById(R.id.edit_input);
        Button btn = (Button) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputView.getText().toString();//텍스트를 입력창으로부터 가져오는것
                mAdapter.add(text);//텍스트를 어답터에 추가하는것
                listView.smoothScrollToPosition(mAdapter.getCount() - 1);//리스트의 맨뒤로 추가하는것
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) listView.getItemAtPosition(position);
                //리스트뷰의 포지션을 받아서 거기에 있는 텍스트를 받아옴
                Toast.makeText(MainActivity.this, "text" + text, Toast.LENGTH_SHORT).show();
            }
        });
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //싱글초이스를 할떄 사용하는것
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);
        //멀티플 초이스를 할때 사용하는것
        listView.setAdapter(mAdapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //다중서택을 할떄 쓰는것
        initData();

        btn=(Button)findViewById(R.id.btn_choice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getChoiceMode()==ListView.CHOICE_MODE_SINGLE){
                    //초이스 모드가 싱글이라면
                    int position=listView.getCheckedItemPosition();
                    String text=(String)listView.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this,"Text"+text, Toast.LENGTH_SHORT).show();
                }else if(listView.getChoiceMode()==ListView.CHOICE_MODE_MULTIPLE){
                    SparseBooleanArray selection=listView.getCheckedItemPositions();
                    StringBuilder sb=new StringBuilder();
                    //추가적인 스트링을 더할려면 빌더 변경할려면 버퍼
                    for(int index=0;index<selection.size();index++){
                        int position=selection.keyAt(index);
                        if(selection.get(position)){
                            String text=(String)listView.getItemAtPosition(position);
                            sb.append(text).append(",");
                        }
                    }
                    Toast.makeText(MainActivity.this,"choice"+sb.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void initData() {
        String[] items = getResources().getStringArray(R.array.list_item);
        for (String s : items) {
            mAdapter.add(s);
        }
    }
}