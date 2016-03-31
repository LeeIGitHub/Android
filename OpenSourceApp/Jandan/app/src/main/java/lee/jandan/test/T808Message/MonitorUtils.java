package lee.jandan.test.T808Message;

/**
 * Created by eve on 2016/3/15 0015.
 */
public class MonitorUtils {
    /* 表B.1消息对照表
    序号	消息体名称	消息ID	序号	消息体名称	消息ID
    1	终端通用应答	0x0001	23	信息服务	0x8304
            2	平台通用应答	0x8001	24	电话回拨	0x8400
            3	终端心跳	0x0002	25	设置电话本	0x8401
            4	终端注册	0x0100	26	车辆控制	0x8500
            5	终端注册应答	0x8100	27	车辆控制应答	0x0500
            6	终端注销	0x0101	28	设置圆形区域	0x8600
            7	终端桨权	0x0102	29	删除圆形区域	0x8601
            8	设置终端参数	0x8103	30	设置矩形区域	0x8602
            9	查询终端参数	0x8104	31	删除矩形区域	0x8603
            10	查询终端参数应答	0x0104	32	设置多边形区域	0x8604
            11	终端控制	0x8105	33	删除多边形区域	0x8605
            12	位置信息汇报	0x0200	34	设置路线	0x8606
            13	位置信息查询	0x8201	35	删除路线	0x8607
            14	位置信息查询应答	0x0201	36	行驶记录仪数据采集命令	0x8700
            15	临时位置跟踪控制	0x8202	37	行驶记录仪数据上报	0x0700
            16	文本信息下发	0x8300	38	行驶记录仪参数下达命令	0x8701
            17	事件设置	0x8301	39	电子运单上报	0x0701
            18	事件报告	0x0301	40	驾驶员身份信息采集上报	0x0702
            19	提问下发	0x8105	41	多媒体事件信息上传	0x0800
            20	提问应答	0x0302	42	多媒体数据上传	0x0801
            21	信息点播菜单设置	0x8303	43	多媒体数据上传应答	0x8800
            22	信息点播/取消	0x0303	44	摄像头立即拍摄命令	0x8801
            45	存储多媒体数据检索	0x8802	51	数据压缩上报	0x0901
            46	存储多媒体数据检索应答	0x0803	52	平台RSA公钥	0x8A00
            47	存储多媒体数据上传	0x8803	53	终端RSA公钥	0x0A00
            48	录音开始命令	Ox8804	54	平台下行消息保留	0x8F00-0x8fff
            49	数据下行透传	0x8900	55	终端上行消息保留	0x0F00-0x0fff
            50	数据上行透传	0x0900*/
    private static int[] ms = {0x0001, 0x8001, 0x0002, 0x0100, 0x8100, 0x0101, 0x0102, 0x8103, 0x8104, 0x0104,
            0x8105, 0x0200, 0x8201, 0x0201, 0x8202, 0x8300, 0x8301, 0x0301, 0x8105, 0x0302,
            0x8303, 0x0303, 0x8304, 0x8400, 0x8401, 0x8500, 0x0500, 0x8600, 0x8601, 0x8602,
            0x8603, 0x8604, 0x8605, 0x8606, 0x8607, 0x8700, 0x0700, 0x8701, 0x0701, 0x0702,
            0x0800, 0x0801, 0x8800, 0x8801, 0x8802, 0x0803, 0x8803, 0x8804, 0x8900, 0x0900,
            0x0901, 0x8A00, 0x0A00, 0x8F00 - 0x8fff, 0x0F00 - 0x0fff};

    public static byte getMessageIdByIndex(int index) {

        return (byte) ms[index];
    }

