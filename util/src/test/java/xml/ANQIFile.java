package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author huqinsong
 * @date 2018/5/3
 */
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"aNQIHead", "aNQIBody"})
public class ANQIFile {

    private ANQIHead aNQIHead;

    private ANQIBody aNQIBody;

    public ANQIFile() {
    }

    public ANQIFile(ANQIHead aNQIHead, ANQIBody aNQIBody) {
        this.aNQIHead = aNQIHead;
        this.aNQIBody = aNQIBody;
    }

    @XmlElement(name = "head")
    public ANQIHead getaNQIHead() {
        return aNQIHead;
    }

    public void setaNQIHead(ANQIHead aNQIHead) {
        this.aNQIHead = aNQIHead;
    }

    @XmlElement(name = "body")
    public ANQIBody getaNQIBody() {
        return aNQIBody;
    }

    public void setaNQIBody(ANQIBody aNQIBody) {
        this.aNQIBody = aNQIBody;
    }
}
