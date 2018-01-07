package org.cronkeeper.server;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.Properties;
import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import org.w3c.dom.*;

public class CronkeeperConf {

    private static final Logger LOG =
            LoggerFactory.getLogger(CronkeeperConf.class);

    private static String defaultResource = "cronkeeper-default.xml";
    private static String confResource = "cronkeeper-site.xml";


    private ClassLoader classLoader;
    {
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = CronkeeperConf.class.getClassLoader();
        }
    }

    private Properties properties;

    public CronkeeperConf() {
        // 加载cronkeeper-default.xml
        loadDefaults();

        // 加载cronkeeper-site.xml
        loadConf();
    }

    /**
     * load properties from cronkeeper-default.xml
     */
    private void loadDefaults() {
        loadResource(defaultResource);
    }

    /**
     * load properties from cronkeeper-site.xml
     */
    private void loadConf() {
        loadResource(confResource);
    }


    /**
     * configuration file format should be like
     * <configuration>
     *     <property>
     *         <name></name>
     *         <value></value>
     *     </property>
     *     ...
     *     <property>...</property>
     * </configuration>
     *
     * @param resourceName
     */
    private void loadResource(String resourceName) {

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            //ignore all comments inside the xml file
            docBuilderFactory.setIgnoringComments(true);

            //allow includes in the xml file
            docBuilderFactory.setNamespaceAware(true);
            try {
                docBuilderFactory.setXIncludeAware(true);
            } catch (UnsupportedOperationException e) {
                LOG.error("Failed to set setXIncludeAware(true) for parser " + docBuilderFactory + ":" + e, e);
            }

            DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();

            URL url = classLoader.getResource(resourceName);

            assert url != null;
            Document doc = builder.parse(url.openStream(), url.toString());

            Element root =  doc.getDocumentElement();
            if (!root.getTagName().equals("configuration")) {
                LOG.error("%s should start with <configuration> tag", resourceName);
                throw new Exception("Error while parsing " + resourceName + ", please " +
                        "check the format of the resource file");
            }

            NodeList nodes = root.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (! (node instanceof Element)) continue;
                Element el = (Element) node;

                if (! el.getTagName().equals("property")) {
                    LOG.warn("%s <configuration> should be consist of <property>", resourceName);
                }

                NodeList fields = node.getChildNodes();
                String name = null, value = null;

                for (int j = 0; j < fields.getLength(); j++) {
                    Node fieldNode = fields.item(j);
                    if (! (fieldNode instanceof Element)) continue;

                    Element fieldEl = (Element) fieldNode;
                    String text = fieldEl.getTextContent().trim();

                    // only name and value tags will be accepted.
                    if (fieldEl.getTagName().equals("name")) {
                        name = text;
                    } else if (fieldEl.getTagName().equals("value")) {
                        value = text;
                    }
                    if (name != null && value != null) {
                        properties.setProperty(name, value);
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            // throw runtime exception instead.
            throw new RuntimeException(ex);
        }
    }

    /**
     * make sure the property is no empty
     * @return properties
     */
    private Properties getProperties() {
        if (properties == null) {
            loadDefaults();
            loadConf();
        }
        return properties;
    }

    /**
     * Get value by key name.
     * @param name - key name
     * @return value
     */
    public String get(String name) {
        return getProperties().getProperty(name);
    }

    /**
     * Get value by key name with default value.
     * @param name - key name
     * @param defaultValue - default value
     * @return value
     */
    public String get(String name, String defaultValue) {
        return getProperties().getProperty(name, defaultValue);
    }
}
