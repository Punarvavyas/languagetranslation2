package com.dal.languagetranslation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText source_txt;
    TextView target_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        source_txt = findViewById(R.id.source_txt);
        target_txt = findViewById(R.id.target_txt);

        //get translation model and set languages
        final TranslateViewModel viewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);
        viewModel.sourceLanguage.setValue(new Language("en"));
        viewModel.targetLanguage.setValue(new Language("fr"));

        //Translate input text as it is typed
        source_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setProgressText(target_txt);
                viewModel.sourceText.postValue(s.toString());
            }
        });

        viewModel.translatedText.observe(this, new Observer<ResultOrError>() {
            @Override
            public void onChanged(ResultOrError resultOrError) {
                if(resultOrError.error != null) {
                    source_txt.setError(resultOrError.error.getLocalizedMessage());
                }
                else {
                    target_txt.setText(resultOrError.result);
                }
            }
        });
    }

    private void setProgressText(TextView tv) {
        tv.setText(this.getString(R.string.translate_progress));


    }
}
