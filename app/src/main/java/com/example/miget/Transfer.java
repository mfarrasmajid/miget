package com.example.miget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Transfer extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        spinner = findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Pilih Bank");
        categories.add("BTPN");
        categories.add("Mandiri");
        categories.add("BCA");
        categories.add("BNI");
        categories.add("BRI");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Pilih Bank"))
                {

                }

                else
                    {
                        String item = parent.getItemAtPosition(position).toString();

                        Toast.makeText(parent.getContext(),"Selected :" + item, Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2 = findViewById(R.id.spinner2);

        List<String> categories2 = new ArrayList<>();
        categories2.add(0, "Pilih Alokasi Dana");
        categories2.add("Monthly");
        categories2.add("Investasi");


        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories2);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataAdapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Pilih Alokasi Dana")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected :" + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final myDbAdapter helper = new myDbAdapter(this);
        final Spinner alokasi = (Spinner) findViewById(R.id.spinner2);
        final EditText nominal= (EditText) findViewById(R.id.nominal);
        String MyPREFERENCES = "MyPrefs" ;
        final SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final String username = sharedpreferences.getString("Name", "");
        final String email = sharedpreferences.getString("Email", "");
        View.OnClickListener transfer= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snominal =nominal.getText().toString();
                String salokasi = alokasi.getSelectedItem().toString();
                if (snominal.isEmpty() || salokasi.equals("Pilih Alokasi Dana")){
                    Message.message(getApplicationContext(),"Field must not be empty!");
                } else {
                    int ret = helper.checkNominal(username,salokasi,snominal);
                    if (ret == 0){
                        Message.message(getApplicationContext(),"Nominal anda kurang untuk melakukan transaksi!");
                    } else {
                        int ret2 = helper.transferNominal(username,salokasi,snominal);
                        if (ret2 == 0){
                            Message.message(getApplicationContext(),"Error");
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("message", "Transfer berhasil!");
                            startActivity(new Intent(Transfer.this, Dashboard.class).putExtras(bundle));
                        }
                    }
                }
            }
        };

        Button signin1 = (Button) findViewById(R.id.button2);
        signin1.setOnClickListener(transfer);

        ImageView close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });
    }
}
