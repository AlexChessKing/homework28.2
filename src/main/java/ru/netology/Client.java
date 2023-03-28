package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static ru.netology.ServerConfig.HOST;
import static ru.netology.ServerConfig.PORT;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
            // принимаем от сервера "???" или последний сохраненный город
            System.out.println(in.readLine());
            // вводим название города и отправляем на сервер
            out.println(String.format(scanner.nextLine()));
            // принимаем от сервера "OK" или "NOT OK" и выводим на экран
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
