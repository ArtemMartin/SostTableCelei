package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExample {
    static final String fileName = "List_targets.xlsx";

    public static void generateExcel(List<Cel> data) throws IOException {
        // Создаем книгу и лист
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Список координат целей");

            // Создаем шрифт
            Font headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 18);
            headerFont.setBold(true);
            headerFont.setFontName("Bahnschrift Light");

            // Создаем стиль для заголовка
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // Отступы (инденты) по 2 символа слева и справа
            headerCellStyle.setIndention((short) 2);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);

            // Создаем стиль для данных
            Font dataFont = workbook.createFont();
            dataFont.setFontHeightInPoints((short) 16);
            dataFont.setFontName("Bahnschrift Light");

            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setFont(dataFont);
            dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataCellStyle.setIndention((short) 2);
            dataCellStyle.setBorderTop(BorderStyle.THIN);
            dataCellStyle.setBorderBottom(BorderStyle.THIN);
            dataCellStyle.setBorderLeft(BorderStyle.THIN);
            dataCellStyle.setBorderRight(BorderStyle.THIN);

            // Заголовок
            Row headerRow2 = sheet.createRow(0);
            String[] headers2 = {"  К-во целей: ", String.valueOf(data.size())};
            for (int i = 0; i < headers2.length; i++) {
                Cell cell = headerRow2.createCell(i);
                cell.setCellValue(headers2[i]);
                cell.setCellStyle(headerCellStyle);
            }

            Row headerRow = sheet.createRow(1);
            String[] headers = {" Номер цели", " X", " Y", " h"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }


            // Данные
            int rowNum = 2;
            for (Cel cel : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cel.getName());
                row.createCell(1).setCellValue("   " + cel.getX());
                row.createCell(2).setCellValue("   " + cel.getY());
                row.createCell(3).setCellValue("   " + cel.getH());

                // Применяем стиль ко всем ячейкам строки
                for (int i = 0; i < 4; i++) {
                    row.getCell(i).setCellStyle(dataCellStyle);
                }
            }

            // Автоподбор ширины столбцов (опционально, но красиво)
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            // Запись в файл
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Файл успешно создан: " + fileName);
        }
    }
}
