package com.example.internship_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.andreilisun.swipedismissdialog.OnSwipeDismissListener;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDirection;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String title = "Slide to Cancel";
    int img=R.drawable.bb ;
    TextView text;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.textView3);
        img1 = (ImageView)findViewById(R.id.imageView3);


        OkHttpClient client = new OkHttpClient();

        String url = "https://backend-test-zypher.herokuapp.com";

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               if(response.isSuccessful()){

                   MainActivity.this.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           View dialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialouge_box,null);
                           new SwipeDismissDialog.Builder(MainActivity.this)
                                 .setView(dialog)
                                 .build()
                                 .show();
                       }
                   });

               }
            }
        });



    }

    public void Click(View view) {
        Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        startActivity(Getintent);
    }
}