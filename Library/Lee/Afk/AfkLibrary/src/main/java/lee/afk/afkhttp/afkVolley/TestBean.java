package lee.afk.afkhttp.afkVolley;

/**
 * Created by dahan on 2015/9/2.
 */
public class TestBean  {

    /**
     * ReturnCode : 1
     * ReturnMsg : 成功
     * ReturnData :
     * ReturnTotalRecords : 0
     */

    private int ReturnCode;
    private String ReturnMsg;
    private String ReturnData;
    private int ReturnTotalRecords;

    public void setReturnCode(int ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    public void setReturnMsg(String ReturnMsg) {
        this.ReturnMsg = ReturnMsg;
    }

    public void setReturnData(String ReturnData) {
        this.ReturnData = ReturnData;
    }

    public void setReturnTotalRecords(int ReturnTotalRecords) {
        this.ReturnTotalRecords = ReturnTotalRecords;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public String getReturnMsg() {
        return ReturnMsg;
    }

    public String getReturnData() {
        return ReturnData;
    }

    public int getReturnTotalRecords() {
        return ReturnTotalRecords;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "ReturnCode=" + ReturnCode +
                ", ReturnMsg='" + ReturnMsg + '\'' +
                ", ReturnData='" + ReturnData + '\'' +
                ", ReturnTotalRecords=" + ReturnTotalRecords +
                '}';
    }

    public class Bean extends LeeBaseBean<TestBean>{}
}
