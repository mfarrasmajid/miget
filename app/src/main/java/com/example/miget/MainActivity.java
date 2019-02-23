package com.example.miget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SIGN UP
        SpannableString ss = new SpannableString("Don't have an account? Sign Up");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 22, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.signup);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);

        // SHOW PASSWORD
        CheckBox checkbox = (CheckBox) findViewById(R.id.showpassword);
        final EditText edtPassword = (EditText) findViewById(R.id.password);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        final myDbAdapter helper = new myDbAdapter(this);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        String MyPREFERENCES = "MyPrefs" ;
        final SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("Name", "Name");
        editor.putString("Email", "Email");
        editor.commit();
        View.OnClickListener signin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername=username.getText().toString();
                String spassword=password.getText().toString();
                if (susername.isEmpty() || spassword.isEmpty()){
                    Message.message(getApplicationContext(),"Username and Password Must Not Empty");
                } else {
                    String[] ret=helper.getLogin(susername,spassword);
                    //String[] ret = {"abc","abc","abc"};
                    String success = "Success";
                    if(success.equals(ret[0]))
                    {
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("Name", ret[1]);
                        editor.putString("Email", ret[3]);
                        editor.commit();
                        //Message.message(getApplicationContext(),ret[0]);
                        startActivity(new Intent(MainActivity.this, Dashboard.class));

                    }
                    else{
                        Message.message(getApplicationContext(),ret[0]);
                    }
                }
            }
        };

        TextView signin1 = (TextView) findViewById(R.id.signin);
        signin1.setOnClickListener(signin);
    }
}
