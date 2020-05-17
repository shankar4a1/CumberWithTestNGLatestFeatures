package com.demo.framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

public class JSONUtility {
	  /***************Path to store all excel files and generated json files*****************/
    private static String excelPath = "src/main/resources/data/";
    private static String jsonPath = "src/main/resources/data/JSONs/";
    private static String JSONPath = "src/main/resources/data/JSONs/";
    private static String fcoJSONPath = "src/main/resources/data/fco/";

    public static void main(String []args) throws Exception {
        JSONUtility obj = new JSONUtility();
        obj.convertExcelToJsonFile("DataSheet.xlsx");
   }

    /**
     * This converts Payment_Details excel file into single JSON file
     * @param fileName: Excel file name having payment_details
     * @throws IOException
     */
    public void convertCutOffExcelToJson(String fileName) throws IOException {
        FileInputStream fi = new FileInputStream(new File(excelPath+fileName));
        Workbook wb = new XSSFWorkbook(fi);
        Sheet sheet = wb.getSheet("TestData");
        int number_of_rows = sheet.getPhysicalNumberOfRows();

        ArrayList<String> headerList = getHeaderList(sheet.getRow(1));

        JSONObject parentObject = new JSONObject();
        for(int rowNum = 2; rowNum < number_of_rows; rowNum++)
        {
            //parentObject = new JSONObject();
            Row row = sheet.getRow(rowNum);
            int num_of_Cells = row.getPhysicalNumberOfCells();
            JSONObject childObject = new JSONObject();
            for(int cellNum=1; cellNum<num_of_Cells; cellNum++)
            {
                Cell currentCell = row.getCell(cellNum);

                switch(currentCell.getCellType())
                {
                    case STRING:
                        childObject.put(headerList.get(cellNum), currentCell.getStringCellValue());
                        break;
                    case NUMERIC:
                        childObject.put(headerList.get(cellNum), currentCell.getNumericCellValue());
                        break;
                    default:
                        childObject.put(headerList.get(cellNum), "");
                }
            }
            parentObject.put((row.getCell(0)).getStringCellValue(),childObject);
        }

//        System.out.println(parentObject.toString());
        createJSONFile(parentObject,"PaymentDetails");
    }

    protected ArrayList<String> getHeaderList(Row row) {
        ArrayList<String> headerList = new ArrayList<String>();

        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            headerList.add(currentCell.getStringCellValue());
        }

        return headerList;
    }

    /**
     * Converts each row present in given excel file into individual json file
     * @param fileName
     * @throws Exception
     */
    public void convertExcelToJsonFile(String fileName) throws Exception {

        FileInputStream fi = new FileInputStream(new File(excelPath+fileName));
        Workbook wb = new XSSFWorkbook(fi);
        Sheet sheet = wb.getSheet("TestData");
        int number_of_rows = sheet.getPhysicalNumberOfRows();

        ArrayList<String> headerList = getHeaderList(sheet.getRow(1));

        JSONObject jsonObject = null;
        for(int rowNum = 2; rowNum < number_of_rows; rowNum++)
        {
            jsonObject = new JSONObject();
            Row row = sheet.getRow(rowNum);
            int num_of_Cells = row.getPhysicalNumberOfCells();
//            System.out.println("Number of cells: " + num_of_Cells);
            for(int cellNum=1; cellNum<num_of_Cells; cellNum++)
            {
                Cell currentCell = row.getCell(cellNum);

                switch(currentCell.getCellType())
                {
                    case STRING:
                        jsonObject.put(headerList.get(cellNum), currentCell.getStringCellValue());
                        break;
                    case NUMERIC:
                        jsonObject.put(headerList.get(cellNum), currentCell.getNumericCellValue());
                        break;
                    default:
                        jsonObject.put(headerList.get(cellNum), "");
                }
            }
            createJSONFile(jsonObject, (row.getCell(0)).getStringCellValue());
//            System.out.println(rowNum);
            jsonObject=null;
        }
    }

    /**
     * Creates json file for given jsonObject
     * @param jsonObject
     * @param filename: created json file will be stored with this name
     * @throws IOException
     */
    protected void createJSONFile(JSONObject jsonObject, String filename) throws IOException {
        FileWriter file = null;
        try {
            file = new FileWriter(JSONPath+filename+".json");
            file.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            file.flush();
            file.close();
            file = null;
        }

    }

    /**
     * Converts json file into map
     * @param jsonFileName: json file name which needs to be converted into map
     * @return Map
     */
    public Map<String, Object> convertJSONtoMAP(String jsonFileName)
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(new File(JSONPath+jsonFileName+".json"), new TypeReference<Map<String, Object>>() {
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Add/update key-value pair in existing json file
     * @param filename: json file name
     * @param key
     * @param value
     * @throws IOException
     */
    public void amendJsonFile(String filename, String key, String value) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(jsonPath+filename+".json"));

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonData);
        //If key already present, it will update the value or else it will create the new one
        ((ObjectNode) rootNode).put(key, value);

        objectMapper.writeValue(new File(jsonPath+filename+".json"), rootNode);
    }

    public void mapToJSONFile(String paymentRefNumber, String fileName) throws IOException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Payment_Ref_Number", paymentRefNumber);
        FileWriter file = null;
        try {
            file = new FileWriter(fcoJSONPath+fileName+".json");
            file.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            file.flush();
            file.close();
            file = null;
        }

    }

}