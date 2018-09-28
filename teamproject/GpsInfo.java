package com.example.ckr97.teamproject;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

/**한남대학교 20160698 원준식**/

public class GpsInfo extends Service implements LocationListener {
    private Context mContext;
    // 현재 GPS 사용유무
    boolean gpsenabled = false;
    // 네트워크 사용유무
    boolean networkenabled = false;
    // GPS 상태값
    boolean getlocation = false;


    Location location;
    double lat; // 위도
    double lon; // 경도
    double al;//고도
    float ac;//정확도
    String pr;//위치제공자

    // 최소 GPS 정보 업데이트 거리 10미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    // 최소 GPS 정보 업데이트 시간 밀리세컨이므로 1분
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    protected LocationManager locationManager;

    public GpsInfo(Context context) {
        this.mContext = context;
        getLocation();
    }

    @TargetApi(23)
    public Location getLocation() {
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(
                        mContext, android.Manifest.permission.ACCESS_FINE_LOCATION )
                        != PackageManager.PERMISSION_GRANTED &&

                ContextCompat.checkSelfPermission(
                        mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            return null;
        }


        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // GPS 정보 가져오기
            gpsenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // 현재 네트워크 상태 값 알아오기
            networkenabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!gpsenabled && !networkenabled) {
                // GPS 와 네트워크사용이 가능하지 않을때 소스 구현
            } else {
                this.getlocation = true;
                // 네트워크 정보로 부터 위치값 가져오기

                if (networkenabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            // 위도 경도 저장
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                            al = location.getAltitude();
                            ac = location.getAccuracy();
                            pr = location.getProvider();
                        }

                    }
                }


                if (gpsenabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                                al = location.getAltitude();
                                ac = location.getAccuracy();
                                pr = location.getProvider();
                            }

                        }

                    }

                }

            }



        } catch (Exception e) {e.printStackTrace(); }

        return location;

    }

    public double getLatitude(){
        if(location != null){
            lat = location.getLatitude();   //위도
        }
        return lat;
    }

    public double getLongitude(){
        if(location != null){
            lon = location.getLongitude();   //경도
        }
        return lon;
    }

    public double getAltitude(){
        if(location != null){
            al = location.getAltitude();   //고도
        }
        return al;
    }

    public float getAccuracy(){
        if(location != null){
            ac = location.getAccuracy();    //정확도
        }
        return ac;
    }

    public String getProvider() {
        if (location != null) {
            pr = location.getProvider();   //위치제공자
        }
        return pr;
    }

    /**

     * GPS 나 wife 정보가 켜져있는지 확인합니다.

     * */

    public boolean isGetLocation() {

        return this.getlocation;

    }


    @Override

    public IBinder onBind(Intent arg0) {

        return null;

    }



    public void onLocationChanged(Location location) {

        // TODO Auto-generated method stub



    }



    public void onStatusChanged(String provider, int status, Bundle extras) {

        // TODO Auto-generated method stub



    }



    public void onProviderEnabled(String provider) {

        // TODO Auto-generated method stub



    }



    public void onProviderDisabled(String provider) {

        // TODO Auto-generated method stub



    }

}




