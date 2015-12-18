package vugga.wimeaapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vugga.wimeaapp.model.Daily;
import vugga.wimeaapp.model.Metar;

/**
 * Created by DOUGLAS on 15/12/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "informationManager";

    // Table Names
    private static final String TABLE_DAILY = "daily";
    private static final String TABLE_METAR = "metar";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_DAILY = "CREATE TABLE daily( id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT,station TEXT,max TEXT,min TEXT, actual TEXT,anemometer TEXT,wind TEXT,maxi TEXT,user TEXT,approved TEXT,rain TEXT,thunder TEXT,fog TEXT,haze TEXT, storm TEXT,quake TEXT,height TEXT, duration TEXT,sunshine TEXT,radiationtype TEXT,radiation TEXT,evaptype1 TEXT,evap1 TEXT,evap2 TEXT,evaptype2 TEXT,sync TEXT )";
    private static final String CREATE_TABLE_METAR = "CREATE TABLE metar( id INTEGER PRIMARY KEY AUTOINCREMENT,station TEXT,type TEXT,datetime TEXT, timezone TEXT,wind_direction TEXT,wind_speed TEXT,unit TEXT,visibility TEXT,present_weather TEXT,cloud TEXT,air_temperature TEXT,humidity TEXT,dew_temperature TEXT, wet_bulb TEXT,station_pressure_hpa TEXT,sea_pressure_hpa TEXT,recent_weather TEXT,user TEXT,day TEXT,sync TEXT,date TEXT )";

    // Tag table create statement


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public String updateMetar(int id,String state) {
        SQLiteDatabase db = this.getWritableDatabase();
        // updating row
        ContentValues cv = new ContentValues();
        cv.put("sync",state); //These Fields should be your String values of actual column names


       // String selectQuery ="UPDATE metar SET sync = "+state+" WHERE id = "+id;
         //       db.execSQL(selectQuery);
        db.update("metar", cv, "id=" + id, null);

        return "updated";
    }
    public String updateDaily(int id,String state) {
        SQLiteDatabase db = this.getWritableDatabase();
        // updating row
        ContentValues cv = new ContentValues();
        cv.put("sync",state); //These Fields should be your String values of actual column names


        // String selectQuery ="UPDATE metar SET sync = "+state+" WHERE id = "+id;
        //       db.execSQL(selectQuery);
        db.update("daily", cv, "id="+id, null);

        return "updated";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_METAR);
        db.execSQL(CREATE_TABLE_DAILY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_METAR);


        // create new tables
        onCreate(db);
    }
    public long createMetar(Metar metar) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", metar.getDay());
        values.put("date", metar.getDay());
        values.put("station", metar.getStation());
        values.put("type", metar.getType());
        values.put("datetime", metar.getDatetime());
        values.put("timezone", metar.getTimezone());
        values.put("wind_direction", metar.getWind_direction());
        values.put("wind_speed", metar.getWind_speed());
        values.put("unit", metar.getUnit());
        values.put("visibility", metar.getVisibility());
        values.put("present_weather", metar.getPresent_weather());
        values.put("cloud", metar.getCloud());
        values.put("air_temperature", metar.getAir_temperature());
        values.put("humidity", metar.getHumidity());
        values.put("dew_temperature", metar.getDew_temperature());
        values.put("wet_bulb", metar.getWet_bulb());
        values.put("station_pressure_hpa", metar.getSea_pressure_hpa());
        values.put("sea_pressure_hpa", metar.getSea_pressure_hpa());
        values.put("recent_weather", metar.getRecent_weather());
        values.put("user", metar.getUser());
        values.put("day", metar.getDay());
        values.put("sync", metar.getSync());
        // insert row
        long metar_id = db.insert(TABLE_METAR, null, values);
        return metar_id;
    }

    public long createDaily(Daily daily) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", daily.getDate());
        values.put("station", daily.getStation());
        values.put("max", daily.getMax());
        values.put("min", daily.getMin());
        values.put("actual", daily.getActual());
        values.put("anemometer", daily.getAnemometer());
        values.put("wind", daily.getWind());
        values.put("maxi", daily.getMaxi());
        values.put("user", daily.getUser());
        values.put("approved", daily.getApproved());
        values.put("rain", daily.getRain());
        values.put("thunder", daily.getThunder());
        values.put("fog", daily.getFog());
        values.put("haze", daily.getHaze());
        values.put("storm", daily.getStorm());
        values.put("quake", daily.getQuake());
        values.put("height", daily.getHeight());
        values.put("duration", daily.getDuration());
        values.put("sunshine", daily.getSunshine());
        values.put("radiationtype", daily.getRadiationtype());
        values.put("radiation", daily.getRadiation());
        values.put("evaptype1", daily.getEvaptype1());
        values.put("evap1", daily.getEvap1());
        values.put("evaptype2", daily.getEvaptype2());
        values.put("evap2", daily.getEvap2());
        values.put("sync", daily.getSync());
        // insert row
        long daily_id = db.insert(TABLE_DAILY, null, values);
        return daily_id;
    }
    public Metar getMetar(long metar_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_METAR + " WHERE id = " + metar_id;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Metar mt = new Metar();
        mt.setId(c.getInt(c.getColumnIndex("id")));
        mt.setStation(c.getString(c.getColumnIndex("station")));
        mt.setType(c.getString(c.getColumnIndex("type")));
        mt.setDatetime(c.getString(c.getColumnIndex("datetime")));
        mt.setTimezone(c.getString(c.getColumnIndex("timezone")));
        mt.setWind_direction(c.getString(c.getColumnIndex("wind_direction")));
        mt.setWind_speed(c.getString(c.getColumnIndex("wind_speed")));
        mt.setUnit(c.getString(c.getColumnIndex("unit")));
        mt.setVisibility(c.getString(c.getColumnIndex("visibility")));
        mt.setPresent_weather(c.getString(c.getColumnIndex("present_weather")));
        mt.setCloud(c.getString(c.getColumnIndex("cloud")));
        mt.setAir_temperature(c.getString(c.getColumnIndex("air_temperature")));
        mt.setHumidity(c.getString(c.getColumnIndex("humidity")));
        mt.setDew_temperature(c.getString(c.getColumnIndex("dew_temperature")));
        mt.setWet_bulb(c.getString(c.getColumnIndex("wet_bulb")));
        mt.setStation_pressure_hpa(c.getString(c.getColumnIndex("station_pressure_hpa")));
        mt.setSea_pressure_hpa(c.getString(c.getColumnIndex("sea_pressure_hpa")));
        mt.setRecent_weather(c.getString(c.getColumnIndex("recent_weather")));
        mt.setUser(c.getString(c.getColumnIndex("user")));
        mt.setDay(c.getString(c.getColumnIndex("day")));
        mt.setSync(c.getString(c.getColumnIndex("sync")));
        return mt;
    }
    public List<Metar> getAllMetar() {
        List<Metar> metars = new ArrayList<Metar>();
        String selectQuery = "SELECT  * FROM " + TABLE_METAR;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Metar mt = new Metar();
                mt.setId(c.getInt(c.getColumnIndex("id")));
                mt.setStation(c.getString(c.getColumnIndex("station")));
                mt.setType(c.getString(c.getColumnIndex("type")));
                mt.setDatetime(c.getString(c.getColumnIndex("datetime")));
                mt.setTimezone(c.getString(c.getColumnIndex("timezone")));
                mt.setWind_direction(c.getString(c.getColumnIndex("wind_direction")));
                mt.setWind_speed(c.getString(c.getColumnIndex("wind_speed")));
                mt.setUnit(c.getString(c.getColumnIndex("unit")));
                mt.setVisibility(c.getString(c.getColumnIndex("visibility")));
                mt.setPresent_weather(c.getString(c.getColumnIndex("present_weather")));
                mt.setCloud(c.getString(c.getColumnIndex("cloud")));
                mt.setAir_temperature(c.getString(c.getColumnIndex("air_temperature")));
                mt.setHumidity(c.getString(c.getColumnIndex("humidity")));
                mt.setDew_temperature(c.getString(c.getColumnIndex("dew_temperature")));
                mt.setWet_bulb(c.getString(c.getColumnIndex("wet_bulb")));
                mt.setStation_pressure_hpa(c.getString(c.getColumnIndex("station_pressure_hpa")));
                mt.setSea_pressure_hpa(c.getString(c.getColumnIndex("sea_pressure_hpa")));
                mt.setRecent_weather(c.getString(c.getColumnIndex("recent_weather")));
                mt.setUser(c.getString(c.getColumnIndex("user")));
                mt.setDay(c.getString(c.getColumnIndex("day")));
                mt.setSync(c.getString(c.getColumnIndex("sync")));
                // adding to todo list
                metars.add(mt);
            } while (c.moveToNext());
        }

        return metars;
    }
    public void deleteMetar(long metar_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_METAR, "id" + " = ?",
                new String[] { String.valueOf(metar_id) });
    }
    public void removeAll()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete("daily", null, null);
        db.delete("metar", null, null);

    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public List<Daily> getAllDaily() {
        List<Daily> dailys = new ArrayList<Daily>();
        String selectQuery = "SELECT  * FROM " + TABLE_DAILY;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Daily mt = new Daily();
                mt.setId(c.getInt(c.getColumnIndex("id")));
                mt.setDate(c.getString(c.getColumnIndex("date")));
                mt.setStation(c.getString(c.getColumnIndex("station")));
                mt.setMax(c.getString(c.getColumnIndex("max")));
                mt.setMin(c.getString(c.getColumnIndex("min")));
                mt.setActual(c.getString(c.getColumnIndex("actual")));
                mt.setAnemometer(c.getString(c.getColumnIndex("anemometer")));
                mt.setWind(c.getString(c.getColumnIndex("wind")));
                mt.setMaxi(c.getString(c.getColumnIndex("maxi")));
                mt.setApproved(c.getString(c.getColumnIndex("approved")));
                mt.setRain(c.getString(c.getColumnIndex("rain")));
                mt.setThunder(c.getString(c.getColumnIndex("thunder")));
                mt.setFog(c.getString(c.getColumnIndex("fog")));
                mt.setHaze(c.getString(c.getColumnIndex("haze")));
                mt.setStorm(c.getString(c.getColumnIndex("storm")));
                mt.setQuake(c.getString(c.getColumnIndex("quake")));
                mt.setHeight(c.getString(c.getColumnIndex("height")));
                mt.setDuration(c.getString(c.getColumnIndex("duration")));
                mt.setUser(c.getString(c.getColumnIndex("user")));
                mt.setSunshine(c.getString(c.getColumnIndex("sunshine")));
                mt.setRadiationtype(c.getString(c.getColumnIndex("radiationtype")));
                mt.setRadiation(c.getString(c.getColumnIndex("radiation")));
                mt.setEvap1(c.getString(c.getColumnIndex("evap1")));
                mt.setEvaptype1(c.getString(c.getColumnIndex("evaptype1")));
                mt.setEvap2(c.getString(c.getColumnIndex("evap2")));
                mt.setEvaptype2(c.getString(c.getColumnIndex("evaptype2")));
                mt.setSync(c.getString(c.getColumnIndex("sync")));
                // adding to todo list
                dailys.add(mt);
            } while (c.moveToNext());
        }

        return dailys;
    }

}