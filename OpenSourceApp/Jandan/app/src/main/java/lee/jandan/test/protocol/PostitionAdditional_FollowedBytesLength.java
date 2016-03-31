package lee.jandan.test.protocol;

/**
 * Created by dahan on 2016/3/21.
 */
public class PostitionAdditional_FollowedBytesLength implements IPositionAdditionalItem {
    byte AdditionalLength;

    short FollowedBytesLength;

    @Override
    public byte getAdditionalId() {
        return (byte) 0xE0;
    }

    public void setAdditionalLength(byte additionalLength) {
        AdditionalLength = additionalLength;
    }

    @Override
    public byte getAdditionalLength() {
        return AdditionalLength;
    }

    @Override
    public byte[] WriteToBytes() {
        if (AdditionalLength == 1) {
            return new byte[]{(byte) (FollowedBytesLength >> 8)};
        } else if (AdditionalLength == 2) {
            return new byte[]{(byte) (FollowedBytesLength >> 8), (byte) FollowedBytesLength};
        } else {
            byte[] bytes = new byte[AdditionalLength];
            for (int i = 0; i < AdditionalLength; i++) {
                bytes[i] = 0x00;
            }
            return bytes;
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {

    }

    public short getFollowedBytesLength() {
        return FollowedBytesLength;
    }

    public void setFollowedBytesLength(short followedBytesLength) {
        FollowedBytesLength = followedBytesLength;
    }
}
