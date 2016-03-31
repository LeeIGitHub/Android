package lee.jandan.test.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import lee.jandan.test.T808Message.BitConverter;

/**
 * Created by dahan on 2016/3/21.
 */
public class OBDExtensions implements IPositionAdditionalItem {
    /**
     * 负荷计算值    单位：%
     */
    byte OBDOverloadCalculate;
    /**
     * 冷却液温度    单位：℃ (A-40）
     */
    byte OBDCoolantTemperature;
    /**
     * OBD车速      单位：km/h
     */
    byte OBDSpeed;
    /**
     * 发动机转速    单位：rpm
     */
    short OBDEngineTurnRate;
    /**
     * OBD里程       单位：Km
     */
    int OBDMileage;
    /**
     * 进气温度      单位：℃ (A-40)
     */
    byte OBDInletTemperature;
    /**
     * 空气流量      单位：g/s
     */
    short OBDAirMassFlow;

    /**
     * 节气门绝对位置 单位：%
     */
    byte OBDThrottlePosition;
    /**
     * 控制模块电压   单位：V
     */
    byte OBDControlVoltage;
    /**
     * 环境温度      单位：℃ (A-40）
     */
    byte OBDAmbientTemperature;
    /**
     * 长期燃油修正  单位：%  (A-128)*100/128
     */
    byte OBDLongTermFuelCorrection;
    /**
     * 汽缸1点火提前角 单位：°
     */
    byte OBDCylinder1FireAngle;
    /**
     * 进气歧管绝对压力 单位：kpa
     */
    byte OBDInletBranchPressure;
    /**
     * 本车OBD标准
     */
    byte OBDStandard;
    /**
     * 每小时油耗    单位：L/H
     */
    byte OBDGasolineConsumptionPerHour;
    /**
     * 100km油耗     单位：L/100Km (A/10)
     */
    byte OBDGasolineConsumptionPerHunKm;
    /**
     * 油量
     */
    int OBDOilValue;

    @Override
    public byte getAdditionalId() {
        return (byte) 0xE2;
    }

    @Override
    public byte getAdditionalLength() {
        return 25;
    }

