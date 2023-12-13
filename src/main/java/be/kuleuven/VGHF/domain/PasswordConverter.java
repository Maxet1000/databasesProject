package be.kuleuven.VGHF.domain;

import javax.persistence.*;

public class PasswordConverter implements AttributeConverter<String, String>{
    
    private static final Encryptor encryptor = new Encryptor();

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }

}
