package lee.jandan.test.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lee.afk.afkutils.log.LeeLog;
import lee.jandan.test.T808Message.BitConverter;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_OverSpeedAlarmAdditional implements IPositionAdditionalItem {
    byte PositionType;
    int AreaId;

    public byte getPositionType() {
        return PositionType;
    }

    public void setPositionType(byte positionType) {
        PositionType = positionType;
    }

    public int getAreaId() {
        return AreaId;
    }

    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    @Override
    public byte getAdditionalId() {
        return 0x11;
    }

    @Override
    public byte getAdditionalLength() {
        if (getPositionType() == 0)
            return 0x01;
        return 0x05;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            if (PositionType == 0) {
                return new byte[]{PositionType};
            } else {
                byte[] bytes = new byte[5];
                bytes[0] = PositionType;
                bytes[1] = (byte) (AreaId >> 24);
                bytes[2] = (byte) (AreaId >> 16);
                bytes[3] = (byte) (AreaId >> 8);
                bytes[4] = (byte) AreaId;
                return bytes;
            }
        } else {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeLong(AreaId);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                LeeLog.e(e);
                return null;
            }
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {

    }
}