    @Override
    public byte[] WriteToBytes() {
        if (BitConverter.IsLittleEndian) {
            byte[] bytes = new byte[25];
            bytes[0] = OBDOverloadCalculate;
            bytes[1] = OBDCoolantTemperature;
            bytes[2] = OBDSpeed;
            bytes[3] = (byte) (OBDEngineTurnRate >> 8);
            bytes[4] = (byte) OBDEngineTurnRate;
            bytes[5] = (byte) (OBDMileage >> 24);
            bytes[6] = (byte) (OBDMileage >> 16);
            bytes[7] = (byte) (OBDMileage >> 8);
            bytes[8] = (byte) OBDMileage;
            bytes[9] = OBDInletTemperature;
            bytes[10] = (byte) (OBDAirMassFlow >> 8);
            bytes[11] = (byte) OBDAirMassFlow;
            bytes[12] = OBDThrottlePosition;
            bytes[13] = OBDControlVoltage;
            bytes[14] = OBDAmbientTemperature;
            bytes[15] = OBDLongTermFuelCorrection;
            bytes[16] = OBDCylinder1FireAngle;
            bytes[17] = OBDInletBranchPressure;
            bytes[18] = OBDStandard;
            bytes[19] = OBDGasolineConsumptionPerHour;
            bytes[20] = OBDGasolineConsumptionPerHunKm;
            bytes[21] = (byte) (OBDOilValue >> 24);
            bytes[22] = (byte) (OBDOilValue >> 16);
            bytes[23] = (byte) (OBDOilValue >> 8);
            bytes[24] = (byte) (OBDOilValue);
            return bytes;
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(OBDOverloadCalculate);
            byteArrayOutputStream.write(OBDCoolantTemperature);
            byteArrayOutputStream.write(OBDSpeed);
            byteArrayOutputStream.write(OBDEngineTurnRate);
            byteArrayOutputStream.write(OBDMileage);
            byteArrayOutputStream.write(OBDInletTemperature);
            byteArrayOutputStream.write(OBDAirMassFlow);
            byteArrayOutputStream.write(OBDThrottlePosition);
            byteArrayOutputStream.write(OBDControlVoltage);
            byteArrayOutputStream.write(OBDAmbientTemperature);
            byteArrayOutputStream.write(OBDLongTermFuelCorrection);
            byteArrayOutputStream.write(OBDCylinder1FireAngle);
            byteArrayOutputStream.write(OBDInletBranchPressure);
            byteArrayOutputStream.write(OBDStandard);
            byteArrayOutputStream.write(OBDGasolineConsumptionPerHour);
            byteArrayOutputStream.write(OBDGasolineConsumptionPerHunKm);
            byteArrayOutputStream.write(OBDOilValue);

            return byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public void ReadFromBytes(byte[] bytes) {
        if (BitConverter.IsLittleEndian)
        {
            OBDOverloadCalculate = bytes[0];
            OBDCoolantTemperature = bytes[1];
            OBDSpeed = bytes[2];
            OBDEngineTurnRate = (short)((bytes[3] << 8) + bytes[4]);
            OBDMileage = (int)((bytes[5] << 24) + (bytes[6] << 16) + (bytes[7] << 8) + bytes[8]);
            OBDInletTemperature = bytes[9];
            OBDAirMassFlow = (short)((bytes[10] << 8) + bytes[11]);
            OBDThrottlePosition = bytes[12];
            OBDControlVoltage = bytes[13];
            OBDAmbientTemperature = bytes[14];
            OBDLongTermFuelCorrection = bytes[15];
            OBDCylinder1FireAngle = bytes[16];
            OBDInletBranchPressure = bytes[17];
            OBDStandard = bytes[18];
            OBDGasolineConsumptionPerHour = bytes[19];
            OBDGasolineConsumptionPerHunKm = bytes[20];
            OBDOilValue = (int)((bytes[21] << 24) + (bytes[22] << 16) + (bytes[23] << 8) + bytes[24]);
        }
        else{
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                OBDOverloadCalculate = dataInputStream.readByte();
                OBDCoolantTemperature = dataInputStream.readByte();
                OBDSpeed = dataInputStream.readByte();

                OBDEngineTurnRate = dataInputStream.readShort();
                OBDMileage = dataInputStream.readInt();
                OBDInletTemperature=dataInputStream.readByte();

                OBDAirMassFlow = dataInputStream.readShort();
                OBDThrottlePosition = dataInputStream.readByte();
                OBDControlVoltage = dataInputStream.readByte();

                OBDAmbientTemperature = dataInputStream.readByte();
                OBDLongTermFuelCorrection=dataInputStream.readByte();
                OBDCylinder1FireAngle = dataInputStream.readByte();

                OBDInletBranchPressure = dataInputStream.readByte();
                OBDStandard = dataInputStream.readByte();
                OBDGasolineConsumptionPerHour = dataInputStream.readByte();

                OBDGasolineConsumptionPerHunKm = dataInputStream.readByte();
                OBDOilValue=dataInputStream.readInt();
            }
            catch (IOException e){

            }

        }
    }

    public byte getOBDOverloadCalculate() {
        return OBDOverloadCalculate;
    }

    public void setOBDOverloadCalculate(byte OBDOverloadCalculate) {
        this.OBDOverloadCalculate = OBDOverloadCalculate;
    }

    public byte getOBDCoolantTemperature() {
        return OBDCoolantTemperature;
    }

    public void setOBDCoolantTemperature(byte OBDCoolantTemperature) {
        this.OBDCoolantTemperature = OBDCoolantTemperature;
    }

    public byte getOBDSpeed() {
        return OBDSpeed;
    }

    public void setOBDSpeed(byte OBDSpeed) {
        this.OBDSpeed = OBDSpeed;
    }

    public short getOBDEngineTurnRate() {
        return OBDEngineTurnRate;
    }

    public void setOBDEngineTurnRate(short OBDEngineTurnRate) {
        this.OBDEngineTurnRate = OBDEngineTurnRate;
    }

    public int getOBDMileage() {
        return OBDMileage;
    }

    public void setOBDMileage(int OBDMileage) {
        this.OBDMileage = OBDMileage;
    }

    public byte getOBDInletTemperature() {
        return OBDInletTemperature;
    }

    public void setOBDInletTemperature(byte OBDInletTemperature) {
        this.OBDInletTemperature = OBDInletTemperature;
    }

    public short getOBDAirMassFlow() {
        return OBDAirMassFlow;
    }

    public void setOBDAirMassFlow(short OBDAirMassFlow) {
        this.OBDAirMassFlow = OBDAirMassFlow;
    }

    public byte getOBDThrottlePosition() {
        return OBDThrottlePosition;
    }

    public void setOBDThrottlePosition(byte OBDThrottlePosition) {
        this.OBDThrottlePosition = OBDThrottlePosition;
    }

    public byte getOBDControlVoltage() {
        return OBDControlVoltage;
    }

    public void setOBDControlVoltage(byte OBDControlVoltage) {
        this.OBDControlVoltage = OBDControlVoltage;
    }

    public byte getOBDAmbientTemperature() {
        return OBDAmbientTemperature;
    }

    public void setOBDAmbientTemperature(byte OBDAmbientTemperature) {
        this.OBDAmbientTemperature = OBDAmbientTemperature;
    }

    public byte getOBDLongTermFuelCorrection() {
        return OBDLongTermFuelCorrection;
    }

    public void setOBDLongTermFuelCorrection(byte OBDLongTermFuelCorrection) {
        this.OBDLongTermFuelCorrection = OBDLongTermFuelCorrection;
    }

    public byte getOBDCylinder1FireAngle() {
        return OBDCylinder1FireAngle;
    }

    public void setOBDCylinder1FireAngle(byte OBDCylinder1FireAngle) {
        this.OBDCylinder1FireAngle = OBDCylinder1FireAngle;
    }

    public byte getOBDInletBranchPressure() {
        return OBDInletBranchPressure;
    }

    public void setOBDInletBranchPressure(byte OBDInletBranchPressure) {
        this.OBDInletBranchPressure = OBDInletBranchPressure;
    }

    public byte getOBDStandard() {
        return OBDStandard;
    }

    public void setOBDStandard(byte OBDStandard) {
        this.OBDStandard = OBDStandard;
    }

    public byte getOBDGasolineConsumptionPerHour() {
        return OBDGasolineConsumptionPerHour;
    }

    public void setOBDGasolineConsumptionPerHour(byte OBDGasolineConsumptionPerHour) {
        this.OBDGasolineConsumptionPerHour = OBDGasolineConsumptionPerHour;
    }

    public byte getOBDGasolineConsumptionPerHunKm() {
        return OBDGasolineConsumptionPerHunKm;
    }

    public void setOBDGasolineConsumptionPerHunKm(byte OBDGasolineConsumptionPerHunKm) {
        this.OBDGasolineConsumptionPerHunKm = OBDGasolineConsumptionPerHunKm;
    }

    public int getOBDOilValue() {
        return OBDOilValue;
    }

    public void setOBDOilValue(int OBDOilValue) {
        this.OBDOilValue = OBDOilValue;
    }
}
