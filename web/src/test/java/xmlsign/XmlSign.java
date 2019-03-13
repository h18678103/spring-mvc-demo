package xmlsign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;

/**
 * @author huqinsong
 * @date 2019/3/5
 */
public class XmlSign {

    public String genXmlSign(){
        try {
            XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");

            Transform envelopedTransform = xmlSignatureFactory.newTransform(Transform.ENVELOPED , (XMLStructure) null);
            DigestMethod sha1DigestMethod = xmlSignatureFactory.newDigestMethod(DigestMethod.SHA1 , null);

            //创建reference元素
            Reference reference = xmlSignatureFactory.newReference("" ,
                    sha1DigestMethod ,
                    Collections.singletonList(envelopedTransform),
                    null , null);

            SignatureMethod rsaSignatureMethod = xmlSignatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1 , null);
            CanonicalizationMethod canonicalizationMethod = xmlSignatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE ,
                    (XMLStructure) null);
            //创建SignedInfo元素
            SignedInfo signedInfo = xmlSignatureFactory.newSignedInfo(
                    canonicalizationMethod ,
                    rsaSignatureMethod,
                    Collections.singletonList(reference));

            //创建密钥对
            KeyStore keyStore = KeyStore.getInstance("PKCS12");


            String pfxFile = null;
            File file = new File(pfxFile);
            FileInputStream fis = new FileInputStream(file);
            char[] password = new char[0];
            keyStore.load(fis, password);
            fis.close();

            //获取别名
            Enumeration enumas = keyStore.aliases();
            String alias = null;
            while (enumas.hasMoreElements()) {
                alias = (String) enumas.nextElement();
            }

            //准备密钥对
            Key key = keyStore.getKey(alias, password);
            KeyPair keyPair = null;
            if (key instanceof PrivateKey) {
                Certificate cert = keyStore.getCertificate(alias);
                PublicKey publicKey = cert.getPublicKey();
                keyPair = new KeyPair(publicKey, (PrivateKey) key);
            }

            X509Certificate x509 = (X509Certificate) keyStore.getCertificate(alias);

            KeyInfoFactory keyInfoFactory = xmlSignatureFactory.getKeyInfoFactory();
            List x509Content = new ArrayList();
            x509Content.add(x509);
            x509Content.add(x509.getSubjectX500Principal().getName());
            X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
            KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            String srcFile = null;
            Document document = documentBuilderFactory.newDocumentBuilder().
                    parse(new FileInputStream(srcFile));
            DOMSignContext domSignContext = new DOMSignContext(keyPair.getPrivate() , document.getDocumentElement());

            //创建Signature元素
            XMLSignature xmlSignature = xmlSignatureFactory.newXMLSignature(signedInfo , keyInfo);
            xmlSignature.sign(domSignContext);


            //输出处理后的文件
            String dstFile = null;
            OutputStream outputStream = new FileOutputStream(dstFile);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document) , new StreamResult(outputStream));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
