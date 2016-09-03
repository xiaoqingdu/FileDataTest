package com.example.xiaoqingdu.filedatatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_write=(Button)findViewById(R.id.btn_write);
        Button btn_read=(Button)findViewById(R.id.btn_read);
        final EditText edit_name=(EditText)findViewById(R.id.edit_name);
        final EditText edit_num=(EditText)findViewById(R.id.edit_num);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream outputStream=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput("MyfileName",MODE_PRIVATE);
                    outputStream=new BufferedOutputStream(fileOutputStream);
                    String name=edit_name.getText().toString();
                    String num=edit_num.getText().toString();
                    try{
                        outputStream.write(name.getBytes(StandardCharsets.UTF_8));
                        outputStream.write(num.getBytes(StandardCharsets.UTF_8));
                    }finally {
                        if(outputStream!=null)
                            outputStream.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"提交成功",Toast.LENGTH_LONG).show();
            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream=null;
                try {
                    FileInputStream fileInputStream=openFileInput("MyfileName");
                    inputStream=new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder builder=new StringBuilder("");
                    try {
                        while ((c=inputStream.read())!=-1){
                            builder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,builder.toString(),Toast.LENGTH_LONG).show();
                    }finally {
                        if(inputStream!=null){
                            inputStream.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
