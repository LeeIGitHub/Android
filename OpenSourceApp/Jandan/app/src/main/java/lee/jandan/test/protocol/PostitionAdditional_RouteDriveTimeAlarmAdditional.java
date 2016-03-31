package lee.jandan.test.protocol;

import android.support.v7.widget.DialogTitle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lee.jandan.test.T808Message.BitConverter;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_RouteDriveTimeAlarmAdditional implements IPositionAdditionalItem {
    int RouteId;
    short DriveTime;
    byte Result;

    @Override
    public byte getAdditionalId() {
        return 0x13;
    }

    @Override
    public byte getAdditionalLength() {
        return 0x07;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[7];
            bytes[0] = (byte) (RouteId >> 24);
            bytes[1] = (byte) (RouteId >> 16);
            bytes[2] = (byte) (RouteId >> 8);
            bytes[3] = (byte) RouteId;
            bytes[4] = (byte) (DriveTime >> 8);
            bytes[5] = (byte) DriveTime;
            bytes[6] = Result;
            return bytes;
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(RouteId);
            byteArrayOutputStream.write(DriveTime);
            byteArrayOutputStream.write(Result);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            RouteId = (bytes[0] << 24) + (bytes[1] << 16) + (bytes[2] << 8) + bytes[3];
            DriveTime = (short) ((bytes[4] << 8) + bytes[5]);
            Result = bytes[6];
        } else {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                dataInputStream.readInt();
                dataInputStream.readShort();
                dataInputStream.readByte();
            } catch (IOException e) {

            }
        }
    }
}
