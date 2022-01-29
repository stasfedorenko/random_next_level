package com.random.helperClass;

import com.random.entity.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    //name of sheet in Excel file
    static String SHEET = "Students";

    //TODO зарефакторить
    public static List<Student> excelToStudent() {
        try {
            String path = new File("src/main/resources/templates/students.xlsx").getAbsolutePath();
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Student> students = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Student student = new Student();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            student.setB_teamId((int) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            student.setC_name(currentCell.getStringCellValue());
                            break;

                        case 2:
                            student.setD_surname(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                students.add(student);
            }

            workbook.close();

            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
