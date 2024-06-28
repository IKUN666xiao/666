package com.example.demo.converter;



import com.example.demo.enums.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.getDbValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.getGenderByValue(dbData).orElse(Gender.BOY);
    }
}