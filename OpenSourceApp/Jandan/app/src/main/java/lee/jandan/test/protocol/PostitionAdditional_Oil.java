package lee.jandan.test.protocol;

import lee.jandan.test.T808Message.BitConverter;
import lee.jandan.test.protocol.IPositionAdditionalItem;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_Oil implements IPositionAdditionalItem {

    /**
     * 油量，WORD，1/10L，对应车上油量表读数
     *
     * @return
     */
    short Oil;

    public byte getAdditionalId()
    {
        return 0x02;
    }

    public byte getAdditionalLength()

    {
        return 0x02;
    }


    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) (Oil >> 8);
            bytes[1] = (byte) Oil;
            return bytes;
        } else {
            return BitConverter.GetBytes(Oil);
        }
    }

    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            Oil = (short) ((bytes[0] << 8) + bytes[1]);
        } else {
            Oil = (short)BitConverter.ToUInt16(bytes, 0);
        }
    }

    public short getOil() {
        return Oil;
    }

    public void setOil(short oil) {
        Oil = oil;
    }
}
