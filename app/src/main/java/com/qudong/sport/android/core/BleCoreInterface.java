package com.qudong.sport.android.core;

public interface BleCoreInterface {

     String  getServiceUUID();

     String getNotifyUUID();

     String getWirteUUID();

     byte[] createStartCommand();


}
