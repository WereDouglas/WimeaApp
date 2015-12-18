package vugga.wimeaapp.model;

/**
 * Created by DOUGLAS on 15/12/2015.
 */
public class Metar {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPresent_weather() {
        return present_weather;
    }

    public void setPresent_weather(String present_weather) {
        this.present_weather = present_weather;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getAir_temperature() {
        return air_temperature;
    }

    public void setAir_temperature(String air_temperature) {
        this.air_temperature = air_temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDew_temperature() {
        return dew_temperature;
    }

    public void setDew_temperature(String dew_temperature) {
        this.dew_temperature = dew_temperature;
    }

    public String getWet_bulb() {
        return wet_bulb;
    }

    public void setWet_bulb(String wet_bulb) {
        this.wet_bulb = wet_bulb;
    }

    public String getStation_pressure_hpa() {
        return station_pressure_hpa;
    }

    public void setStation_pressure_hpa(String station_pressure_hpa) {
        this.station_pressure_hpa = station_pressure_hpa;
    }

    public String getSea_pressure_hpa() {
        return sea_pressure_hpa;
    }

    public void setSea_pressure_hpa(String sea_pressure_hpa) {
        this.sea_pressure_hpa = sea_pressure_hpa;
    }

    public String getRecent_weather() {
        return recent_weather;
    }

    public void setRecent_weather(String recent_weather) {
        this.recent_weather = recent_weather;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    int id;
    String station;
    String type;
    String datetime;
    String timezone;
    String wind_direction;
    String wind_speed;
    String unit;
    String visibility;
    String present_weather;
    String cloud;
    String air_temperature;
    String humidity;
    String dew_temperature;
    String wet_bulb;
    String station_pressure_hpa;
    String sea_pressure_hpa;
    String recent_weather;
    String user;
    String day;

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    String sync;
}
