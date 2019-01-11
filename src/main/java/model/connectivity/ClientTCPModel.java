package model.connectivity;

import model.SurveyModel;

import java.io.*;
import java.net.*;

public class ClientTCPModel {

    public ClientTCPModel() {
        String address = "192.168.1.10";
        int port = 12367;

        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            socket.setTcpNoDelay(true);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.println(true);
            out.flush();

            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            SurveyModel sm = (SurveyModel)ois.readObject();
            System.out.println(sm.getName());

            socket.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
