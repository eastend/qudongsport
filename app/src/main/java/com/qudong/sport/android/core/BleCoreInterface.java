package com.qudong.sport.android.core;

public interface BleCoreInterface {

     String  getServiceUUID();

     String getNotifyUUID();

     String getWriteUUID();

     byte[] createStartCommand();


}
