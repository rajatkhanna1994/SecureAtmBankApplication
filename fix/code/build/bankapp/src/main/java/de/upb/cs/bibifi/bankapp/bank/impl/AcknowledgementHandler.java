package de.upb.cs.bibifi.bankapp.bank.impl;

import com.google.gson.Gson;
import de.upb.cs.bibifi.commons.IEncryption;
import de.upb.cs.bibifi.commons.dto.Acknowledgement;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class AcknowledgementHandler implements Callable {

    private final DataInputStream dataInputStream;
    private final List<String> processedList;
    private final String requireResponseId;
    private IEncryption encryption;

    public AcknowledgementHandler(DataInputStream dataInputStream, IEncryption encryption, List<String> processedList, String packetId) {
        this.dataInputStream = dataInputStream;
        this.encryption = encryption;
        this.processedList = processedList;
        this.requireResponseId = packetId;
    }


    @Override
    public String call() throws IOException {
        String receivedAckId = null;
        String receiveMessage = dataInputStream.readUTF();
        String decryptMsg = encryption.decryptMessage(receiveMessage);
        //Take decrypted msg and make pkt
        if (decryptMsg != null) {
            Gson gson = new Gson();
            String ackMessage = decryptMsg.toString();
            Acknowledgement ack = gson.fromJson(ackMessage, Acknowledgement.class);

            if (ack.getAckId() == null || ack.getAckId().isEmpty() ||
                    processedList.contains(ack.getAckId()) || !ack.getResponseId().equals(requireResponseId)) {
                throw new IOException("Duplicate or wrong ack was sent");
            }
            receivedAckId = ack.getAckId();
        }
        return receivedAckId;
    }
}
