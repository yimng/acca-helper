package com.thinkgem.jeesite.freetek.util.JsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * decimal类型序列化工具,自动在小数点后面补00
 * 使用方法:在实体的get方法上直接加@JsonSerialize(using = CustomBigDecimalSerialize.class)
 * @author Young
 * @version 2016/9/1
 */
public class CustomBigDecimalSerialize extends JsonSerializer<BigDecimal>{

    private DecimalFormat df = new DecimalFormat("##.00");

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(df.format(bigDecimal));
    }
}
