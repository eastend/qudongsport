package com.qudong.sport.android.core;

/**
 * Created by Administrator on 2018/7/24 0024.
 */
@Deprecated
public class BLECore {

    public static final String BASE_UUID = "0000FFF0-0000-1000-8000-00805F9B34FB";
    public static final String READ_UUID = "0000FFF1-0000-1000-8000-00805F9B34FB";
    public static final String WRITE_UUID = "0000FFF2-0000-1000-8000-00805F9B34FB";

    private static byte COMMAND_HEAD = (byte) 0xf2;


    /**
     * 按键操作
     */
    private static class KeyCommand {
        //Key: 1=启动按键；
        static final byte KEY_START = 0x01;

        //Key: 2=停止按键；
        static final byte KEY_STOP = 0x02;

        //Key: 3=暂停
        static final byte KEY_PAUSE = 0x03;

        //Key: 4=设置数据有效
        static final byte KEY_DATA_VALID = 0x04;
    }


    /**
     * Cnt:1 清除Cnt值（表示app已接收到Cnt数据,电子表可以清除Cnt数据）
     */
    private static class ClearCntCommand {
        static final byte YES = 0x01;
        static final byte NO = 0x00;
    }

    /**
     * 转动
     */
    private static class TurnStatus {
        //正向转动
        static final byte TURN_FORWARD = 0x01;

        //反向转动
        static final byte TURN_REVERSE = 0x02;

        //静止
        static final byte TURN_STATIONARY = 0x00;

    }


    /**
     * 创建测试命令
     *
     * @return
     */
    public static final byte[] createTestCommand() {
        return new byte[]{(byte) 0xF2, (byte) 0xC0, 0x00, (byte) 0xB2};
    }

    /**
     * 创建控制命令
     * 0xF2	0xA0	0x07	Key	Clear Cnt	预留	预留	预留	预留	预留	XX
     *
     * @return
     */
    public static final byte[] createControlCommand(int boatKeyEvent, boolean isClearCnt) {


        byte[] command = new byte[11];

        command[0] = COMMAND_HEAD;
        command[1] = (byte) 0xA0;
        command[2] = 0x07;

        //第3位
        switch (boatKeyEvent) {
            case BoatKeyEvent.ACTION_START:
                command[3] = KeyCommand.KEY_START;
                break;
            case BoatKeyEvent.ACTION_STOP:
                command[3] = KeyCommand.KEY_STOP;
                break;
            case BoatKeyEvent.ACTION_PAUSE:
                command[3] = KeyCommand.KEY_PAUSE;
                break;
            case BoatKeyEvent.DATA_VAILD:
                command[3] = KeyCommand.KEY_DATA_VALID;
                break;
            default:
                break;
        }

        //第4位
        if (isClearCnt) {
            command[4] = ClearCntCommand.YES;
        } else {
            command[4] = ClearCntCommand.NO;
        }

        command[5] = 0x00;
        command[6] = 0x00;
        command[7] = 0x00;
        command[8] = 0x00;
        command[9] = 0x00;

        byte checkSum = 0x00;

        for (int i = 0; i < 9; i++) {
            checkSum = (byte) (checkSum + command[i]);
        }
        command[10] = checkSum;

        return command;
    }

    /**
     * byte数组转整形
     *
     * @param b
     * @return
     */
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
    }


    public static String byteArray2HexString(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            buffer.append(hex);
        }
        return buffer.toString();
    }

}
