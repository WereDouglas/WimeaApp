package vugga.wimeaapp.model;

/**
 * Created by DOUGLAS on 15/12/2015.
 */
public class Daily {
    int id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getAnemometer() {
        return anemometer;
    }

    public void setAnemometer(String anemometer) {
        this.anemometer = anemometer;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getMaxi() {
        return maxi;
    }

    public void setMaxi(String maxi) {
        this.maxi = maxi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getThunder() {
        return thunder;
    }

    public void setThunder(String thunder) {
        this.thunder = thunder;
    }

    public String getFog() {
        return fog;
    }

    public void setFog(String fog) {
        this.fog = fog;
    }

    public String getHaze() {
        return haze;
    }

    public void setHaze(String haze) {
        this.haze = haze;
    }

    public String getStorm() {
        return storm;
    }

    public void setStorm(String storm) {
        this.storm = storm;
    }

    public String getQuake() {
        return quake;
    }

    public void setQuake(String quake) {
        this.quake = quake;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSunshine() {
        return sunshine;
    }

    public void setSunshine(String sunshine) {
        this.sunshine = sunshine;
    }

    public String getRadiationtype() {
        return radiationtype;
    }

    public void setRadiationtype(String radiationtype) {
        this.radiationtype = radiationtype;
    }

    public String getRadiation() {
        return radiation;
    }

    public void setRadiation(String radiation) {
        this.radiation = radiation;
    }

    public String getEvaptype1() {
        return evaptype1;
    }

    public void setEvaptype1(String evaptype1) {
        this.evaptype1 = evaptype1;
    }

    public String getEvap1() {
        return evap1;
    }

    public void setEvap1(String evap1) {
        this.evap1 = evap1;
    }

    public String getEvap2() {
        return evap2;
    }

    public void setEvap2(String evap2) {
        this.evap2 = evap2;
    }

    public String getEvaptype2() {
        return evaptype2;
    }

    public void setEvaptype2(String evaptype2) {
        this.evaptype2 = evaptype2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    String date;
    String station;
    String max;
    String min;
    String actual;
    String anemometer;
    String wind;
    String maxi;
    String user;
    String approved;
    String rain;
    String thunder;
    String fog;

    public String getGpm() {
        return gpm;
    }

    public void setGpm(String gpm) {
        this.gpm = gpm;
    }

    String gpm;
    String haze;
    String storm;
    String quake;
    String height;
    String duration;
    String sunshine;
    String radiationtype;
    String radiation;
    String evaptype1;
    String evap1;
    String evap2;
    String evaptype2;
    String sync;


    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }





    int status;
    String created_at;

    // constructors
    public Daily() {
    }

    public Daily(String note, int status) {

        this.status = status;
    }

    public Daily(int id, String note, int status) {
        this.id = id;

        this.status = status;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    // getters
    public int getId() {
        return this.id;
    }


}
