package com.kevinlondon.geoquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	
	private TextView mQuestionTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};
	
	private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getNextQuestion();
			}
		});
        
        updateQuestion();
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		checkAnswer(true);
        	}
        });
        
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		checkAnswer(false);
        	}
        });
        
        mPrevButton = (ImageButton) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getPreviousQuestion();
			}
		});
        
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getNextQuestion();
			}
		});
    }

    private void getNextQuestion() {
    	mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
    	updateQuestion();
    }
    
    private void getPreviousQuestion() {
    	mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
    	if (mCurrentIndex < 0) {
    		mCurrentIndex = mQuestionBank.length - 1;
    	}
    	
    	updateQuestion();
    }
    
    private void updateQuestion() {
    	int question = mQuestionBank[mCurrentIndex].getQuestion();
    	mQuestionTextView.setText(question);
    }
    
    private void checkAnswer(boolean userPressedTrue) {
    	boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
    	// The resource ID for the question
    	int messageResId = 0;
    	if (userPressedTrue == answerIsTrue) {
    		messageResId = R.string.correct_toast;
    	} else {
    		messageResId = R.string.incorrect_toast;
    	}
    	
    	Toast.makeText(this,  messageResId,  Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
            return rootView;
        }
    }

}
