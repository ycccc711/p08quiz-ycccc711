package sg.edu.rp.soi.c346_p08quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btnSave;
    CheckBox cbLocal;
    Spinner spn;

    String name = "";
    int age = 0;
    int po = 0;
    String local = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        cbLocal = findViewById(R.id.cbLocal);
        spn = findViewById(R.id.spinner_class);
        btnSave = findViewById(R.id.btnSave);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                po = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                age = Integer.parseInt(etAge.getText().toString());


                if (cbLocal.isChecked()) {
                    local = "Local Student";
                } else {
                    local = "Non-local Student";
                }
                saveData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String strname = prefs.getString("getname", "");
        int intage = prefs.getInt("getage", 0);
        int intpo = prefs.getInt("getpo", 0);
        Boolean strlocal = prefs.getBoolean("getlo", true);

        etName.setText(strname);
        etAge.setText(intage+"");
        Log.d("tag", strname);
        Log.d("tag", intage + "");
        Log.d("tag", intpo + "");
        Log.d("tag", strlocal + "");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void saveData() {
        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor prefsEdit = prefs.edit();
        prefsEdit.putString("getname", name);
        prefsEdit.putInt("getage", age);
        prefsEdit.putInt("getpo", po);
        prefsEdit.putBoolean("getlo", Boolean.parseBoolean(local));
        prefsEdit.commit();
    }
}
