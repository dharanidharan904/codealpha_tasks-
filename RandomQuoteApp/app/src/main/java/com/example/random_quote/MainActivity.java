package com.example.random_quote;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvQuote, tvAuthor, tvTimer;
    Button btnNewQuote;

    ArrayList<QuoteModel> quoteList;
    Random random;

    Handler handler = new Handler();
    Runnable runnable;

    CountDownTimer countDownTimer;

    private static final int DELAY = 5000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tvQuote);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvTimer = findViewById(R.id.tvTimer);
        btnNewQuote = findViewById(R.id.btnNewQuote);

        quoteList = new ArrayList<>();
        random = new Random();

        loadQuotes();

        showRandomQuote();
        startAutoChange();

        btnNewQuote.setOnClickListener(v -> {
            showRandomQuote();
            resetTimer();
            startCountdown();
        });
    }

    private void showRandomQuote() {

        int index = random.nextInt(quoteList.size());
        QuoteModel quote = quoteList.get(index);

        Animation animation =
                AnimationUtils.loadAnimation(this, R.anim.fade_scale);

        tvQuote.startAnimation(animation);
        tvAuthor.startAnimation(animation);

        tvQuote.setText("\"" + quote.getQuote() + "\"");
        tvAuthor.setText("- " + quote.getAuthor());
    }

    private void startAutoChange() {

        runnable = new Runnable() {
            @Override
            public void run() {
                showRandomQuote();
                startCountdown();
                handler.postDelayed(this, DELAY);
            }
        };

        handler.postDelayed(runnable, DELAY);
        startCountdown();
    }

    private void resetTimer() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, DELAY);
    }

    private void startCountdown() {

        if (countDownTimer != null)
            countDownTimer.cancel();

        countDownTimer = new CountDownTimer(DELAY, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText(
                        "Next quote in " + millisUntilFinished / 1000 + "s"
                );
            }

            public void onFinish() {
                tvTimer.setText("Changing...");
            }
        }.start();
    }

    private void loadQuotes() {

        quoteList.add(new QuoteModel("Stay hungry, stay foolish.", "Steve Jobs"));
        quoteList.add(new QuoteModel("Be yourself; everyone else is already taken.", "Oscar Wilde"));
        quoteList.add(new QuoteModel("Dream big and dare to fail.", "Norman Vaughan"));
        quoteList.add(new QuoteModel("Success is not final.", "Winston Churchill"));
        quoteList.add(new QuoteModel("Believe you can and you're halfway there.", "Theodore Roosevelt"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnable);

        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}