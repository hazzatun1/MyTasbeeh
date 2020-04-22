package com.bandhan.hazzatun.mytasbeeh;

import android.app.Dialog;
import android.content.DialogInterface;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
  //  private static final String FILE_NAME = "exampleTasbeeh.txt";
    private int mcounter = 0;
    private SharedPreferences prefs;
    Button cnt;
    TextView txv;
    EditText et, etName;
    String value;
    boolean haveIBeenClicked;
    Database db;

    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = getSharedPreferences("auto.tasbeeh.data", MODE_PRIVATE);
        String strPref = prefs.getString("count", null);
        et = (EditText) findViewById(R.id.uput);
        cnt = (Button) findViewById(R.id.count);
        txv = (TextView) findViewById(R.id.txt);

        if (strPref != null) {
            txv.setText(prefs.getString("count", ""));
            value = txv.getText().toString();
            int mr = Integer.parseInt(value);
            txv.setText(String.valueOf(mcounter = mr));

        }
    }

    public void play(View view) {
        mcounter++;
        txv.setText(String.valueOf(mcounter));
    }

    public void resets(View view) {
        Button ret = (Button) findViewById(R.id.reset);
        txv.setText(String.valueOf(mcounter = 0));
    }

    public void edits(View view) {

       // Button ed = (Button) findViewById(R.id.edit);

        txv.setVisibility(txv.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        et.setVisibility(et.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

        value = et.getText().toString();
        int mr = Integer.valueOf(value);
        if (value != String.valueOf(0)) {
            et.setText((value = String.valueOf(mcounter)));
        }

        txv.setText(String.valueOf(mcounter = mr));
    }




    public void saves(View view) {

        //    Button sv = (Button) findViewById(R.id.save);

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View yourCustomView = inflater.inflate(R.layout.count_name, null);

        final TextView etName = (EditText) yourCustomView.findViewById(R.id.edit_countname);
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)

                .setTitle("Save as")

                .setMessage("Enter a name")
                .setView(yourCustomView)
                .setPositiveButton("Yes", null)

                .setNegativeButton("No", null)
                .show();
        Button positiveButton= dialog.getButton(dialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isinserted= db.addName(etName.getText().toString(), txv.getText().toString());
                        if(isinserted==true)
                            Toast.makeText(MainActivity.this,"Data inserted", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this,"Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
        Button negButton= dialog.getButton(dialog.BUTTON_NEGATIVE);
        negButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "No Happend", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }




    public void viewAll(View view) {
    }




        public void lt(View view) { //light
            haveIBeenClicked=!haveIBeenClicked;
          //  Button lt = findViewById(R.id.light);
            if(haveIBeenClicked){
                et.setTextColor(getResources().getColor(R.color.y));
                txv.setTextColor(getResources().getColor(R.color.y));
                cnt.setTextColor(getResources().getColor(R.color.y));
                LinearLayout layout =(LinearLayout)findViewById(R.id.lb);
                layout.setBackgroundResource(R.drawable.bl);
            }
            else{
                et.setTextColor(getResources().getColor(R.color.b));
                txv.setTextColor(getResources().getColor(R.color.b));
                cnt.setTextColor(getResources().getColor(R.color.b));
                LinearLayout layout =(LinearLayout)findViewById(R.id.lb);
                layout.setBackgroundResource(R.drawable.bk);

            }
        }
        @Override
        protected void onPause() {
            super.onPause();
            value=txv.getText().toString();
            prefs.edit().putString("count",value).apply();
        }

}