    /* 表11终端参数设置各参数项定义及说明
     参数ID	数据类型	描述及要求
     0x0001	DWORD	终端心跳发送间隔，单位为秒(s)
     0x0002	DWORD	TCP消息应答超时时间，单位为秒(s)
     0x0003	DWORD	TCP消息重传次数
     0x0004	DWORD	UDP消息应答超时时间，单位为秒(s)
     0x0005	DWORD	UDP消息重传次数
     0x0006	DWORD	SMS消息应答超时时间，单位为秒(s)
     0x0007	DWORD	SMS消息重传次数
     0x0008-0x000F		保留
     0x0010	STRING	主服务器APN，无线通信拨号访问点。若网络制式为CDMA，则该处为PPP拨号号码
     0x0011	STRING	主服务器无线通信拨号用户名
     0x0012	STRING	主服务器无线通信拨号密码
     0x0013	STRING	主服务器地址，IP或域名
     0x0014	STRING	备份服务器APN，无线通信拨号访问点
     0x0015	STRING	备份服务器无线通信拨号用户名
     0x0016	STRING	备份服务器无线通信拨号密码
     0x0017	STRING	备份服务器地址，IP或域名
     0x0018	DWORD	服务器TCP端口
     0x0019	DWORD	服务器UDP端口
     0x001A-0x001F		保留
     0x0020	DWORD	位置汇报策略，0：定时汇报；1：定距汇报；2：定时和定距汇报
     0x0021	DWORD	位置汇报方案，0：根据ACC状态；1：根据登录状态和ACC状态，先判断登录状态，若登录再根据ACC状态
     0x0022	DWORD	驾驶员未登录汇报时间间隔，单位为秒(s),>0
             0x0023-0x0026	DWORD	保留
     0x0027	DWORD	休眠时汇报时间间隔，单位为秒(s),>0
             0x0028	DWORD	紧急报警时汇报时间间隔，单位为秒(s),>0
             0x0029	DWORD	缺省时间汇报间隔，单位为秒(s),>0
             0x002A-0x002B	DWORD	保留
     0x002C	DWORD	缺省距离汇报间隔，单位为米(m),>0
             0x002D	DWORD	驾驶员未登录汇报距离间隔，单位为米(m),>0
             0x002E	DWORD	休眠时汇报距离间隔，单位为米(m),>0
             0x002F	DWORD	紧急报警时汇报距离间隔，单位为米(m),>0
             0x0030	DWORD	拐点补传角度，<180°
             0x0031-0x003F		保留
     0x0040	STRING	监控平台电话号码
     0x0041	STRING	复位电话号码，可采用此电话号码拨打终端电话让终端复位
     0x0042	STRING	恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
     0x0043	STRING	监控平台SMS电话号码
     0x0044	STRING	接收终端SMS文本报警号码
     0x0045	DWORD	终端电话接听策略，0：自动接听；1：ACC ON时自动接听，OFF时手动接听
     0x0046	DWORD	每次最长通话时间，单位为秒(s),0为不允许通话，0xFFFFFFFF为不限制
     0x0047	DWORD	当月最长通话时间，单位为秒(s),0为不允许通话，0xFFFFFFFF为不限制
     0x0048	STRING	监听电话号码
     0x0049	STRING	监管平台特权短信号码
     0x004A-0x004F		保留
     0x0050	DWORD	报警屏蔽字。与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警被屏蔽
     0x0051	DWORD	报警发送文本SMS开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时发送文本SMS
     0x0052	DWORD	报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时摄像头拍摄
     0x0053	DWORD	报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警时牌的照片进行存储，否则实时长传
     0x0054	DWORD	关键标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警为关键报警
     0x0055	DWORD	最高速度，单位为公里每小时(km/h)
     0x0056	DWORD	超速持续时间，单位为秒(s)
     0x0057	DWORD	连续驾驶时间门限，单位为秒(s)
     0x0058	DWORD	当天累计驾驶时间门限，单位为秒(s)
     0x0059	DWORD	最小休息时间，单位为秒(s)
     0x005A	DWORD	最长停车时间，单位为秒(s)
     0x005B-0x006F		保留
     0x0070	DWORD	图像/视频质量，1-10,1最好
     0x0071	DWORD	亮度，0-255
             0x0072	DWORD	对比度，0-127
             0x0073	DWORD	饱和度，0-127
             0x0074	DWORD	色度，0-255
             0x0075-0x007F	DWORD
     0x0080	DWORD	车辆里程表读数，1/10km
     0x0081	DWORD	车辆所在的省域ID
     0x0082	DWORD	车辆所在的市域ID
     0x0083	DWORD	公安交通管理部门颁发的机动车号牌
     0x0084	DWORD	车牌颜色，按照JT/T415-2006的5.4.12*/
    public static byte getConfigIdByIndex(int index) {
        if (index < 85) {
            String hex = 0x00 + "" + index;
            return (byte) Integer.parseInt(hex, 16);
        } else if (index == 85) {
            return (byte) Integer.parseInt((0x0008 - 0x000F) + "", 16);
        } else if (index == 86) {
            return (byte) Integer.parseInt((0x001A - 0x001F) + "", 16);
        } else if (index == 87) {
            return (byte) Integer.parseInt((0x0023 - 0x0026) + "", 16);
        } else if (index == 88) {
            return (byte) Integer.parseInt((0x002A - 0x002B) + "", 16);
        } else if (index == 89) {
            return (byte) Integer.parseInt((0x0031 - 0x003F) + "", 16);
        } else if (index == 90) {
            return (byte) Integer.parseInt((0x004A - 0x004F) + "", 16);
        } else if (index == 91) {
            return (byte) Integer.parseInt((0x005B - 0x006F) + "", 16);
        } else {
            return (byte) Integer.parseInt((0x0075 - 0x007F) + "", 16);
        }
    }

    /*  表A.2外设类型编号表
      外设类型	编号
      行业信息终端机	0x01
      调度显示屏	0x02
      车载导航显示屏	0x03
      油量检测器	0x04
      加速度检测器	0x05
      防盗报警器	0x06
      接口扩展器	0x07
      载重检测器	0x08
      客流检测器	0x08
      通用传感器	0x0A*/
    public static byte getOutTypeByIndex(int index) {
        if (index == 9)
            index = 'A';
        String hex = 0x0 + "" + index;
        return (byte) Integer.parseInt(hex, 16);
    }

    /*  命令类型表
      协议类型	业务功能类型	命令类型
      外设通用协议	上电指示/应答	0x01
      链路探询/应答	0x02
      外设电源控制/应答	0x03
      查询外设版木号信息	0x04
      保留	0x05-0x3F
      专用协议	各种从机外设的专有功能业务协议	0x40-0Xff*/
    public static byte getShellTypeByIndex(int index) {
        String hex = "";
        if (index == 5) {
            hex = (0x05 - 0x3F) + "";
        } else if (index == 6) {
            hex = (0x40 - 0xFF) + "";
        } else {
            hex = "0x0" + index;
        }
        return (byte) Integer.parseInt(hex, 16);
    }
}
