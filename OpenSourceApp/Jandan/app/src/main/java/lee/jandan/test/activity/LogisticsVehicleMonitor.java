package lee.jandan.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;

import lee.afk.afkutils.log.LeeLog;
import lee.jandan.R;
import lee.jandan.test.T808Message.BitConverter;
import lee.jandan.test.T808Message.MonitorUtils;
import lee.jandan.test.T808Message.T808Message;
import lee.jandan.test.T808Message.T808MessageFactory;
import lee.jandan.test.T808Message.T808MessageHeader;
import lee.jandan.test.T808Message.Tools;
import lee.jandan.test.bean.BodyBean;
import lee.jandan.test.protocol.JT_0100;

public class LogisticsVehicleMonitor extends Activity implements View.OnClickListener {
    //    private static MyHandler handler;
    private static String ServerIpV4 = "192.168.3.5";
    private static String ServerIpDef = "115.182.194.15";
    //    private static String LocalIp = "192.168.3.5";
    private static String LocalIp = "192.168.1.217";
//    private static String LocalIp = "115.182.194.13";
    //    private static int SERVER_PORT = 8085;
    private static int SERVER_PORT = 8080;
//    private static int SERVER_PORT = 8898;
    static TextView textView;
    Button btServer;
    Button btClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_808);
        textView = (TextView) findViewById(R.id.test);
        btServer = (Button) findViewById(R.id.tcp_server);
        btServer.setOnClickListener(this);
        btClient = (Button) findViewById(R.id.tcp_client);
        btClient.setOnClickListener(this);
       /* if (handler == null)
            handler = new MyHandler(this);*/
//        终端上行鉴权指令
//        new Thread(new MyRunnableClient(this, "7E0102000B01310001112200023132333435363738393041487E")).start();

    }


    /*public static class MyHandler extends Handler {
        WeakReference<Activity> reference;
        LogisticsVehicleMonitor activity;

        public MyHandler(Activity activity) {
            this.reference = new WeakReference<Activity>(activity);
            activity = reference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String m = msg.getData().getString("msg");
                activity.textView.append("come from server" + m + "\n");
            }
        }
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tcp_server:
//                终端注册
//                7e0102000b013100011122000631323334353637383930414c7e
//                7E0100 0022 013100011122 0004 00 0B 044D 3132333435 4142313233343536 41313132323333 00 BEA9513233343536 00637E
//                7E010000220131000111220002000B044D313233343541423132333435364131313232333300BEA951323334353600657E
//                                            0b044d313233343541423132333435364131313232333300fdfd513233343536
                T808Message t808Message = new T808Message();
                T808MessageHeader header = new T808MessageHeader();
                /*
                BodyBean.RegisterBody registerBody = new BodyBean.RegisterBody();
                registerBody.setProvinceId("11");
                registerBody.setCityId("1101");
                registerBody.setManufacturerId("12345");
                registerBody.setTerminalType("AB123456");
                registerBody.setTerminalId("A112233");
                registerBody.setPlateColor("00");
                registerBody.setPlateNo("京Q23456");
*/
                /**
                 消息Id*/
//                header.setMessageType(Integer.parseInt("0100", 16));//Integer.parseInt("0100", 16)
                /**
                 *  消息体属性**/
//                header.setMessageBodyProperty((short) Integer.parseInt("0022", 16));
                /**
                 *  流水号*/
//                header.setMessageSerialNo((short) Integer.parseInt("0002", 16));

                /*分包号*/
//                header.setMessagePacketNo((short) Integer.parseInt("00", 16));
//                header.setIsPackage(false);
                header.setSimId("13122279802");
                header.setMessageType(0x0100);
//                String bodyS = "0B044D313233343541423132333435364131313232333300BEA9513233343536";
                /*11 1101 12345 AB123456 A112233 0 京Q23456*/
                t808Message.setHeader(header);
//                t808Message.setMessageContents(registerBody);

//                header.setMessageSize(bodyS.length());
               /* t808Message.setPacketDescr();
                t808Message.setStatus();*/
