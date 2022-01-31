package server.servercommandengine.servercommands;

import server.helpers.HttpStatuses;
import server.helpers.Parsers;
import server.helpers.Printer;
import server.helpers.idMap.IdMap;

import java.io.*;
import java.util.Optional;

public class ServerDeleter extends ServerCommand {

    public ServerDeleter(String data, DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(data, input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        try {
            boolean isName = Integer.parseInt(Parsers.getFirstWord(data)) == 1;
            Optional<String> filename;
            boolean deletedByName = false;
            boolean deletedByFilename = false;

            if (isName) {
                filename = Optional.of(Parsers.getStringWithoutFirstWord(data));
                System.out.println(filename);
                deletedByName = IdMap.deleteByFilename(filename.get());
                System.out.println("isName = true");
            } else {
                int id = Integer.parseInt(Parsers.getStringWithoutFirstWord(data));
                System.out.println(id);
                filename = IdMap.deleteById(id);
                deletedByFilename = filename.isPresent();
                System.out.println("isName = false");
            }

            if ((deletedByFilename || deletedByName) && (new File(Parsers.getServerPath(filename.get()))).delete()) {
                output.writeUTF(Integer.toString(HttpStatuses.OK.getCode()));
            } else {
                output.writeUTF(Integer.toString(HttpStatuses.NOT_FOUND.getCode()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
