package com.example.geektrust;

import com.example.geektrust.controller.ExpensesController;
import com.example.geektrust.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(@NotNull String[] args) {
        try {
            //"java-maven-starter-kit/sample_input/input1.txt"
            String filePath = args[0];
            UserRepository repository = new UserRepository();
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                ExpensesController expensesController = new ExpensesController();
                expensesController.run(sc.nextLine(), repository);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
