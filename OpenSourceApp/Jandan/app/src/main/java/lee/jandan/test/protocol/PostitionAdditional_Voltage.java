package lee.jandan.test.protocol;

import lee.jandan.test.T808Message.BitConverter;
import lee.jandan.test.protocol.IPositionAdditionalItem;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_Voltage implements IPositionAdditionalItem {
    /**
     * 电压,单位0.01V
     */
    short Voltage;

    @Override
    public byte getAdditionalId() {
        return (byte) 0x80;
    }

    @Override
    public byte getAdditionalLength() {
        return 0x02;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) (Voltage >> 8);
            bytes[1] = (byte) Voltage;
            return bytes;
        } else {
            return BitConverter.GetBytes(Voltage);
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            Voltage = (short) ((bytes[0] << 8) + bytes[1]);
        } else {
            Voltage = (short) BitConverter.ToUInt16(bytes, 0);
        }
    }

    public short getVoltage() {
        return Voltage;
    }

    public void setVoltage(short voltage) {
        Voltage = voltage;
    }
}
