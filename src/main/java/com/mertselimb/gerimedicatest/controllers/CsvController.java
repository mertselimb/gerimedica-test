package com.mertselimb.gerimedicatest.controllers;

import com.mertselimb.gerimedicatest.models.Csv;
import com.mertselimb.gerimedicatest.repositories.CsvRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CsvRepository csvRepository;

    @GetMapping
    public List<Csv> getAllCsv() {
        return csvRepository.findAll();
    }

    @GetMapping("/{code}")
    public Optional<Csv> getCsvById(@PathVariable String code) {
        return csvRepository.findByCode(code);
    }

    @PostMapping
    public Csv createCsv(@RequestBody Csv csv) {
        return csvRepository.save(csv);
    }

    @DeleteMapping("/{code}")
    public void deleteCsv(@PathVariable String code) {
        csvRepository.deleteById(code);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllCsv() {
        csvRepository.deleteAll();
        return ResponseEntity.ok("All CSV data deleted successfully");
    }

    @PostMapping("/import-csv")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile csvFile) throws IOException {
        if (csvFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            Iterable<CSVRecord> records = parser.getRecords();

            for (CSVRecord record : records) {
                Csv csv = new Csv();
                csv.setCode(record.get("code"));
                csv.setSource(record.get("source"));
                csv.setCodeListCode(record.get("codeListCode"));
                csv.setDisplayValue(record.get("displayValue"));
                csv.setLongDescription(record.get("longDescription"));
                String fromDate = record.get("fromDate");
                if (isTruthy(fromDate)) {
                    csv.setFromDate(formatter.parse(fromDate));
                }
                String toDate = record.get("toDate");
                if (isTruthy(toDate)) {
                    csv.setToDate(formatter.parse(toDate));
                }
                String sortingPriority = record.get("sortingPriority");
                if (isTruthy(sortingPriority)) {
                    csv.setSortingPriority(Integer.parseInt(sortingPriority));
                }

                csvRepository.save(csv);
            }

            return ResponseEntity.ok("CSV data imported successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception appropriately
            return ResponseEntity.internalServerError().body("Error importing CSV data: " + e.getMessage());
        }
    }

    public static boolean isTruthy(Object value) {
        return value != null && !(value instanceof String && ((String) value).isEmpty());
    }
}