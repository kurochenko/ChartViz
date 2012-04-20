package net.kurochenko.chartviz.backend.parser.ecb;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

/**
 * @author Andrej Kuročenko <andrej@kurochenko.net>
 */
public class EcbNamespaceContext implements NamespaceContext {

    public static final String NS_ECB = "ecb";
    public static final String NS_XML = "xml";
    public static final String NS_GMS = "pre";
    public static final String GESMES_NAMESPACE_URI = "http://www.gesmes.org/xml/2002-08-01";
    public static final String ECB_NAMESPACE_URI = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref";

    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Null prefix");
        }

        if      (NS_GMS.equals(prefix)) return GESMES_NAMESPACE_URI;
        else if (NS_ECB.equals(prefix)) return ECB_NAMESPACE_URI;
        else if (NS_XML.equals(prefix)) return XMLConstants.XML_NS_URI;
        return XMLConstants.NULL_NS_URI;
    }

    public String getPrefix(String namespaceURI) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterator getPrefixes(String namespaceURI) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
