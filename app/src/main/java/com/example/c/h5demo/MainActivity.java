package com.example.c.h5demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView mwebView;
    private Button mbtn;
    private WebAppInterface appInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mwebView= (WebView) findViewById(R.id.wv);
        mbtn= (Button) findViewById(R.id.btn);
        mbtn.setOnClickListener(this);

        mwebView.loadUrl("file:///android_asset/index.html");
        mwebView.getSettings().setJavaScriptEnabled(true);
        appInterface=new WebAppInterface(this);
        mwebView.addJavascriptInterface(appInterface,"app");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                appInterface.showName("约吧~~");
                break;
        }
    }

    class WebAppInterface{

        private Context context;

        public WebAppInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void sayHello(String name){
            Toast.makeText(context,"name="+name,Toast.LENGTH_SHORT).show();
        }

        public void showName(String name){
            mwebView.loadUrl("javascript:showName('"+name+"')");
        }
    }
}
