package com.qudong.sport.android.core;

public class CoreV1 implements BleCoreInterface {

    @Override
    public String getServiceUUID() {
        return "FFF0";
    }

    @Override
    public String getNotifyUUID() {
        return "FFF1";
    }

    @Override
    public String getWriteUUID() {
        return "FFF2";
    }


    /**
     * 0xF2	0xC3	0x07	Key	Level	坡度	瓦特值H	瓦特值L	预留	预留	XX
     * @return
     */
    @Override
    public byte[] createStartCommand() {

        byte[] command=new byte[11];
        command[0]= (byte) 0xf2;
        command[1]= (byte) 0xC3;
        command[2]= (byte) 0x07;
        command[3]=0x01;
        command[4]=0x00;
        command[5]=0x00;
        command[6]=0x00;
        command[7]=0x00;
        command[8]=0x00;
        command[9]=0x00;

        byte sumCheck=0x00;
        for (int i=0; i<command.length-1;i++){
            sumCheck+=command[i];
        }
        command[10]=sumCheck;

        return command;
    }
}
