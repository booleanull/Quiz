package com.bnull.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton backButton;
    private TextView questionText;
    private TextView positionText;

    private String[] questionStrings;
    private ArrayList<Question> questionItems;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.buttonTrue);
        falseButton = findViewById(R.id.buttonFalse);
        nextButton = findViewById(R.id.buttonNext);
        backButton = findViewById(R.id.buttonBack);
        questionText = findViewById(R.id.textViewQuestion);
        positionText = findViewById(R.id.textViewPosition);

        questionStrings = getResources().getStringArray(R.array.questions);
        questionItems = new ArrayList<>();
        questionItems.add(new Question(questionStrings[0], true));
        questionItems.add(new Question(questionStrings[1], false));
        questionItems.add(new Question(questionStrings[2], false));
        questionItems.add(new Question(questionStrings[3], true));
        questionItems.add(new Question(questionStrings[4], true));
        questionItems.add(new Question(questionStrings[5], false));
        questionItems.add(new Question(questionStrings[6], false));
        questionItems.add(new Question(questionStrings[7], true));
        questionItems.add(new Question(questionStrings[8], true));
        questionItems.add(new Question(questionStrings[9], false));
        questionItems.add(new Question(questionStrings[10], false));
        questionText.setText(questionStrings[position]);
        positionText.setText("1 /" + questionItems.size());

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTrue:
                checkCorrectAnswer(true);
                break;
            case R.id.buttonFalse:
                checkCorrectAnswer(false);
                break;
            case R.id.buttonNext:
                moveQuestion(1);
                break;
            case R.id.buttonBack:
                moveQuestion(-1);
                break;
        }
    }

    private void checkCorrectAnswer(boolean answer) {
        if(answer == questionItems.get(position).isAnswer())
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show();
    }

    private void moveQuestion(int p) {
        if(position + p < questionItems.size() && position + p >= 0) {
            position += p;
            questionText.setText(questionItems.get(position).getText());

            int pos = position + 1;
            positionText.setText(pos + "/" + questionItems.size());
        }
    }
}
