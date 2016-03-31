package lee.jandan.test.protocol;

import java.util.Objects;

/**
 * Created by dahan on 2016/3/16.
 */
public class ParameterItem {
    long ParameterId;
    byte ParameterLength;
    Objects ParameterValue;

    public long getParameterId() {
        return ParameterId;
    }

    public void setParameterId(long parameterId) {
        ParameterId = parameterId;
    }

    public byte getParameterLength() {
        return ParameterLength;
    }

    public void setParameterLength(byte parameterLength) {
        ParameterLength = parameterLength;
    }

    public Objects getParameterValue() {
        return ParameterValue;
    }

    public void setParameterValue(Objects parameterValue) {
        ParameterValue = parameterValue;
    }
}
