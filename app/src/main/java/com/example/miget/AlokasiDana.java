package com.example.miget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AlokasiDana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alokasi_dana);

        ImageView close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });

        final myDbAdapter helper = new myDbAdapter(this);
        final EditText tottabungan = (EditText) findViewById(R.id.tottabungan);
        final EditText bulanan = (EditText) findViewById(R.id.bulanan);
        final EditText investasi = (EditText) findViewById(R.id.investasi);
        String MyPREFERENCES = "MyPrefs" ;
        final SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final String username = sharedpreferences.getString("Name", "");
        final String email = sharedpreferences.getString("Email", "");

        View.OnClickListener join = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stottabungan=tottabungan.getText().toString();
                String sbulanan=bulanan.getText().toString();
                String sinvestasi=investasi.getText().toString();
                if (stottabungan.isEmpty() || sbulanan.isEmpty() || sinvestasi.isEmpty()){
                    Message.message(getApplicationContext(),"Fields Must Not Empty");
                } else {
                    long ret=helper.insertTabungan(username,stottabungan,sbulanan,sinvestasi);
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "Dana Teralokasikan!");
                    startActivity(new Intent(AlokasiDana.this, Dashboard.class).putExtras(bundle));
                }
            }
        };

        TextView alokasi = (TextView) findViewById(R.id.button2);
        alokasi.setOnClickListener(join);
    }
}