//                t808Message.setHeader(header);
//                t808Message.setMessageContents(T808MessageFactory
//                        .Create(header.getMessageType(),BodyBean.getRegister(registerBody)));
                // BodyBean.getRegister(registerBody)
                JT_0100 ji = new JT_0100();
                ji.setProvinceId((short)11);
                ji.setCityId((short) 1101);
                ji.setManufactureId("12345");
                ji.setTerminalModelNo("AB12345");
                ji.setTerminalId(Tools.ToHexString("A112233"));
                ji.setPlateColor((byte)0);
                ji.setLicenseNo("京Q1");
                t808Message.setMessageContents(ji);//Tools.HexString2Bytes(bodyS)


               /* 0100 0022 013100011122 0002 00
                0b 04 4d 31 32 33 34 35 41 42 31 32 33 34 35 36 41 31 31 32 32 33 33 00 fd fd 51 32 33 34 35 36
                0065 7e*/

                new Thread(new MyRunnableClient(this, t808Message)).start();
                break;
            case R.id.tcp_client:
//                7E 0102 000B 013100011122 0017 31323334353637383930 415D 7E
//                7E0102000B013100011122001731323334353637383930415D7E
                T808Message t = new T808Message();
                T808MessageHeader h = new T808MessageHeader();
                 /* 消息Id*/
                h.setMessageType(BitConverter.ToUInt16(Tools.HexString2Bytes(Tools.ToHexString(MonitorUtils.getMessageIdByIndex(5))), 10));
                   /* 消息体属性*/
                h.setMessageBodyProperty((short) BitConverter.ToUInt16(Tools.HexString2Bytes("000B"), 10));
               /* 分包号*/
//                h.setMessagePacketNo((short) BitConverter.ToUInt16(Tools.HexString2Bytes("31"), 10));
                   /* 流水号*/
                h.setMessageSerialNo((short) BitConverter.ToUInt16(Tools.HexString2Bytes("0017"), 10));
                h.setIsPackage(false);
                h.setSimId("13100011122");
                t.setHeader(h);
                t.setMessageContents(T808MessageFactory.Create(h.getMessageType(), Tools.HexString2Bytes("31323334353637383930")));
                new Thread(new MyRunnableClient(this, t)).start();
                break;
        }
    }

    public static class MyRunnableClient implements Runnable {
        WeakReference<Activity> reference;
        LogisticsVehicleMonitor activity;
        //        String hexString;
        T808Message t808Message;

        public MyRunnableClient(Activity activity, T808Message t808Message) {
            this.reference = new WeakReference<Activity>(activity);
            this.t808Message = t808Message;
            activity = reference.get();
        }

        @Override
        public void run() {
            try {
//                Logger.e("tcp", "Client：Connecting");//5b424034336630626536300aServerIpDef, SERVER_PORT)
                LeeLog.e("tcp" + "Client：Connecting");
                Socket socket = new Socket(LocalIp, SERVER_PORT);
                //发送给服务端的消息
//                String message = "Message from Android phone";
                try {
                    //第二个参数为True则为自动flush
                   /* PrintWriter out = new PrintWriter(
                            new BufferedWriter(new OutputStreamWriter(
                                    socket.getOutputStream())), true);
//                    标识位	消息头	消息体	校验码	标识位
//                  终止
//                    byte[] hb = DataUtils.hexStringToBytes(hexString);
                    out.println(EncodingUtils.getAsciiBytes(hexString));*/
//                    DataUtils.hexString2String(hexString)
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//                    dos.writeBytes();
                    dos.write(t808Message.WriteToBytes());
                    socket.shutdownOutput();
                    InputStream inputStream = socket.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, buffer.length);
                    }
                    t808Message.ReadFromBytes(baos.toByteArray());
                   /* BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String reply = null;
                    while (!((reply = br.readLine()) == null)) {
                        Logger.e("tcp", DataUtils.string2hexString(reply));
                    }
                    br.close();*/
                    baos.close();
                    inputStream.close();
                    dos.close();
                    socket.sendUrgentData(0xFF);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //关闭Socket
                    socket.close();
                    System.out.println("Client:Socket closed");
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
