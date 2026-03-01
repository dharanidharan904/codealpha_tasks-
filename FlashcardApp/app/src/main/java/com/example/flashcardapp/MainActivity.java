package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textQuestion,textAnswer;
    ArrayList<Flashcard> list=new ArrayList<>();
    int index=0;

    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuestion=findViewById(R.id.textQuestion);
        textAnswer=findViewById(R.id.textAnswer);

        Button btnShow=findViewById(R.id.btnShow);
        Button btnNext=findViewById(R.id.btnNext);
        Button btnPrev=findViewById(R.id.btnPrev);
        Button btnAdd=findViewById(R.id.btnAdd);
        Button btnEdit=findViewById(R.id.btnEdit);
        Button btnDelete=findViewById(R.id.btnDelete);

        list.add(new Flashcard("What is Android?","Mobile OS"));

        showCard();

        launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode()==RESULT_OK){

                        String q=result.getData().getStringExtra("question");
                        String a=result.getData().getStringExtra("answer");

                        if(editMode)
                            list.set(index,new Flashcard(q,a));
                        else{
                            list.add(new Flashcard(q,a));
                            index=list.size()-1;
                        }
                        editMode=false;
                        showCard();
                    }
                });

        btnShow.setOnClickListener(v->
                textAnswer.setVisibility(View.VISIBLE));

        btnNext.setOnClickListener(v->{
            if(index<list.size()-1) index++;
            showCard();
        });

        btnPrev.setOnClickListener(v->{
            if(index>0) index--;
            showCard();
        });

        btnAdd.setOnClickListener(v->{
            editMode=false;
            launcher.launch(new Intent(this,AddEditActivity.class));
        });

        btnEdit.setOnClickListener(v->{
            editMode=true;
            Intent i=new Intent(this,AddEditActivity.class);
            i.putExtra("question",list.get(index).getQuestion());
            i.putExtra("answer",list.get(index).getAnswer());
            launcher.launch(i);
        });

        btnDelete.setOnClickListener(v->{
            if(list.size()>0){
                list.remove(index);
                if(index>0) index--;
                showCard();
            }
        });
    }

    boolean editMode=false;

    void showCard(){
        if(list.size()==0){
            textQuestion.setText("No Flashcards");
            textAnswer.setVisibility(View.GONE);
            return;
        }

        textQuestion.setText(list.get(index).getQuestion());
        textAnswer.setText(list.get(index).getAnswer());
        textAnswer.setVisibility(View.GONE);
    }
}