package server.servercommandengine.servercommands;

import server.helpers.*;
import server.helpers.idMap.IdMap;

import java.io.*;
import java.nio.file.Files;

public class ServerGetter extends ServerCommand {

    public ServerGetter(String data, DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(data, input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        boolean isName = Integer.parseInt(Parsers.getFirstWord(data)) == IdOrNameStatuses.NAME.getCode();
        String filename;

        if (isName) {
            filename = Parsers.getStringWithoutFirstWord(data);
        } else {
            int id = Integer.parseInt(Parsers.getStringWithoutFirstWord(data));
            filename = IdMap.getFilenameById(id);
        }

        File file = new File(Parsers.getServerPath(filename));

        if (!file.exists()) {
            output.writeUTF(Integer.toString(HttpStatuses.NOT_FOUND.getCode()));
            return true;
        }

        try {
            output.writeUTF(Integer.toString(ReadReadyStatuses.READY.getCode()));
            byte[] message = Files.readAllBytes(file.toPath());
            output.writeInt(message.length);
            output.write(message);

            int ready = Integer.parseInt(input.readUTF());

            if (ready == client.clientcommandengine.helpers.ReadReadyStatuses.READY.getCode()) {
                int response = HttpStatuses.OK.getCode();
                output.writeInt(response);
            }
        } catch (IOException e) {
            output.writeUTF(ReadReadyStatuses.NOT_READY.toString());
            e.printStackTrace();
        }

        return true;
    }
}
