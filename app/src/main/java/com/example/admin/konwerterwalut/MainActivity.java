package com.example.admin.konwerterwalut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {

        switch (view.getId())
        {
            case R.id.button:
                Intent intentEU = new Intent(MainActivity.this, EurosActivity.class);
                startActivity(intentEU);
                Toast.makeText(getApplicationContext(), "Wybierasz Euro...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Intent intentDOL = new Intent(MainActivity.this, DolarActivity.class);
                Toast.makeText(getApplicationContext(), "Wybierasz Dolary...", Toast.LENGTH_SHORT).show();
                startActivity(intentDOL);
                break;
        }
    }
}
