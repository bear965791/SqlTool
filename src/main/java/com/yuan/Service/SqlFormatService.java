package com.yuan.Service;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class SqlFormatService {

    public String transfer(String sql){
        StringBuilder sb = new StringBuilder();
        String[] rows = sql.toUpperCase().split("\n");
        String first = String.valueOf(rows[0].trim().charAt(0));
        if(StringUtils.equalsIgnoreCase(first,"S") ){
            sb = select(rows);
        }else if(StringUtils.equalsIgnoreCase(first,"U") ){
            sb = update(rows);
        }else if(StringUtils.equalsIgnoreCase(first,"D") ){

        }else if(StringUtils.equalsIgnoreCase(first,"I")){
            sb = insert(rows);
        }
        return sb.toString();
    }

    private StringBuilder insert(String[] rows){
        //如果沒逗號要加逗號
        StringBuilder sb = new StringBuilder();
        if (rows != null) {
            String tableName = rows[0].substring(rows[0].indexOf("INTO")+4);
            sb.append("new StringBuilder(\" INSERT INTO "+tableName+"\") \n");
            List<String > columns = Arrays.stream(rows)
                                     .skip(1).map(t->{
                                         return t.split("=")[0];
                                     }).collect(Collectors.toList());
            columns.stream()
                    .filter(StringUtils::isNotBlank)
                    .skip(1)
                    .forEach(row ->sb.append("+ \" "+row+" \"\n"));
        }
        return sb;
    }

    private StringBuilder update(String[] rows){
        StringBuilder sb = new StringBuilder();
        if (rows != null) {
            sb.append("new StringBuilder(\" "+rows[0]+" \") \n");

            List<String > columns = Arrays.stream(rows)
                    .skip(1).map(t->{
                        return t.split("=")[0];
                    }).collect(Collectors.toList());

            columns.stream()
                    .filter(StringUtils::isNotBlank)
                    .skip(1)
                    .forEach(row ->sb.append("+ \" "+row+" \"\n"));
        }
        return sb;
    }

    private StringBuilder select(String[] rows) {
        StringBuilder sb = new StringBuilder();
            sb.append("new StringBuilder(\" " + rows[0] + " \")\n");
            if (rows != null) {
                Arrays.stream(rows)
                        .filter(StringUtils::isNotBlank)
                        .skip(1)
                        .forEach(row -> sb.append("               .append(\" " + row + " \")\n"));
            }
            return sb;
    }
}
