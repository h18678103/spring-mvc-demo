package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author huqinsong
 * @date 2018/5/3
 */
@XmlType(propOrder = {"anqiNum", "dateTime", "appovalNum"})
public class ANQIHead {

    private int anqiNum;
    private String dateTime;
    private String appovalNum;

    public ANQIHead() {
    }

    public ANQIHead(int anqiNum, String dateTime, String appovalNum) {
        this.anqiNum = anqiNum;
        this.dateTime = dateTime;
        this.appovalNum = appovalNum;
    }

    @XmlElement(name = "num")
    public int getAnqiNum() {
        return anqiNum;
    }

    public void setAnqiNum(int anqiNum) {
        this.anqiNum = anqiNum;
    }

    @XmlElement(name = "date")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @XmlElement(name = "num")
    public String getAppovalNum() {
        return appovalNum;
    }

    public void setAppovalNum(String appovalNum) {
        this.appovalNum = appovalNum;
    }
}
