package lee.jandan.test.protocol;

import lee.jandan.test.T808Message.BitConverter;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_AlarmEventId implements IPositionAdditionalItem {
    short AlarmEventId;

    public short getAlarmEventId() {
        return AlarmEventId;
    }

    public void setAlarmEventId(short alarmEventId) {
        AlarmEventId = alarmEventId;
    }

    @Override
    public byte getAdditionalId() {
        return 0x04;
    }

    @Override
    public byte getAdditionalLength() {
        return 0x02;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) (AlarmEventId >> 8);
            bytes[1] = (byte) AlarmEventId;
            return bytes;
        } else {
            return BitConverter.GetBytes(AlarmEventId);
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian) {
            AlarmEventId = (short) ((bytes[0] << 8) + bytes[1]);
        } else {
            AlarmEventId = (short)BitConverter.ToUInt16(bytes, 0);
        }
    }
}
