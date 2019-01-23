package com.mercury.SpringBootRESTDemo.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomUserSerializer extends StdSerializer<List<User>>{
	
	public CustomUserSerializer() {
        this(null);
    }

    public CustomUserSerializer(Class<List<User>> t) {
        super(t);
    }
    
    @Override
    public void serialize(
            List<User> users,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<User> studs = new ArrayList<>();
        for (User s : users) {
            s.setAppointments(null);
            studs.add(s);
        }
        generator.writeObject(studs);
    }
}
