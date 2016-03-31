package lee.jandan.test.protocol;

/**
 * Created by dahan on 2016/3/21.
 */
public interface IPositionAdditionalItem {
    byte getAdditionalId();

    byte getAdditionalLength();

    byte[] WriteToBytes();

    void ReadFromBytes(byte[] bytes);

}
