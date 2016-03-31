package lee.jandan.test.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lee.afk.afkutils.log.LeeLog;
import lee.jandan.test.T808Message.BitConverter;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_InOutAreaAlarmAdditional implements IPositionAdditionalItem {
    byte PositionType;
    int AreaId;
    byte Direction;

    @Override
    public byte getAdditionalId() {
        return 0x12;
    }

    @Override
    public byte getAdditionalLength() {
        return 0x06;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[6];
            bytes[0] = PositionType;
            bytes[1] = (byte) (AreaId >> 24);
            bytes[2] = (byte) (AreaId >> 16);
            bytes[3] = (byte) (AreaId >> 8);
            bytes[4] = (byte) AreaId;
            bytes[5] = Direction;
            return bytes;
        } else {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                byteArrayOutputStream.write(PositionType);
                dataOutputStream.writeLong(AreaId);
                byteArrayOutputStream.write(Direction);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                LeeLog.e(e);
                return null;
            }
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            PositionType = bytes[0];
            AreaId = (bytes[1] << 24) + (bytes[2] << 16) + (bytes[3] << 8) + bytes[4];
            Direction = bytes[5];
        } else {
            try{
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                PositionType = dataInputStream.readByte();
                AreaId = dataInputStream.readInt();
                Direction = dataInputStream.readByte();
            }
            catch (IOException e){
                LeeLog.e(e);
            }
        }
    }
}