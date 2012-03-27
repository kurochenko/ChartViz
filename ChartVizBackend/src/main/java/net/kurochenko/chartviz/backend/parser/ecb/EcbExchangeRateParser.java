package net.kurochenko.chartviz.backend.parser.ecb;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Component
public class EcbExchangeRateParser implements ExchangeRateParser{

    public static Logger logger = Logger.getLogger(EcbExchangeRateParser.class);

    /** Node name which contains currency rate or validity date */
    public static final String XML_DATA_NODE_NAME = "Cube";

    /** Node attribute name which contains currency rate validity date */
    public static final String XML_TIME_ATTR_NAME = "time";

    /** Node attribute name which contains currency code */
    public static final String XML_CURRENCY_ATTR_NAME = "currency";

    /** Node attribute name which contains currency rate */
    public static final String XML_RATE_ATTR_NAME = "rate";

    /** Currency rate validity date format */
    public static final String VALIDITY_DATE_FORMAT = "y-M-d";

    /**
     * URL path to XML file with currency rates
     */
    private static final String RATES_XML = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";


    /**
     * Parses XML file with actual currency rates. XML file is loaded directly from URL defined in configuration
     * property file
     * @return parsed DTO object or {@code null} when any parse or IO errors occur
     */
    public ExchangeRateDTO parse()  {

        ExchangeRateDTO result = null;
        Document doc = getDocument();
        Map<String, BigDecimal> rates = new HashMap<String, BigDecimal>();
        boolean error = false;

        if (doc != null) {
            result = new ExchangeRateDTO();
            Element rootNode = doc.getDocumentElement();
            NodeList dataNodeList = rootNode.getElementsByTagName(XML_DATA_NODE_NAME);

            for (int i = 0; i < dataNodeList.getLength(); i++) {
                Node dataNode = dataNodeList.item(i);

                if (dataNode.hasAttributes()) {
                    if (dataNode.getAttributes().getNamedItem(XML_TIME_ATTR_NAME) != null) {
                        result.setDay(parseValidityDate(dataNode.getAttributes().getNamedItem(XML_TIME_ATTR_NAME).getNodeValue()));
                        if (result.getDay() == null) {
                            error = true;
                        }
                    } else {
                        String key = dataNode.getAttributes().getNamedItem(XML_CURRENCY_ATTR_NAME).getNodeValue();
                        BigDecimal value = new BigDecimal(dataNode.getAttributes().getNamedItem(XML_RATE_ATTR_NAME).getNodeValue());
                        rates.put(key, value);
                    }
                }
            }
            result.setRates(rates);
        }

        return error ? null : result;
    }

    /**
     * Parses string date to {@code java.util.Date}
     * @param date string date representation
     * @return date or {@code null} when parse error occurs
     */
    private Date parseValidityDate(String date) {

        DateFormat dateFormat = new SimpleDateFormat(VALIDITY_DATE_FORMAT);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            logger.error("Failed to parse currency rate validity date", e);
        }

        return null;
    }

    /**
     * Loads XML file with actual currency rates from URL saved in configuration file
     * and parses it into {@code org.w3c.dom.Document}
     * @return parsed document or {@code null} when any error occurs
     */
    private Document getDocument() {

        Document document = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(RATES_XML);
            inputStream = url.openStream();

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);

            DocumentBuilder builder = domFactory.newDocumentBuilder();
            document = builder.parse(inputStream);
        } catch (MalformedURLException e) {
            logger.error("Failed to retrieve XML file with currency rates.", e);
        } catch (IOException e) {
            logger.error("Failed to open XML file with currency rates.", e);
        } catch (SAXException e) {
            logger.error("Failed to parse XML file with currency rates.", e);
        } catch (ParserConfigurationException e) {
            logger.error("Failed to configure currency rates XML file parser.", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("Failed to close input stream fo currency rates XML file", e);
                }
            }
        }

        return document;
    }
}
