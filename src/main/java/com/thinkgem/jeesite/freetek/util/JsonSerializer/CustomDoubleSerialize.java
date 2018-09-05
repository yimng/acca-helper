package com.thinkgem.jeesite.freetek.util.JsonSerializer;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 用户将double类型的数据格式化成小数点后两位的字符串数据：如输出为“900.00”.
 * @author lihaifeng747
 *
 */
public class CustomDoubleSerialize extends JsonSerializer<Double>{

	private DecimalFormat df = new DecimalFormat("##"); 
	
	@Override  
    public void serialize(Double value, JsonGenerator jgen,  
            SerializerProvider provider) throws IOException,  
            JsonProcessingException {  
  
        jgen.writeString(df.format(value));  
    }  
}
