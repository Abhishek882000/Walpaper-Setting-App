package com.abhishek.livewalpaper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    private ProgressDialog progressDialog;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.mygrid);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        alertDialog=new AlertDialog.Builder(MainActivity.this).create();


        final Integer imageid[]={R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.cc,R.drawable.fourteen,
                R.drawable.hitesh, R.drawable.gati, R.drawable.snapdeal, R.drawable.aptrons, R.drawable.khushi, R.drawable.mohan, R.drawable.jha
                , R.drawable.computer,R.drawable.card, R.drawable.peoples,
                R.drawable.thumb1, R.drawable.thumb2,};
    mylistadapter mylistadapter=new mylistadapter(this,imageid);
    gridView.setAdapter(mylistadapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,FullScreen.class);
                intent.putExtra("name",imageid[position]);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {

        alertDialog.setMessage("Do you want to close the application ?");
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
       alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               alertDialog.cancel();
           }
       });
      alertDialog.setTitle(" Exit");
      alertDialog.show();








    }

}
