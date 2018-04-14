package cn.withzz.gps.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by shmily_zz on 2017/4/28.
 */
@Entity
public class CoordInfo implements Serializable{
    public static final long serialVersionUID = 1L;
    @Id
    private String addTime;//"2017-04-26T21:35:52";
    private double xCool; //114.154709f;
    private double yCool; //30.51853f;
    private String path;


    @Generated(hash = 368176445)
    public CoordInfo(String addTime, double xCool, double yCool, String path) {
        this.addTime = addTime;
        this.xCool = xCool;
        this.yCool = yCool;
        this.path = path;
    }


    @Generated(hash = 2080530215)
    public CoordInfo() {
    }


    @Override
    public String toString() {
        return "CoordInfo{" +
                "addTime='" + addTime + '\'' +
                ", xCool=" + xCool +
                ", yCool=" + yCool +
                ", path='" + path + '\'' +
                '}';
    }


    public String getAddTime() {
        return this.addTime;
    }


    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }


    public double getXCool() {
        return this.xCool;
    }


    public void setXCool(double xCool) {
        this.xCool = xCool;
    }


    public double getYCool() {
        return this.yCool;
    }


    public void setYCool(double yCool) {
        this.yCool = yCool;
    }


    public String getPath() {
        return this.path;
    }


    public void setPath(String path) {
        this.path = path;
    }

}
