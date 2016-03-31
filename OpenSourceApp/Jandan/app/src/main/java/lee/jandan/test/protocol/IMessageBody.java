package lee.jandan.test.protocol;

/**
 * Created by dahan on 2016/3/16.
 */
public interface IMessageBody {
    byte[] WriteToBytes();
    void ReadFromBytes(byte[] messageBodyBytes);
}
