package com.example.testprocessing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

import processing.android.CompatUtils;
import processing.android.PFragment;
import processing.core.PApplet;
import processing.core.PVector;

public class MainActivity extends AppCompatActivity {
    private PApplet sketch;
    Button btnGraphNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frame = (FrameLayout) findViewById(R.id.container);

        Sketch sketch = new Sketch();
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, this);


        btnGraphNow = findViewById(R.id.btnGraphNow);
        btnGraphNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DesmosWebView.class);
                Bundle extra = new Bundle();
                ArrayList<PVector> drawing = sketch.getDrawing();
                extra.putSerializable("drawing", drawing);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (sketch != null) {
            sketch.onRequestPermissionsResult(
                    requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (sketch != null) {
            sketch.onNewIntent(intent);
        }
    }
}