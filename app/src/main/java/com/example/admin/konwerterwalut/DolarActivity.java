package com.example.admin.konwerterwalut;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DolarActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_dolar);
        btn1 = (Button) findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Konwertacja Zł na Usd...", Toast.LENGTH_SHORT).show();
                KonwertZłUsd();
                Button b4 =(Button) findViewById(R.id.button4);
                b4.setEnabled(false);
            }
        });

        btn2 = (Button) findViewById(R.id.button4);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Konwertacja Usd na Zł...", Toast.LENGTH_SHORT).show();
                KonwertUsdZł();
                Button b3 =(Button) findViewById(R.id.button3);
                b3.setEnabled(false);
            }
        });

        btn3 = (Button) findViewById(R.id.button5);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Resetowanie...", Toast.LENGTH_SHORT).show();
                Button b3=(Button) findViewById(R.id.button3);
                b3.setEnabled(true);
                Button b4=(Button) findViewById(R.id.button4);
                b4.setEnabled(true);
                EditText ZŁ= (EditText) findViewById(R.id.editText);
                EditText  USD=(EditText) findViewById(R.id.editText2);
                ZŁ.setText("");
                USD.setText("");
            }
        });


    }

    void KonwertZłUsd() {

        EditText editText= (EditText) findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        EditText  editText2=(EditText) findViewById(R.id.editText2);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER);

        try {
            Document doc = Jsoup.connect("https://internetowykantor.pl/kurs-dolara/").get();
            Elements kurspobrany = doc.select("span.kurs.kurs_sprzedazy");
            String kursformatowany = kurspobrany.text().replace(",", ".");
            double kurs = Double.parseDouble(kursformatowany);
            double kasa = Double.parseDouble(editText.getText().toString());

            double wynik = kasa / kurs;
            wynik *=100;
            wynik = Math.round(wynik);
            wynik /=100;
            editText2.setText(String.valueOf(wynik));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void KonwertUsdZł() {

        EditText editText= (EditText) findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        EditText  editText2=(EditText) findViewById(R.id.editText2);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER);

        try {
            Document doc = Jsoup.connect("https://internetowykantor.pl/kurs-dolara/").get();
            Elements kurspobrany = doc.select("span.kurs.kurs_kupna");
            String kursformatowany = kurspobrany.text().replace(",", ".");
            double kurs = Double.parseDouble(kursformatowany);
            double kasa = Double.parseDouble(editText2.getText().toString());

            double wynik = kasa * kurs;
            wynik *=100;
            wynik = Math.round(wynik);
            wynik /=100;
            editText.setText(String.valueOf(wynik));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}