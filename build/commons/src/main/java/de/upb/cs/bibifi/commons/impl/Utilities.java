package de.upb.cs.bibifi.commons.impl;

import com.google.gson.Gson;
import de.upb.cs.bibifi.commons.dto.TransmissionPacket;

import java.io.OutputStream;


public class Utilities {

    //Json Deserializer
    public static TransmissionPacket deserializer (String text){
        Gson gson = new Gson();
        return gson.fromJson(text, TransmissionPacket.class);
    }

    //Json Serializer
    public static String Serializer (TransmissionPacket transmissionPacket){
        Gson gson = new Gson();
        return gson.toJson(transmissionPacket);
    }


}
