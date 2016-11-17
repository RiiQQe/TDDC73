package com.example.micke.lab3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String[] tempArr = {"kalle", "kaka", "kul", "a", "b", "c", "d", "e", "f", "g", "h", "i", "a", "b", "c", "d", "e", "f", "g", "h", "i"};
    String[] tempArr2 = {"NYA STRANGAR", "KALLE ANKA"};
    String prevArr[];
    
    CustomListView clv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InteractiveSearcher is = (InteractiveSearcher) findViewById(R.id.interactiveSearcher);

        clv = (CustomListView) findViewById(R.id.customListView);

        clv.populate(tempArr);
        prevArr = tempArr;

        is.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();

                if (prevArr[0].contains("kalle")) prevArr = tempArr2;
                else prevArr = tempArr;

                // Use this function to populate with new data
                clv.populate(prevArr);

                if(charSequence.length() != 0)
                    new SearchOperation().execute("http://flask-afteach.rhcloud.com/getnames/4/" + charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void showNDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.nrOfResults);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Integer k = Integer.parseInt(input.getText().toString());

                clv.updateNrOfResults(k);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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

            showNDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
