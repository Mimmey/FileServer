package server;

import server.helpers.Parsers;
import server.helpers.Printer;
import server.helpers.idMap.IdMap;
import server.servercommandengine.servercommandfactory.ServerCommandCreator;
import server.servercommandengine.servercommands.ServerCommand;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        String address = "127.0.0.1";
        int port = 23456;

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Printer printer = new Printer(new BufferedWriter(new OutputStreamWriter(System.out)));
                ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address))
        ) {
            IdMap.initialize();
            printer.printServerStarted();
            File file = new File(Parsers.PATH_SERVER);
            file.mkdir();

            while (true) {
                try (
                    Socket socket = serverSocket.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String request = input.readUTF();

                    ServerCommandCreator commandCreator = new ServerCommandCreator(reader, printer);
                    ServerCommand command = commandCreator.createCommand(request, input, output);

                    if (!command.apply()) {
                        IdMap.serialize();
                        break;
                    }
                } catch (EOFException e) {
                    e.printStackTrace();
                }
            }

        } catch (EOFException e) {
            e.printStackTrace();
        }
    }
}