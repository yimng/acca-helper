package com.thinkgem.jeesite.freetek.util.JsonSerializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间反序列化工具,直接将yyyy-MM-dd类型的转为时间类型的数据
 * 使用方法:在实体的set方法上直接加@JsonSerialize(using = CustomDateSerialize.class)
 * @author Young
 * @version 2016/9/1
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {

    //定义时间格式
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            return sdf.parse(jsonParser.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
