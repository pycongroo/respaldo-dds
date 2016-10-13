package converters;

import javax.persistence.AttributeConverter;

import org.uqbar.geodds.Point;

public class PointConverter implements AttributeConverter<Point, String> {

	@Override
	public String convertToDatabaseColumn(Point attribute) {
		String salida = String.format("%s;%s", 
				Double.toString(attribute.latitude()),
				Double.toString(attribute.longitude()));
		return salida;
	}

	@Override
	public Point convertToEntityAttribute(String dbData) {
		Double x,y;
		int index = dbData.indexOf(";");
		String s1,s2;
		s1 = dbData.substring(0, index);
		s2 = dbData.substring(index + 1);
		x = Double.valueOf(s1);
		y = Double.valueOf(s2);
		Point punto = new Point(x, y);
		return punto;
	}

}