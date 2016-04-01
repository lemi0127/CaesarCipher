package com.algonquincollege.lemi0127.caesarcipher;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class PlainTextActivity extends AppCompatActivity {
    private DialogFragment aboutDialog;
    private EditText editPlainText;
    private int rotation;
    private CharacterPickerDialog rotationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInput = editPlainText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), EncryptedTextActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(THE_MESSAGE, textInput);
                intent.putExtra(THE_ROTATION, rotation);
                startActivity(intent);
            }
        });

        aboutDialog = new AboutDialogFragment();
        rotation = ROT13;
        rotationDialog = new CharacterPickerDialog(this, new View(this), null, ROTATIONS, false){
            @Override
            public void Onclick(View view){
                int index = ROTATIONS.indexOf( ((Button)v).getText().toString());
                if (index>=0){
                    rotation = index;
                }
                dismiss();
            }
        };

        Button clearButton = (Button) findViewById(R.id.clearButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
