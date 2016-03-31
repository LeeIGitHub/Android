package lee.jandan.test.protocol;

import lee.jandan.test.T808Message.BitConverter;
import lee.jandan.test.protocol.IPositionAdditionalItem;

/**
 * Created by Lee on 2016/3/21.
 */
public class PostitionAdditional_RecorderSpeed implements IPositionAdditionalItem {
    /**
     * 行驶记录功能获取的速度，WORD，1/10km/h
     */
    short RecorderSpeed;

    @Override
    public byte getAdditionalId() {
        return 0x03;
    }

    @Override
    public byte getAdditionalLength() {
        return 0x02;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) (RecorderSpeed >> 8);
            bytes[1] = (byte) RecorderSpeed;
            return bytes;
        } else {
            return BitConverter.GetBytes(RecorderSpeed);
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            RecorderSpeed = (short) ((bytes[0] << 8) + bytes[1]);
        } else {
            RecorderSpeed = (short)BitConverter.ToUInt16(bytes, 0);
        }
    }

    public short getRecorderSpeed() {
        return RecorderSpeed;
    }

    public void setRecorderSpeed(short recorderSpeed) {
        RecorderSpeed = recorderSpeed;
    }
}
