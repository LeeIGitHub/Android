package lee.jandan.test.protocol;

import lee.jandan.test.T808Message.BitConverter;
import lee.jandan.test.protocol.IPositionAdditionalItem;
import lee.jandan.test.type.Uint32;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_Mileage implements IPositionAdditionalItem {
    /**
     * 里程，DWORD，1/10km，对应车上里程表读数
     */
    int Mileage;

    public byte getAdditionalId() {
        return 0x01;
    }

    public byte getAdditionalLength() {
        return 0x04;
    }

    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[4];
            bytes[0] = (byte) (Mileage >> 24);
            bytes[1] = (byte) (Mileage >> 16);
            bytes[2] = (byte) (Mileage >> 8);
            bytes[3] = (byte) Mileage;
            return bytes;
        } else {
            return BitConverter.GetBytes(Mileage);
        }
    }

    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            Mileage = (bytes[0] << 24) + (bytes[1] << 16) + (bytes[2] << 8) + bytes[3];
        } else {
            Mileage = BitConverter.ToUInt32(bytes, 0);
        }
    }

    public int getMileage() {
        return Mileage;
    }

    public void setMileage(int mileage) {
        Mileage = mileage;
    }
}
