package cn.withzz.gps;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.greenrobot.greendao.database.Database;

import cn.withzz.gps.gen.DaoMaster;
import cn.withzz.gps.gen.DaoSession;


/**
 * Created by shmily_zz on 2017/4/28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        instance=this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "coordInfo-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public static Application getInstance(){
        return instance;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
