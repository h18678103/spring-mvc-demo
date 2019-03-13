package xmlsign;

/**
 * @author huqinsong
 * @date 2019/3/5
 */
public class XmlSignTest {


    private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIsxmhgEjm/60r/Tr3fjde1Uy+btkXbOGMhXLMzoAU1j2WgEsziwq66p7" +
            "/MtVzcxYl6jxKGvszFz3aWqr9SFqNmCu5URn+5I/9ZAbA7AjvOKBDskGGY84VhPusVGYklZTrRvevUGvmEsGzQGE80moych7tc6KufyUCC22JnMIdQQIDAQAB";

    private static String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIizGaGASOb/rSv9Ovd" +
            "+N17VTL5u2Rds4YyFcszOgBTWPZaASzOLCrrqnv8y1XNzFiXqPEoa+zMXPdpaqv1IWo2YK7lRGf7kj/1kBsDsCO84oEOyQYZjzhWE+6xUZiSVlOtG969Qa+YSwbNAYTzSajJyHu1zoq5/JQILbYmcwh1BAgMBAAECgYA1C4K7p8MiWD6Dnl0+Yqzr0MtZ7juxml+fnr0gelU7QLNfbmNAGglM5PRTxQ8NILCMf+trOjMV7UPkMmC9rlDsoK9Fq1QEQL64YiLgpS9qx7JhUEWc1a3jcPr+0L0LT4goaXKesmrETN9ewP7kSF+7deu3T4nNhfR8FS59/KPYQQJBAPQgqe3w/iuYphawXr5RtM9Qz6zUDXb8UZrlukCB2YlpnyzM96L9d8hwHcx3ptHKp5I92mL1QGV5/NRSSBhgexkCQQCPWPsgqhGtROwBVUSxfCiXtH13j9P+TCwSxRqeNM3diV5tWHz6xv+SsJMahqFBCc4+I8Ms2jHqVeNTKnwaQKBpAkALas+nsiKg9VWnL/5uVjHnlDbyNUSf8PL8FIxeYfRDie4I0VkvhT0p4ZxzKJFjtF+b5kqlYCTv2gGqCGMYyMJZAkAFCG3AxtfaCopyvYis5pwSP38MPJuH2SrO+eamWVpFBfavxiSVNXlxsAtqKgHtY6e/HMhUgdCw1pUlrV8wGMbJAkEAuQNMyuU1bd59B9QJsal9nYLYNTiY41moSDEWgbYS+5qXz/11gL0dj/5L6I+F5xUXlhdgDAswMfYc6u1fG07E4Q==";


    private static String xml = "<abc><def>ghi</def></abc>";


    public static void main(String[] args) throws Exception {
        String s = XmlUtil.signXml(xml, priKey);
        boolean b = XmlUtil.verifyXml(s, pubKey);
        System.out.println("b="+b);
    }
}
