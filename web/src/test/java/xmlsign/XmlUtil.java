package xmlsign;

import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class XmlUtil {

    static BASE64Encoder encoder = new BASE64Encoder();
    static BASE64Decoder decoder = new BASE64Decoder();
    public static String signXml(String sourceXml, String privateKey) throws Exception {
        Document document = toDocument(sourceXml);

        //Create XML Signature Factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(decoder.decodeBuffer(privateKey));
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        DOMSignContext signContext = new DOMSignContext(priKey, document.getDocumentElement());

        Reference ref = null;
        SignedInfo signedInfo = null;
        try {
            ref = sigFactory.newReference("", sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(sigFactory.newTransform(Transform.ENVELOPED,
                            (TransformParameterSpec) null)), null, null);
            signedInfo = sigFactory.newSignedInfo(
                    sigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                            (C14NMethodParameterSpec) null),
                    sigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                    Collections.singletonList(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }

        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, null);
        try {
            signature.sign(signContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toString(document);
    }

    private static String toString(Document document) throws Exception {
        // output as String
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();

        try(StringWriter stringWriter = new StringWriter()){
            StreamResult streamRes = new StreamResult(stringWriter);
            transformer.transform(new DOMSource(document), streamRes);
            return stringWriter.toString();
        }
    }

    private static Document toDocument(String sourceXml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try (InputStream inputStream = IOUtils.toInputStream(sourceXml, "UTF-8")){
            DocumentBuilder builder = dbf.newDocumentBuilder();

            return builder.parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyXml(String signedXml, String publicKey) throws Exception {
        Document doc = toDocument(signedXml);

        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            return false;
        }
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoder.decodeBuffer(publicKey));
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        DOMValidateContext valContext = new DOMValidateContext(pubKey, nl.item(0));
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);
        return signature.validate(valContext);
    }

    public static String rmSignNode(String signedXml,String parentNodeName, String removeNodeName) throws Exception {
        Document doc = toDocument(signedXml);
        Node parent = doc.getElementsByTagName(parentNodeName).item(0);
        NodeList nodeList = doc.getElementsByTagName(removeNodeName);
        if (nodeList.getLength() > 0) {
            parent.removeChild(nodeList.item(0));
        }
        return toString(doc);
    }
}
