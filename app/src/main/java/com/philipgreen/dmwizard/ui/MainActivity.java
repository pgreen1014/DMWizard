package com.philipgreen.dmwizard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.philipgreen.dmwizard.R;
import com.philipgreen.dmwizard.ui.character.creation.CharacterCreatorActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button mBtnCreateCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCreateCharacter = (Button) findViewById(R.id.character_create_button);

        mBtnCreateCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CharacterCreatorActivity.class);
                startActivity(i);
            }
        });
    }
}
