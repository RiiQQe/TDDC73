package com.example.micke.lab3;


import android.os.Bundle;

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

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    CustomListView clv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InteractiveSearcher is = (InteractiveSearcher) findViewById(R.id.interactiveSearcher);

        clv = (CustomListView) findViewById(R.id.customListView);

        is.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.length() != 0)
                    new SearchOperation(new SearchOperationInterface() {
                        @Override
                        public void putResults(JSONArray result) {
                            if(result != null) {
                                try {
                                    String[] resultingArray = result.join(",").replaceAll("\"", "").split(",");
                                    clv.populate(resultingArray);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).execute("http://flask-afteach.rhcloud.com/getnames/4/" + charSequence);
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
