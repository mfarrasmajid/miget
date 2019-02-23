package com.example.miget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity {

    BarChart barChart;
    private Spinner spinner;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        barChart = (BarChart) findViewById(R.id.barchart);

        barChart.setDrawBarShadow(false);
        //barChart.setDrawValueAboveBar(false);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(true);
        barChart.setFitBars(true);
        barChart.setDrawGridBackground(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1,6000000f));
        barEntries.add(new BarEntry(2,6000000f));
        barEntries.add(new BarEntry(3,6000000f));
        barEntries.add(new BarEntry(4,6000000f));
        barEntries.add(new BarEntry(5,6000000f));
        //barEntries.add(new BarEntry(6,6000000f));
        //barEntries.add(new BarEntry(7,6000000f));
        //barEntries.add(new BarEntry(8,6000000f));
        //barEntries.add(new BarEntry(9,6000000f));
        //barEntries.add(new BarEntry(10,6000000f));
        //barEntries.add(new BarEntry(11,6000000f));
        //barEntries.add(new BarEntry(0,6000000f));

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        barEntries1.add(new BarEntry(1,2000000f));
        barEntries1.add(new BarEntry(2,1000000f));
        barEntries1.add(new BarEntry(3,2000000f));
        barEntries1.add(new BarEntry(4,4000000f));
        barEntries1.add(new BarEntry(5,3000000f));
        //barEntries1.add(new BarEntry(6,2000000f));
        //barEntries1.add(new BarEntry(7,3000000f));
        //barEntries1.add(new BarEntry(8,7000000f));
        //barEntries1.add(new BarEntry(9,5000000f));
        //barEntries1.add(new BarEntry(10,2000000f));
        //barEntries1.add(new BarEntry(11,3000000f));
        //barEntries1.add(new BarEntry(0,4000000f));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Income");
        barDataSet.setColors(new int[] {0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2, 0xFF50d2c2});

        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"Outcome");
        barDataSet1.setColors(new int[] {0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53, 0xFFfcab53});

        BarData data = new BarData(barDataSet, barDataSet1);
        data.setDrawValues(false);

        float groupSpace = 0.1f;
        float barSpace = 0.02f;
        float barWidth = 0.43f;

        barChart.setData(data);

        data.setBarWidth(barWidth);
        barChart.groupBars(1,groupSpace,barSpace);



        String[] months = new String[] {"Jan","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);

        spinner2 = findViewById(R.id.month);

        List<String> categories2 = new ArrayList<>();
        categories2.add(0, "Januari 2019");
        categories2.add("Februari 2019");
        categories2.add("Maret 2019");
        categories2.add("April 2019");
        categories2.add("Mei 2019");
        categories2.add("Juni 2019");
        categories2.add("Juli 2019");
        categories2.add("Agustus 2019");
        categories2.add("September 2019");
        categories2.add("Oktober 2019");
        categories2.add("November 2019");
        categories2.add("Desember 2019");


        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories2);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataAdapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Januari 2019")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();

                    //Toast.makeText(parent.getContext(), "Selected :" + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner = findViewById(R.id.year);

        List<String> categories = new ArrayList<>();
        categories.add(0, "2019");
        categories.add("2018");
        categories.add("2017");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("2019")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected :" + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });


    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;
        public MyAxisValueFormatter(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis){
            return mValues[(int)value];
        }
    }
}
