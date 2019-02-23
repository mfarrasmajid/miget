package com.example.miget;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // SIGN IN
        SpannableString ss = new SpannableString("Already have an account? Sign In");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }   };
        ss.setSpan(clickableSpan, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.signin2);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);

        final myDbAdapter helper = new myDbAdapter(this);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        View.OnClickListener join = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername=username.getText().toString();
                String spassword=password.getText().toString();
                String semail=email.getText().toString();
                if (susername.isEmpty() || spassword.isEmpty() || semail.isEmpty()){
                    Message.message(getApplicationContext(),"Fields Must Not Empty");
                } else {
                    long ret=helper.insertData(susername,spassword,semail);
                    if(ret != 0) {
                        Message.message(getApplicationContext(),"User Added!");
                        username.setText("");
                        password.setText("");
                        email.setText("");
                    }
                    else{
                        Message.message(getApplicationContext(),"User Already Exist");
                    }
                }
            }
        };

        TextView alokasi = (TextView) findViewById(R.id.join);
        alokasi.setOnClickListener(join);


    }
}
