package lee.jandan.test.protocol;

/**
 * Created by dahan on 2016/3/21.
 */
public class PositionAdditionalFactory {
    public static IPositionAdditionalItem CreatePositionalFactory(byte additionalId, byte length, byte[] bytes) {
        IPositionAdditionalItem additional = null;
        switch (additionalId) {
            case 0x01:
                additional = new PostitionAdditional_Mileage();
                additional.ReadFromBytes(bytes);
                break;
            case 0x02:
                additional = new PostitionAdditional_Oil();
                additional.ReadFromBytes(bytes);
                break;
            case 0x03:
                additional = new PostitionAdditional_RecorderSpeed();
                additional.ReadFromBytes(bytes);
                break;
            case 0x04:
                additional = new PostitionAdditional_AlarmEventId();
                additional.ReadFromBytes(bytes);
                break;
            case 0x11:
                additional = new PostitionAdditional_OverSpeedAlarmAdditional();
                additional.ReadFromBytes(bytes);
                break;
            case 0x12:
                additional = new PostitionAdditional_InOutAreaAlarmAdditional();
                additional.ReadFromBytes(bytes);
                break;
            case 0x13:
                additional = new PostitionAdditional_RouteDriveTimeAlarmAdditional();
                additional.ReadFromBytes(bytes);
                break;
            case (byte)0xE0:
                PostitionAdditional_FollowedBytesLength folloedBytesLength = new PostitionAdditional_FollowedBytesLength();
                folloedBytesLength.AdditionalLength = length;
                folloedBytesLength.ReadFromBytes(bytes);
                additional = folloedBytesLength;
                break;
            case (byte)0xE1:
                additional = new PostitionAdditional_Voltage();
                additional.ReadFromBytes(bytes);
                break;
            case (byte)0xE2:
                additional = new OBDExtensions();
                additional.ReadFromBytes(bytes);
                break;
        }
        return additional;
    }
}
