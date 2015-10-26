package org.gem.utils.csv.jackson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;

public class FasterXMLCustomDateDeSerializer extends JsonDeserializer<Date> {

	public FasterXMLCustomDateDeSerializer() {
		super();
	}

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
	String pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'ZZZ (z)";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		try {
			return sdf.parse(arg0.getText());
		} catch (ParseException e) {
			e.getMessage();
		}
		return null;
	}
}
