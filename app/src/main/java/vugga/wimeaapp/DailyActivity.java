package vugga.wimeaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vugga.wimeaapp.helper.DatabaseHelper;
import vugga.wimeaapp.model.Daily;
import vugga.wimeaapp.model.Metar;


public class DailyActivity extends ActionBarActivity {
    DatabaseHelper db;
    Button submit,displayDate;
    TextView info;
    DatePicker datePicker;
    CheckBox ts,rf,hs,hz,eq,fg;
    EditText max,min,actual,duration,sunshine,evaptype1,evap1,evaptype2,evap2,gpm,radiationtype,radiation,anemometer,wind,dateview;
    String station,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        displayDate=(Button)findViewById(R.id.button1);


        db = new DatabaseHelper(getApplicationContext());
        SharedPreferences myPrefs = getSharedPreferences(util.PREFS_NAME, 0);
        name =   myPrefs.getString("name", "").toString();
        station =   myPrefs.getString("station", "").toString();
        info = (TextView) findViewById(R.id.info);

        if(!(name.toString().equals("") && station.toString().equals("")) ) {

            info.setText("" + name + "    " + station);
        }
        //  max,min,actual,duration,sunshine,evaptype1,evap1,evaptype2,evap2,gpm,radiationtype,radiation,ts,rf,hs,hz,eq,fg;
        datePicker =(DatePicker)findViewById(R.id.datePicker);
        dateview =(EditText)findViewById(R.id.dateview);
        max =(EditText)findViewById(R.id.max);
        min  =(EditText)findViewById(R.id.min);
        actual =(EditText)findViewById(R.id.actual);
        duration=(EditText)findViewById(R.id.duration);
        sunshine =(EditText)findViewById(R.id.sunshine);
        evaptype1=(EditText)findViewById(R.id.evaptype1);
        evap1=(EditText)findViewById(R.id.evap1);
        evaptype2=(EditText)findViewById(R.id.evaptype2);
        evap2=(EditText)findViewById(R.id.evap2);
        gpm=(EditText)findViewById(R.id.gpm);
        anemometer =(EditText)findViewById(R.id.anemometer);
        radiationtype=(EditText)findViewById(R.id.radiationtype);
        radiation=(EditText)findViewById(R.id.radiation);
        wind = (EditText)findViewById(R.id.wind);
        ts=(CheckBox)findViewById(R.id.ts);
        rf=(CheckBox)findViewById(R.id.rf);
        hs=(CheckBox)findViewById(R.id.hs);
        hz=(CheckBox)findViewById(R.id.hz);
        eq=(CheckBox)findViewById(R.id.eq);
        fg=(CheckBox)findViewById(R.id.fg);

        dateview.setText(getCurrentDate());

        displayDate.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                dateview.setText(getCurrentDate());
                Toast.makeText(getApplicationContext(),  dateview.getText().toString(), Toast.LENGTH_LONG).show();
            }

        });

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),  dateview.getText().toString(), Toast.LENGTH_LONG).show();

                Daily mt = new Daily();
                mt.setStation(station);
                mt.setDate(""+dateview.getText().toString());
                mt.setMax(" " + max.getText().toString());
                mt.setMin(" " + min.getText().toString());
                mt.setHeight(" "+anemometer.getText().toString());
                mt.setActual(" " + actual.getText().toString());
                mt.setDuration(" " + duration.getText().toString());
                mt.setAnemometer(" "+anemometer.getText().toString());
                mt.setSunshine(" " + sunshine.getText().toString());
                mt.setEvaptype1(" " + evaptype1.getText().toString());
                mt.setEvap1(" " + evap1.getText().toString());
                mt.setWind(" "+wind.getText().toString());
                mt.setEvaptype2(" " + evaptype2.getText().toString());
                mt.setEvap2(" " + evap2.getText().toString());
                mt.setGpm(" " + gpm.getText().toString());
                mt.setRadiation(" " + radiation.getText().toString());
                mt.setRadiationtype(" " + radiationtype.getText().toString() + " ");

                if (ts.isChecked())
                   mt.setThunder("true");else mt.setThunder("false");
                if (rf.isChecked())
                    mt.setRain("true");else mt.setRain("false");
                if (hs.isChecked())
                    mt.setStorm("true");else mt.setStorm("false");
                if (hz.isChecked())
                    mt.setHaze("true");else mt.setHaze("false");
                if (eq.isChecked())
                    mt.setQuake("true");else mt.setQuake("false");
                if (fg.isChecked())
                    mt.setFog("true");else mt.setFog("false");

                mt.setUser(name);
                mt.setSync("F");
                long todo1_id = db.createDaily(mt);

               Intent startLocation = new Intent(DailyActivity.this, DailysActivity.class);
               startActivity(startLocation);


            }
        });

    }
    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append(datePicker.getYear()+"-");
        builder.append((datePicker.getMonth() + 1)+"-");
        builder.append(datePicker.getDayOfMonth());
        return builder.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.daily, menu);
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
}
