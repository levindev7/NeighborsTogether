package study.db;

import study.model.Color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// Реализуем интерфейс с дженериками для преобразования Color в String для db и обратно с помощью методов convert...
@Converter
public class ColorConverterExample implements AttributeConverter<Color, String> {
    @Override
    public String convertToDatabaseColumn(Color atribute) {
        return atribute.getR() + "," + atribute.getG() + "," + atribute.getB();
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            throw new NullPointerException("Failed to convert null to Color");
        }
        String[] components = dbData.split(",");
        if (components.length != 3) {
            throw new IllegalArgumentException("Failed to convert " + dbData + " to Color");
        }
        return new Color(
                Integer.parseInt(components[0]),
                Integer.parseInt(components[1]),
                Integer.parseInt(components[2])

        );
    }
}
