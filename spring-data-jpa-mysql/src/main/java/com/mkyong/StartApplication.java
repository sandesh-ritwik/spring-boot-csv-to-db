package com.mkyong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mkyong.BookRepository;
import com.mkyong.Book;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    private BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {

        String csvFilePath = "C:\\Users\\RSTPL081\\Desktop\\books.csv";
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            CSVParser records = CSVParser.parse(lineReader, CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            ArrayList<Book> Books = new ArrayList<Book>();
            for (CSVRecord record : records) {
                Book Book = new Book();
                Book.setId(Integer.parseInt(record.get(0)));
                Book.setName(record.get(1));
                Books.add(Book);
                repository.save(Book);
            }
            System.out.println(Books);
    } catch (Exception ex) {
        ex.printStackTrace();
    } 
}
}