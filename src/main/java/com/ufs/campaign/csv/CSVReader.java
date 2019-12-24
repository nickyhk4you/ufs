package com.ufs.campaign.csv;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CSVReader {

    private final Logger logger = LoggerFactory.getLogger(CSVReader.class);

    public <T> List<T> readCSV(Class<T> tClass) {
        try {
            File f = ResourceUtils.getFile("classpath:csv/Mini-site_SKU1104_new.csv");
            return this.readCSVWithHeader(f.getAbsolutePath(), tClass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> readCSVWithHeader(String filePath, Class<T> clazz) {
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();

        List<T> resultList = new ArrayList<>();
        try {
            Map<String, PropertyDescriptor> propertyDescriptorMap = CsvUtils.getCsvFieldMapPropertyDescriptor(clazz);
            FileReader fileReader = new FileReader(filePath);
            //创建CSVParser对象
            CSVParser parser = new CSVParser(fileReader, format);
            Map<String, Integer> headerMap = parser.getHeaderMap();
            for (CSVRecord record : parser) {
                T t = clazz.newInstance();
                if (logger.isDebugEnabled()) {
                    logger.debug("DEBUG ->  " + record.toString());
                }
                for (Map.Entry<String, Integer> headerEntry : headerMap.entrySet()) {
                    String cleanEntryKey = specialUnicode(headerEntry.getKey());
                    boolean containsKey = propertyDescriptorMap.keySet().contains(cleanEntryKey);
                    if (containsKey && record.size() > headerEntry.getValue()) {
                        propertyDescriptorMap.get(cleanEntryKey).getWriteMethod().invoke(t, record.get(headerEntry.getValue()));
                    }
                }
                resultList.add(t);
            }
            parser.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;

    }

    public String specialUnicode(String str) {
        if (str.startsWith("\uFEFF")) {
            str = str.replace("\uFEFF", "");
        } else if (str.endsWith("\uFEFF")) {
            str = str.replace("\uFEFF", "");
        }
        return str;
    }

}
