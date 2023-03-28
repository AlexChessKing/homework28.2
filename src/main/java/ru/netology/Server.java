package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.netology.ServerConfig.PORT;

public class Server {
    public static void main(String[] args) {
        String city = "";

        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // стартуем сервер один (!) раз
            System.out.println("Сервер запущен");
            while (true) { // в цикле (!) принимаем подключения
                try (Socket socket = serverSocket.accept(); // ждем подключения
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                    ) {
                    // отправляем клиенту "???" или последний сохраненный город
                    out.println(city.equals("") ? "???" : city);
                    // принимаем от клиента название города
                    String answer = in.readLine();
                    // выводим на экран название города
                    System.out.println(answer);
                    // проверяем не введено ли первое слово или начинается ли введенное слово на последнюю букву последнего сохраненного слова
                    if (city.equals("") || answer.toLowerCase().charAt(0) == city.toLowerCase().charAt(city.length() - 1)) {
                        city = answer;
                        out.println("OK");
                    } else {
                        out.println("NOT OK");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
