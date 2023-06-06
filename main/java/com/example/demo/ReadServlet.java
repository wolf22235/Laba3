package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;//Проверка совместимости объектов друг с другом с переполнением 2 long

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//Получение данных
        ServletContext sc = getServletContext();//Возвращение контекста
        sc.getRequestDispatcher("/jsp/read.jsp").forward(req, resp);//Возвращение объекта
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Отправление данных
        String pathStr = "C:\\Users\\legen\\IdeaProjects\\laba6\\demo\\src\\main\\webapp\\myData.dat";//Путь
        Path path = Paths.get(pathStr);//Получение
        response.setContentType("application/json");//Установление типа данных json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();//Создаем экземляр json и вызываем его
        String datArrayString="1";//
        if(Files.exists(path)) {//
            datArrayString = Files.readString(path);//
            System.out.println(datArrayString);//
        }
        PrintWriter out = response.getWriter();//
        String jsonArrayString="";//
        if(datArrayString!="") {//
            ArrayList<Games> games = FileHandler.readGamesFromFile();//
            jsonArrayString = gson.toJson(games);//
        }
        out.print(jsonArrayString);//
        out.close();//Закрытие
    }

}
