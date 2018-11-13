package com.iisi.opd.data.out.format.impl;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

@Component
public class XmlOutputFormatImpl implements com.iisi.opd.data.out.format.OutputFormat {
    @Override
    public String getOutputData(List<Map<String, Object>> dataList) throws Exception {
        return getOutputData(dataList, false);
    }

    @Override
    public String getOutputData(List<Map<String, Object>> dataList, boolean isAppend) throws Exception {
        Document xml = DocumentHelper.createDocument();
        xml.setXMLEncoding("UTF-8");
        Element data = xml.addElement("data");
        int i = 0;
        if (isAppend) {
        }
        for (i = 1; i < dataList.size(); i++) {
            Element row = data.addElement("row");

            Map<String, Object> dataMap = (Map) dataList.get(i);
            String[] keys = (String[]) dataMap.keySet().toArray(new String[0]);

            for (String key : keys) {
                Element value = row.addElement(key);

                if (dataMap.get(key) != null) {
                    value.addText(dataMap.get(key).toString());
                } else {
                    value.addText("");
                }
            }
        }
        org.dom4j.io.OutputFormat format = org.dom4j.io.OutputFormat.createPrettyPrint();
        StringWriter sw = new StringWriter();
        XMLWriter writer = new XMLWriter(sw, format);
        writer.write(xml);
        writer.close();
        sw.close();

        return sw.toString();
    }
}
