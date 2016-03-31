package lee.jandan.test.bean;

import lee.jandan.test.T808Message.DataUtils;
import lee.jandan.test.T808Message.Tools;
import lee.jandan.test.protocol.IMessageBody;

/**
 * Created by eve on 2016/3/17 0017.
 */
public class BodyBean {

//   public static WeakHashMap<Integer, RegisterBody> RegisterMap = new WeakHashMap<Integer, RegisterBody>();

    public static class RegisterBody implements IMessageBody {
   /* 消息ID：0X0100。
    终端注册消息体数据格式见表6。
    表6终端注册消息体数据格式
    起始字节	字段	数据类型	描述及要求
    0	省域ID	WORD	标示终端安装车辆所在的省域，0保留，由平台取默认值。省域ID采用GB/T 2260中规定的行政区划代码六位中前两位。
            2	市县域ID	WORD	标示终端安装车辆所在的市域和县域，0保留，由平台取默认值。市县域ID采用GB/T 2260中规定的行政区划代码六位后四位。
            4	制造商ID	BYTE[5]	五个字节，终端制造商编码。
            9	终端型号	BYTE[8]	八个字节，此终端型号由制造商自行定义，位数不是八位的，补空格。
            17	终端ID	BYTE[7]	七个字节，由大写字母和数字组成，此终端ID由制造商自行定义。
            21	车牌颜色	BYTE	车牌颜色，按照JT/T 415-2006的5.4.12
            25	车牌	STRING	公安交通管理部门颁发的机动车号牌*/

        String provinceId;
        String cityId;
        String manufacturerId;//5
        String terminalType;//8
        String terminalId;//7
        String plateColor;
        String plateNo;

        public RegisterBody() {
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getManufacturerId() {
            return manufacturerId;
        }

        public void setManufacturerId(String manufacturerId) {
            this.manufacturerId = manufacturerId;
        }

        public String getTerminalType() {
            return terminalType;
        }

        public void setTerminalType(String terminalType) {
            this.terminalType = terminalType;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public void setTerminalId(String terminalId) {
            this.terminalId = terminalId;
        }

        public String getPlateColor() {
            return plateColor;
        }

        public void setPlateColor(String plateColor) {
            this.plateColor = plateColor;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        @Override
        public byte[] WriteToBytes() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(DataUtils.toHexString(Integer.parseInt(getProvinceId())));
            buffer.append(DataUtils.toHexString(Integer.parseInt(getCityId())));
            buffer.append(Tools.ToHexString(getManufacturerId()));
            buffer.append(Tools.ToHexString(getTerminalType()));
            buffer.append(Tools.ToHexString(getTerminalId()));
            buffer.append(DataUtils.toHexString(Integer.parseInt(getPlateColor())));
            buffer.append(Tools.ToHexString(getPlateNo()));
          /*  MyBuffer buff = new MyBuffer();
            if (BitConverter.IsLittleEndian) {
                java.util.ArrayList<Byte> bytes = new java.util.ArrayList<Byte>(50);
                buff.put((byte) (Short.parseShort(getProvinceId()) >> 8));
                buff.put((byte) Short.parseShort(getProvinceId()));
                buff.put((byte) (Short.parseShort(getCityId()) >> 8));
                buff.put((byte) Short.parseShort(getCityId()));
                buff.put(getManufacturerId(), 5);
                buff.put(getTerminalType(), 20);
                buff.put(Byte.parseByte(getTerminalId().substring(0, 2), 16));
                buff.put(Byte.parseByte(getTerminalId().substring(2, 4), 16));
                buff.put(Byte.parseByte(getTerminalId().substring(4, 6), 16));
                buff.put(Byte.parseByte(getTerminalId().substring(6, 8), 16));
                buff.put(Byte.parseByte(getTerminalId().substring(8, 10), 16));
                buff.put(Byte.parseByte(getTerminalId().substring(10, 12), 16));
                buff.put((byte) 0xFF);
                // buff.putRange(GetFixedSizedBytes(TerminalId, 7, 0x00));
                buff.put(Byte.parseByte(getPlateColor()));
                buff.put(getPlateNo());
                buff.put((byte) 0x00);
                return buff.array();
            } else {

                try {
                    try {
                        buff.put(getProvinceId());
                        buff.put(getCityId());
                        buff.put(getManufacturerId(), 5);
                        buff
                                .put(getTerminalType(), 20);
                        buff.put(Byte
                                .parseByte(getTerminalId().substring(0, 2), 16));
                        buff.put(Byte
                                .parseByte(getTerminalId().substring(2, 4), 16));
                        buff.put(Byte
                                .parseByte(getTerminalId().substring(4, 6), 16));
                        buff.put(Byte
                                .parseByte(getTerminalId().substring(6, 8), 16));
                        buff.put(Byte.parseByte(getTerminalId().substring(8, 10),
                                16));
                        buff.put(Byte.parseByte(getTerminalId().substring(10, 12),
                                16));
                        buff.put((byte) 0xFF);
                        // buff.put(GetFixedSizedBytes(TerminalId, 7, 0x00));
                        buff.put(Byte.parseByte(getPlateColor()));
                        buff.put(getPlateNo());
                        buff.put((byte) 0x00);
                    } finally {

                    }
                    return buff.array();
                } finally {

                }

            }*/
            return Tools.HexString2Bytes(buffer.toString());
        }

        @Override
        public void ReadFromBytes(byte[] messageBodyBytes) {

        }
    }

    /*    Logger.e("tcp", Integer.valueOf("0B", 16) + "=" +DataUtils.toHexString(11) + "\n" +
                Integer.valueOf("044D", 16) + "=" + DataUtils.toHexString(1101) + "\n" +
                Tools.getStringFromHex("3132333435") + "=" + Tools.ToHexString("12345") + "\n" +
                Tools.getStringFromHex("4142313233343536") + "=" + Tools.ToHexString("AB123456") + "\n" +
                Tools.getStringFromHex("41313132323333") + "=" + Tools.ToHexString("A112233") + "\n" +
                Integer.parseInt("00", 16) + "=" + DataUtils.toHexString(00)+ "\n" +
                Tools.getStringFromHex("BEA9513233343536") + "=" + Tools.ToHexString("京Q23456"));*/
    public static byte[] getRegister(RegisterBody registerBody) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(DataUtils.toHexString(Integer.parseInt(registerBody.getProvinceId())));
        buffer.append(DataUtils.toHexString(Integer.parseInt(registerBody.getCityId())));
        buffer.append(Tools.ToHexString(registerBody.getManufacturerId()));
        buffer.append(Tools.ToHexString(registerBody.getTerminalType()));
        buffer.append(Tools.ToHexString(registerBody.getTerminalId()));
        buffer.append(DataUtils.toHexString(Integer.parseInt(registerBody.getPlateColor())));
        buffer.append(Tools.ToHexString(registerBody.getPlateNo()));

        return Tools.HexString2Bytes(buffer.toString());

    }
}
