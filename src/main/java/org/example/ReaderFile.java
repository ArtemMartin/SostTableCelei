package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReaderFile {
    List<Cel> listCels = new ArrayList<>();
    String file;

    public ReaderFile(String file) {
        this.file = file;
    }

    public List<Cel> getListCels() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            String[] mas;
            while ((line = reader.readLine()) != null) {
                mas = line.split(",");
                listCels.add(new Cel(mas[0], mas[1], mas[2], mas[3]));
            }
        } catch (IOException e) {
            Logger.getLogger(Class.class.getName()).info(e.getMessage() + " : " + Arrays.toString(e.getStackTrace()));
        }
        //проверяем совпадения
        proverkaPoNomweram();
        return listCels;
    }

    public void proverkaPoNomweram() {
        List<Cel> listRepetitions = new ArrayList<>();
        if (!listCels.isEmpty()) {
            String numberForVerification;
            for (int i = 0; i < listCels.size(); i++) {
                numberForVerification = listCels.get(i).getName();
                for (int j = i + 1; j < listCels.size(); j++) {
                    if (numberForVerification.equals(listCels.get(j).getName())) {
                        listRepetitions.add(listCels.get(j));
                    }
                }
            }
        }
        if (!listRepetitions.isEmpty()) {
            System.out.println("Найдены совпадения по номерам:");
            for (Cel repetition : listRepetitions) {
                System.out.println(repetition);
            }
            System.out.println("-------------------------------------------");
        }
    }
}
