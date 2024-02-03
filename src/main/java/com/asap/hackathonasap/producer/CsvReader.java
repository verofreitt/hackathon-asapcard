package com.asap.hackathonasap.producer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.asap.hackathonasap.service.TransactionService;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class CsvReader {

	private static TransactionService transactionService;
	
	public static List<String[]> readAndFilterCsv(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        try (CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(path.toFile()))) {
            return csvParser.getRecords().stream()
                    .filter(record -> record.toMap().values().stream().noneMatch(String::isEmpty))
                    .map(CSVRecord::values)
                    .collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        try {
            String inputCsvPath = "Documents\\Hackathon Asapcard\\rabbitmq\\input-data.csv";
            String conciliationCsvPath = "Documents\\Hackathon Asapcard\\rabbitmq\\conciliation-data.csv";

            List<String[]> filteredInputData = readAndFilterCsv(inputCsvPath);
            List<String[]> filteredConciliationData = readAndFilterCsv(conciliationCsvPath);

            RabbitMQProducer.sendMessage(filteredInputData, "input-queue");
            RabbitMQProducer.sendMessage(filteredConciliationData, "conciliation-queue");

            filteredInputData.forEach(data -> transactionService.processCsvTransaction(data));
            filteredConciliationData.forEach(data -> transactionService.processCsvTransaction(data));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
