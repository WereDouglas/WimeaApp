package vugga.wimeaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vugga.wimeaapp.helper.DatabaseHelper;
import vugga.wimeaapp.model.Metar;


public class MetarActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper db;
    Button metars,submit;
    TextView info;
    Spinner type;
    EditText ccc,timezone,wind_direction,wind_speed,height,cloud,air_temperature,humidity,dew,wet,tt,td,stationhpa,stationhpain,seahpa,seahpain,re;
    String  datetimes,station,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metar);

        Spinner spinner = (Spinner) findViewById(R.id.type);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("METAR");
        categories.add("SPECI");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        metars = (Button) findViewById(R.id.metars);
        db = new DatabaseHelper(getApplicationContext());
        SharedPreferences myPrefs = getSharedPreferences(util.PREFS_NAME, 0);
        name =   myPrefs.getString("name", "").toString();
       station =   myPrefs.getString("station", "").toString();
        info = (TextView) findViewById(R.id.info);

        if(!(name.toString().equals("") && station.toString().equals("")) ) {

            info.setText("" + name + "    " + station);
        }
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datetimes = ""+day+""+hour+"00"+"Z";
        timezone  =(EditText)findViewById(R.id.timezone);
        wind_direction =(EditText)findViewById(R.id.wind_direction);
        wind_speed =(EditText)findViewById(R.id.wind_speed);
        height =(EditText)findViewById(R.id.height);
        cloud =(EditText)findViewById(R.id.cloud);
        air_temperature =(EditText)findViewById(R.id.air_temperature);
        humidity =(EditText)findViewById(R.id.humidity);
        dew =(EditText)findViewById(R.id.dew);
        wet =(EditText)findViewById(R.id.wet);
        tt =(EditText)findViewById(R.id.tt);
        td =(EditText)findViewById(R.id.td);
        stationhpa =(EditText)findViewById(R.id.stationhpa);
        stationhpain =(EditText)findViewById(R.id.stationhpain);
        seahpa =(EditText)findViewById(R.id.seahpa);
        seahpain =(EditText)findViewById(R.id.seahpain);
        re =(EditText)findViewById(R.id.re);
        timezone.setText(datetimes);

        humidity.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //var dew = (humidity * temperature) / 100;
                    // var wet = Math.round(((dew * 2) + temperature) / 3);
                    double dews = Math.round  (( Double.parseDouble(humidity.getText().toString()) * Double.parseDouble(air_temperature.getText().toString()) /100));
                    double wets = Math.round((dews * 2) + Double.parseDouble(air_temperature.getText().toString()) / 3);
                    dew.setText(dews+"");
                    wet.setText(wets+"");
                    tt.setText(wets+"");
                    td.setText(dews+"");
                }
            }
        });
        stationhpa.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    double in = Math.round (Double.parseDouble(stationhpa.getText().toString())* 0.02952);
                    stationhpain.setText(""+in);
                }
            }
        });
        seahpa.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    double in = Math.round (Double.parseDouble(seahpa.getText().toString())* 0.02952);
                    seahpain.setText(""+in);
                }
            }
        });



        metars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startLocation = new Intent(MetarActivity.this, MetarsActivity.class);
                startActivity(startLocation);

            }
        });
        submit = (Button) findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Metar mt = new Metar();
                mt.setStation(station);
                mt.setType(item);
                mt.setDatetime(datetimes);
                mt.setTimezone("GMT");
                mt.setWind_direction(wind_direction.getText().toString());
                mt.setWind_speed(wind_speed.getText().toString());
                mt.setUnit("KT");
                mt.setVisibility(height.getText().toString());
                mt.setPresent_weather(re.getText().toString());
                mt.setCloud(cloud.getText().toString());
                mt.setAir_temperature(air_temperature.getText().toString());
                mt.setHumidity(humidity.getText().toString());
                mt.setDew_temperature(dew.getText().toString());
                mt.setWet_bulb(wet.getText().toString());
                mt.setStation_pressure_hpa("0" + stationhpa.getText().toString() + "");
                mt.setSea_pressure_hpa("0" + seahpa.getText().toString() + "");
                mt.setRecent_weather(re.getText().toString()+"");
                mt.setUser(name);
                mt.setSync("F");

                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                String cDateTime=dateFormat.format(new Date());
                mt.setDay(cDateTime.toString());
                long todo1_id = db.createMetar(mt);

                Intent startLocation = new Intent(MetarActivity.this, MetarsActivity.class);
                startActivity(startLocation);

            }
        });


    }
    String item;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.metar, menu);
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
