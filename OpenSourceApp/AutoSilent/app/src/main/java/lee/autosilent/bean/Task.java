package lee.autosilent.bean;


import java.io.Serializable;

/**
 * Created by dahan on 2015/10/21.
 */
public class Task implements Serializable{

    private int id;
    private String ssid;
    private String mac;
    private int mode;
    private int line;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", ssid='" + ssid + '\'' +
                ", mac='" + mac + '\'' +
                ", mode=" + mode +
                ", line=" + line +
                '}';
    }
}
