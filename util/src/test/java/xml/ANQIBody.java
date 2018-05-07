package xml;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author huqinsong
 * @date 2018/5/3
 */
public class ANQIBody {

    private List<String> anqi;

    private List<String> anqi2;

    public ANQIBody() {
    }

    public ANQIBody(String... s) {
        anqi = Arrays.asList(s);
        anqi2 = Arrays.asList(s);
    }


    @XmlElement(name = "anQi")
    public List<String> getAnqi()
    {
        return anqi;
    }

    public void setAnqi(List<String> anqi)
    {
        this.anqi = anqi;
    }

    @XmlElement(name = "ANQI2")
    public List<String> getAnqi2() {
        return anqi2;
    }

    public void setAnqi2(List<String> anqi2) {
        this.anqi2 = anqi2;
    }
}
