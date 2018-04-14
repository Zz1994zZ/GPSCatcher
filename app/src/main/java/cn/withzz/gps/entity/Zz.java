package cn.withzz.gps.entity;

/**
 * Created by shmily_zz on 2017/5/2.
 */

public class Zz {

    /**
     * Id : null
     * Name : mydevice
     * UserId : null
     * col1 : 111
     * col2 : 222
     * col3 : 333
     * col4 : 444
     * Xcool : 114.154709
     * Ycool : 30.51853
     * State : 0
     * AddTime : 2017-04-26T21:35:52
     * PlaceNoXY :
     * apikey : null
     */

    private Object Id;
    private String Name;
    private Object UserId;
    private int col1;
    private int col2;
    private int col3;
    private int col4;
    private double Xcool;
    private double Ycool;
    private int State;
    private String AddTime;
    private String PlaceNoXY;
    private Object apikey;

    public Object getId() {
        return Id;
    }

    public void setId(Object Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Object getUserId() {
        return UserId;
    }

    public void setUserId(Object UserId) {
        this.UserId = UserId;
    }

    public int getCol1() {
        return col1;
    }

    public void setCol1(int col1) {
        this.col1 = col1;
    }

    public int getCol2() {
        return col2;
    }

    public void setCol2(int col2) {
        this.col2 = col2;
    }

    public int getCol3() {
        return col3;
    }

    public void setCol3(int col3) {
        this.col3 = col3;
    }

    public int getCol4() {
        return col4;
    }

    public void setCol4(int col4) {
        this.col4 = col4;
    }

    public double getXcool() {
        return Xcool;
    }

    public void setXcool(double Xcool) {
        this.Xcool = Xcool;
    }

    public double getYcool() {
        return Ycool;
    }

    public void setYcool(double Ycool) {
        this.Ycool = Ycool;
    }

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String AddTime) {
        this.AddTime = AddTime;
    }

    public String getPlaceNoXY() {
        return PlaceNoXY;
    }

    public void setPlaceNoXY(String PlaceNoXY) {
        this.PlaceNoXY = PlaceNoXY;
    }

    public Object getApikey() {
        return apikey;
    }

    public void setApikey(Object apikey) {
        this.apikey = apikey;
    }
}
