package com.random.helperClass;

import com.random.entity.Student;
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
    enum CellsEnum {
        ID,
        NAME,
        SURNAME,
    }

    //TODO refactor/replace switch statement (done!)
    public static List<Student> excelToStudent() {
        final String SHEET = "Students";
        try (FileInputStream fis = new FileInputStream(
                new File("src/main/resources/templates/students.xlsx").getAbsolutePath());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Student> students = new ArrayList<>();

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (currentRow.getRowNum() == 0) {
                    continue;
                }

                Student student = new Student();
                //adding fields to Student
                student.setB_teamId((int) currentRow.getCell(CellsEnum.ID.ordinal()).getNumericCellValue());
                student.setC_name(currentRow.getCell(CellsEnum.NAME.ordinal()).getStringCellValue());
                student.setD_surname(currentRow.getCell(CellsEnum.SURNAME.ordinal()).getStringCellValue());

                students.add(student);
            }
            return students;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
