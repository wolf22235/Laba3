package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileHandler {
    private static String pathStr = "C:\\Users\\legen\\IdeaProjects\\laba6\\demo\\src\\main\\webapp\\myData.dat";
    private static Path path = Paths.get(pathStr);
    public static void writeGamesToFile(Games games) throws IOException {
        String gamesString = "";
        gamesString+=games.getGame()+","; //Присваем получаемый параметр в myData через запятую
        gamesString+=games.getDevelopers()+",";
        gamesString+=games.getGenre()+",";
        gamesString+=games.getYear()+",";
        gamesString+=games.getMetacritic()+"\n";
        if(!Files.exists(path)) { //Если файла не существует
            Files.createFile(path); //Создаем. Проверка
        }
        Files.write(path, gamesString.getBytes(), StandardOpenOption.APPEND); //Запись в файл
    }
    public static ArrayList<Games> readGamesFromFile() throws IOException { //Объявление списка
        if(Files.exists(path)) { //Если файл существует
            BufferedReader reader = new BufferedReader(new FileReader(pathStr)); //Считывание текста из потока ввода
            String data = reader.readLine(); //Передача текста в data
            ArrayList<Games> games = new ArrayList<>(); //Создание списка
            while(data != null) { //Если передача не осуществилась
                String[] parameters = data.split(","); //Передаем параметры через запятые
                Games tempGames = new Games(parameters[0],parameters[1],parameters[2],Float.parseFloat(parameters[3]),Float.parseFloat(parameters[4]));
                games.add(tempGames);//Добавляем
                data = reader.readLine();//Считываем
            }
            reader.close();//Закрываем буфер
            return games;//Возвращаем параметр
        }
        return null;
    }
}
