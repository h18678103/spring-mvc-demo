package xml;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author huqinsong
 * @date 2018/5/3
 */
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"s", "i", "goodBoy", "goodGirl"})
public class AeBe {

    private int i;

    private String s;

    private String goodBoy;

    private String goodGirl;

    @XmlElement(name = "ix")
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @XmlElement(name = "sx")
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getGoodBoy() {
        return goodBoy;
    }

    public void setGoodBoy(String goodBoy) {
        this.goodBoy = goodBoy;
    }

    public String getGoodGirl() {
        return goodGirl;
    }

    public void setGoodGirl(String goodGirl) {
        this.goodGirl = goodGirl;
    }

    public static void main(String[] args) throws JAXBException {
        AeBe aeBe = new AeBe();
        aeBe.setI(100);
        aeBe.setS("sss");
        aeBe.setGoodBoy("LiLei");
        aeBe.setGoodGirl("HanMeiMei");
        String s = XmlHelper.objectToXML(AeBe.class, aeBe);
        System.out.println(s);
        AeBe o = (AeBe) XmlHelper.xmlToObject(AeBe.class, s);
        System.out.println(o.getI());
        System.out.println(o.getS());
        System.out.println(o.getGoodBoy());
        System.out.println(o.getGoodGirl());
    }
}
