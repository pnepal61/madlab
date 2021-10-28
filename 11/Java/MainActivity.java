package com.example.ex11_hybridmobile_024;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView web;
    EditText url;
    Button userButton,urlButton,resetButton;
    private static final int INTERNET_CODE = 103;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web=findViewById(R.id.WebView1);
        url=findViewById(R.id.baseUrl);
        userButton=findViewById(R.id.userWebButton);
        urlButton=findViewById(R.id.printButton);
        resetButton=findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url.setText("");
                web.loadDataWithBaseURL(null,"","text/html","utf-8",null);

            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userHTML="<h1>USER HTML</h1>\n" +
                        "<p>The fruits examples:-\n" +
                        "<ul>\n" +
                        "    <li>Apple</li>\n" +
                        "    <li>Orange</li>\n" +
                        "    <li>Watermelon</li>\n" +
                        "</ul>\n" +
                        "<p>Here are the list of tags used:-\n" +
                        "<ol>\n" +
                        "    <li>Heading tags</li>\n" +
                        "    <li>Underline, bold and emphasis tags</li>\n" +
                        "    <li>Paragraph tags</li>\n" +
                        "    <li>ordered and unordered lists</li>\n" +
                        "</ol>";
                web.loadDataWithBaseURL(null,userHTML,"text/html","utf-8",null);
            }
        });
        urlButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                web.getSettings().setJavaScriptEnabled(true);
                String URL = url.getText().toString();
                web.loadUrl(URL);
            }
        });
    }
}
