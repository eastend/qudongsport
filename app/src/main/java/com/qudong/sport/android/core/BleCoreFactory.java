package com.qudong.sport.android.core;

public class BleCoreFactory {

    public static BleCoreInterface createCore(String deviceName){
        if(deviceName.startsWith("EW")){
            return new CoreV1();
        }

        return null;
    }






}
