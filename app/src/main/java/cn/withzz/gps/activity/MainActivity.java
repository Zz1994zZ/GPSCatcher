package cn.withzz.gps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.google.gson.Gson;

import cn.withzz.gps.R;
import cn.withzz.gps.entity.CoordInfo;
import cn.withzz.gps.entity.Zz;
import cn.withzz.gps.fragment.CoordInfoFragment;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements CoordInfoFragment.OnListFragmentInteractionListener{
    private OkHttpClient client = new OkHttpClient();
    private Gson gson=new Gson();
    private CoordInfoFragment coordInfoFragment;
    private Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordInfoFragment=(CoordInfoFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar=Snackbar.make(view, "正在获取位置信息...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                snackbar.show();
                getInfo().
                        subscribeOn(Schedulers.newThread()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Consumer<CoordInfo>() {
                            @Override
                            public void accept(@NonNull CoordInfo coordInfo) throws Exception {
                                coordInfoFragment.add(coordInfo);
                                snackbar.dismiss();
                            }
                        });
            }
        });
    }
    @Override
    public void onListFragmentInteraction(CoordInfo item) {
        Intent intent=new Intent(this,MapActivity.class);
        intent.putExtra("coordInfo",item);
        startActivity(intent);
    }

    private  Observable<CoordInfo> getInfo(){
        return Observable.create(new ObservableOnSubscribe<CoordInfo>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<CoordInfo> observableEmitter) throws Exception {
                Request request = new Request.Builder()
                        .url(getResources().getString(R.string.requset_uri))
                        .build();

                Response response = client.newCall(request).execute();
                String json=response.body().string();
                Log.i("zz",json);
                Zz[] zz=gson.fromJson(json,Zz[].class);
                final CoordInfo cf=new CoordInfo();
                cf.setAddTime(zz[0].getAddTime());

                LatLng point = new LatLng(zz[0].getYcool(), zz[0].getXcool());
                // 将GPS设备采集的原始GPS坐标转换成百度坐标
                CoordinateConverter converter  = new CoordinateConverter();
                converter.from(CoordinateConverter.CoordType.GPS);
// sourceLatLng待转换坐标
                converter.coord(point);
                point = converter.convert();
                cf.setXCool(point.longitude);
                cf.setYCool(point.latitude);
                //地理编码将坐标转换成地址
                GeoCoder geoCoder = GeoCoder.newInstance();
                OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {

                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                    }
                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        cf.setPath(reverseGeoCodeResult.getAddress());
                        Log.i("zz",cf.toString());
                        observableEmitter.onNext(cf);
                    }
                };
                // 设置地理编码检索监听者
                geoCoder.setOnGetGeoCodeResultListener(listener);
                //
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(point));

            }
        });
    }





}
