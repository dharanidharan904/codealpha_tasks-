package com.example.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditActivity extends AppCompatActivity {

    EditText editQuestion,editAnswer;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_add_edit);

        editQuestion=findViewById(R.id.editQuestion);
        editAnswer=findViewById(R.id.editAnswer);
        Button btnSave=findViewById(R.id.btnSave);

        editQuestion.setText(getIntent().getStringExtra("question"));
        editAnswer.setText(getIntent().getStringExtra("answer"));

        btnSave.setOnClickListener(v->{
            Intent data=new Intent();
            data.putExtra("question",editQuestion.getText().toString());
            data.putExtra("answer",editAnswer.getText().toString());
            setResult(Activity.RESULT_OK,data);
            finish();
        });
    }
}