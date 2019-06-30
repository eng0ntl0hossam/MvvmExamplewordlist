package com.example.mvvmexamplewordlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvmexamplewordlist.database.Word;
import com.example.mvvmexamplewordlist.viewModel.WordViewModel;

public class NewWord2Activity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.mvvmexamplewordlist.REPLY";
    public static final String EXTRA_REPLY_ID = "com.example.mvvmexamplewordlist.REPLY_ID";
    private EditText mEditWordView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word2);
        mEditWordView = findViewById(R.id.edit_word);

        int id=-1;
        final Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String word = extras.getString(MainActivity.EXTRA_DATA_UPDATE_WORD, "");
            if (!word.isEmpty()) {
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);

                    if (extras != null && extras.containsKey(MainActivity.EXTRA_DATA_ID)) {
                        int id = extras.getInt(MainActivity.EXTRA_DATA_ID, -1);
                        if (id != -1) {
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                        }
                    }
                    // Set the result status to indicate success.
                    setResult(RESULT_OK, replyIntent);

                }
                finish();
            }
        });
    }


}
