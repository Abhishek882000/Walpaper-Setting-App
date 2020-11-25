package com.abhishek.livewalpaper;

import android.Manifest;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FullScreen extends AppCompatActivity {

    ImageView imageView;
    Button button,download;
    OutputStream outputStream;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ActivityCompat.requestPermissions(FullScreen.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        imageView=findViewById(R.id.full);
        button=findViewById(R.id.wallpaper);
        download=findViewById(R.id.download);
        alertDialog=new AlertDialog.Builder(FullScreen.this).create();
        alertDialog.setTitle("Download path...");
        alertDialog.setMessage("Image download in Phone Memory/Theme");

        final Intent intent=getIntent();
        imageView.setImageResource(intent.getIntExtra("name",0));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();

                WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "Set Wallpaper Successfully ", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Wallpaper not load yet!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                BitmapDrawable drawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap=drawable.getBitmap();

                File filepath= Environment.getExternalStorageDirectory();
                File dir=new File(filepath.getAbsolutePath()+"/Theme/");
                dir.mkdirs();
                File file=new File(dir,System.currentTimeMillis()+".jpg");
                try {
                    outputStream=null;
                    outputStream=new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    Toast.makeText(getApplicationContext(), "Image Download Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
