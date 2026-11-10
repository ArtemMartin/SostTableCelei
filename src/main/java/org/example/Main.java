package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Cel> listCel = new ArrayList<>();
        System.out.print("Ввести название файла: ");
        String strFile = scanner.nextLine();
        if (strFile != null) {
            ReaderFile readerFile = new ReaderFile(strFile);
            listCel = readerFile.getListCels();
        }
        //создаём файл excel
        if (!listCel.isEmpty()) {
            ExcelExample.generateExcel(listCel);
        }
    }
}