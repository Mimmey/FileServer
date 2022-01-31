package client;

import client.clientcommandengine.clientcommandfactory.ClientCommandCreator;
import client.clientcommandengine.clientcommands.ClientCommand;
import server.helpers.CommandTypes;
import server.helpers.Parsers;
import server.helpers.Printer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;

        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Printer printer = new Printer(new BufferedWriter(new OutputStreamWriter(System.out)));
            Socket socket = new Socket(InetAddress.getByName(address), port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            File file = new File(Parsers.PATH_CLIENT);
            file.mkdir();

            printer.printEnterAction();
            int choice;

            ClientCommandCreator commandCreator = new ClientCommandCreator(reader, printer);
            ClientCommand command = null;

            try {
                choice = Integer.parseInt(reader.readLine());
                command = commandCreator.createCommand(choice, input, output);
            } catch (IllegalArgumentException e) {
                command = commandCreator.createCommand(CommandTypes.EXIT.getIndex(), input, output);
            } finally {
                command.apply();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
