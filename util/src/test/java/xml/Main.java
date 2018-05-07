package xml;

/**
 * @author huqinsong
 * @date 2018/5/3
 */
public class Main {
    public static void main(String[] args) throws Exception{
        ANQIHead head = new ANQIHead(1, "YYYY-MM-DD HH:MM:SS", "王安琪No1");
        ANQIBody body = new ANQIBody("00001", "00002", "00003");
        ANQIFile anqiFile1 = new ANQIFile(head, body);
        String xml = XmlHelper.objectToXML(ANQIFile.class, anqiFile1);
        System.out.println("===========");
        System.out.println(xml);
        System.out.println("===========");

        ANQIFile o = (ANQIFile) XmlHelper.xmlToObject(ANQIFile.class, xml);
        System.out.println(o.getaNQIBody().getAnqi().get(0));
        System.out.println(o.getaNQIBody().getAnqi2().get(1));
        System.out.println(o.getaNQIHead().getDateTime());

    }
}
