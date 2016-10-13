package converters;

import javax.persistence.AttributeConverter;

public class DoubleConverter implements AttributeConverter<Double, String> {

	@Override
	public String convertToDatabaseColumn(Double attribute) {
		return Double.toString(attribute);
	}

	@Override
	public Double convertToEntityAttribute(String dbData) {
		return Double.valueOf(dbData);
	}

}
