package client.clientcommandengine.clientcommands;
import client.clientcommandengine.helpers.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.ServerException;

public class ClientDeleter extends ClientCommand {

    public ClientDeleter(DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        StringBuilder request = new StringBuilder("DELETE ");

        printer.printDeleteByNameOrById();
        int type = Integer.parseInt(reader.readLine());
        String subject;

        if (type == IdOrNameStatuses.NAME.getCode()) {
            printer.printEnterFilename();
        } else {
            printer.printEnterId();
        }

        subject = reader.readLine();

        request.append(type).append(" ").append(subject);

        output.writeUTF(request.toString());
        printer.printRequestSent();
        String response = input.readUTF();

        HttpStatuses status = HttpStatuses.NOT_FOUND;

        try {
            int statusCode = Integer.parseInt(StringAndPathWorkers.getFirstWord(response));
            status = HttpStatuses.findByCode(statusCode);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        switch (status) {
            case OK:
                printer.printResponseFileDeleted();
                break;
            case NOT_FOUND:
                printer.printResponseFileNotFound();
                break;
            default:
                throw new ServerException("");
        }

        return true;
    }
}
