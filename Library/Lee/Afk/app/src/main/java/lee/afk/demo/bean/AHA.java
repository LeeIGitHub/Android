package lee.afk.demo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dahan on 2016/3/8.
 */
public class AHA implements Serializable {

    /**
     * ReturnCode : 1
     * ReturnMsg : 成功
     * ReturnData : [{"LinkUrl":"http://fh.56wmm.com/Activity/LuckDraw?UserId=300000352","ImageUrl":"http://img2.56wmm.com/Banner/ae25c1eaaaa245e399ad0b1ed3846551.jpg"},{"LinkUrl":"http://fh.56wmm.com/Activity/FirstLotteryRecord?UserId=300000352","ImageUrl":"http://img2.56wmm.com/Banner/852355eba21842b7ba7418c22d9a8ea5.jpg"},{"LinkUrl":"http://fh.56wmm.com/Activity/LuckDraw?UserId=300000352","ImageUrl":"http://img2.56wmm.com/Banner/2ab302c89068459f9d9fdb92285c5788.jpg"},{"LinkUrl":"http://fh.56wmm.com/Activity/LuckDraw?UserId=300000352","ImageUrl":"http://img2.56wmm.com/Banner/0c9acbcc9ac84262b8ff9e543b4e39d4.jpg"},{"LinkUrl":"http://fh.56wmm.com/Activity/LuckDraw?UserId=300000352","ImageUrl":"http://img2.56wmm.com/Banner/77f231eeacca453b8d1399aa6722cabb.jpg"},{"LinkUrl":"","ImageUrl":"http://img2.56wmm.com/Banner/222aaf8fa986484384ee81f23bb02930.jpg"}]
     * ReturnTotalRecords : 0
     */

    private int ReturnCode;
    private String ReturnMsg;
    private int ReturnTotalRecords;
    /**
     * LinkUrl : http://fh.56wmm.com/Activity/LuckDraw?UserId=300000352
     * ImageUrl : http://img2.56wmm.com/Banner/ae25c1eaaaa245e399ad0b1ed3846551.jpg
     */

    private List<ReturnDataEntity> ReturnData;

    public void setReturnCode(int ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    public void setReturnMsg(String ReturnMsg) {
        this.ReturnMsg = ReturnMsg;
    }

    public void setReturnTotalRecords(int ReturnTotalRecords) {
        this.ReturnTotalRecords = ReturnTotalRecords;
    }

    public void setReturnData(List<ReturnDataEntity> ReturnData) {
        this.ReturnData = ReturnData;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public String getReturnMsg() {
        return ReturnMsg;
    }

    public int getReturnTotalRecords() {
        return ReturnTotalRecords;
    }

    public List<ReturnDataEntity> getReturnData() {
        return ReturnData;
    }

    public static class ReturnDataEntity {
        private String LinkUrl;
        private String ImageUrl;

        public void setLinkUrl(String LinkUrl) {
            this.LinkUrl = LinkUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getLinkUrl() {
            return LinkUrl;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        @Override
        public String toString() {
            return "ReturnDataEntity{" +
                    "LinkUrl='" + LinkUrl + '\'' +
                    ", ImageUrl='" + ImageUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AHA{" +
                "ReturnCode=" + ReturnCode +
                ", ReturnMsg='" + ReturnMsg + '\'' +
                ", ReturnTotalRecords=" + ReturnTotalRecords +
                ", ReturnData=" + ReturnData +
                '}';
    }
}
