package com.ufs.campaign.csv;


import com.google.common.collect.Maps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CSVReaderWriter {

    private final Logger logger = LoggerFactory.getLogger(CSVReaderWriter.class);

    @Autowired
    private Environment env;

    public File writeCsv(List<Object> objectList, String[] fileHeader, String fileName) {
        // 这里显式地配置一下CSV文件的Header，然后设置跳过Header（要不然读的时候会把头也当成一条记录）
        CSVFormat format = CSVFormat.DEFAULT.withHeader(fileHeader).withRecordSeparator("\n");
        // 这个是定位   判断某个字段的数据应该放在records数组中的那个位子
        Map<String, Integer> map = Maps.newHashMap();
        for (int i = 0; i < fileHeader.length; i++) {
            map.put(fileHeader[i], i);
        }
        File csvFile = new File(fileName);
        try {
            // 获取对象的PropertyDescriptor
            Map<String, PropertyDescriptor> descriptorMap = null;
            // 附加
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(env.getProperty("csv.output.folder")+csvFile), "UTF-8"));
            CSVPrinter printer = new CSVPrinter(bw, format);
            for (Object object : objectList) {
                if (CheckUtils.isEmpty(descriptorMap)) {
                    descriptorMap = CsvUtils.getCsvFieldMapPropertyDescriptor(object.getClass());
                }
                String[] records = new String[fileHeader.length];
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    if (descriptorMap.containsKey(stringIntegerEntry.getKey())) {
                        records[map.get(stringIntegerEntry.getKey())] = (String) descriptorMap.get(stringIntegerEntry.getKey()).getReadMethod().invoke(object);
                    }
                }
                printer.printRecord(Arrays.asList(records));
            }
            bw.flush();
            bw.close();
            printer.close();
        } catch (Exception e) {
            logger.error("CsvUtils.writeCsv,写csv文件失败,message:{}", e.getMessage(), e);
        }
        return csvFile;
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
