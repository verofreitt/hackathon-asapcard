package com.asap.hackathonasap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.opencsv.exceptions.CsvException;

@SpringBootApplication
public class FileObservers implements CommandLineRunner {

	private void insertCSV(Path directoryPath, String csvFile, Path directoryPathProcessed, String queueName)
			throws JSONException, IOException, TimeoutException, InterruptedException {
		Thread.sleep(1000);
		String lineData;
		BufferedReader read = new BufferedReader(new FileReader(directoryPath.toString() + "\\" + csvFile));
		RabbitMQSender sender = new RabbitMQSender(queueName);

		while ((lineData = read.readLine()) != null) {
			String[] individualRecordFromCcsv = lineData.split(";");
			JSONObject jsonobj = new JSONObject();

			if(queueName.equals("transacoes")) {
	            jsonobj.put("id", individualRecordFromCcsv[0]);
	            jsonobj.put("date", individualRecordFromCcsv[1]);
	            jsonobj.put("document", individualRecordFromCcsv[2]);
	            jsonobj.put("name", individualRecordFromCcsv[3]);
	            jsonobj.put("age", individualRecordFromCcsv[4]);
	            jsonobj.put("value", individualRecordFromCcsv[5]);
	            jsonobj.put("num", individualRecordFromCcsv[6]);
			}else if(queueName.equals("conciliacoes")) {
				jsonobj.put("id", individualRecordFromCcsv[0]);
				jsonobj.put("date", individualRecordFromCcsv[1]);
				jsonobj.put("document", individualRecordFromCcsv[2]);
				jsonobj.put("status", individualRecordFromCcsv[3]);
			}
			

			sender.enviar(jsonobj.toString());
		}
		read.close();

		Path sourcePath = Paths.get(directoryPath.toString() + "\\" + csvFile.toString());
		Path targetPath = Paths.get(directoryPathProcessed.toString() + "\\" + csvFile.toString());

		try {
			Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("Arquivo movido com sucesso de " + sourcePath + " para " + targetPath);
		} catch (IOException e) {
			System.err.println("Erro ao mover o arquivo: " + e.getMessage());
		}
	}

	private void observeDirectory(Path directoryPath, Path directoryPathProcessed, String queueName)
			throws JSONException, TimeoutException {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			while (true) {
				WatchKey key = watchService.take();

				for (WatchEvent<?> event : key.pollEvents()) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						Path filePath = (Path) event.context();
						System.out.println("Novo arquivo criado: " + filePath);

						if (filePath.toString().endsWith(".csv")) {
							insertCSV(directoryPath, filePath.toFile().toString(), directoryPathProcessed, queueName);
						}
					}
				}

				boolean valid = key.reset();
				if (!valid) {
					System.out.println("Chave de observação não é mais válida. Saindo do loop.");
					break;
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void readExisting(Path directoryPath, Path directoryPathProcessed, String queue) throws JSONException, IOException, TimeoutException, InterruptedException {
		java.io.File[] existingCsvFiles = directoryPath.toFile()
				.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

		for (java.io.File csvFile : existingCsvFiles) {
			if (csvFile.isFile()) {
				System.out.println("Processando arquivo existente: " + csvFile.getName());

				insertCSV(directoryPath, csvFile.getName(), directoryPathProcessed, queue);
			}
		}
	}

	@Override
	public void run(String... args) throws IOException, CsvException, JSONException, TimeoutException, InterruptedException {
		Path transacoesDirectory = Path.of("C:\\Users\\verof\\Desktop\\Teste RabbitMQ e Banco\\arquivos\\transacoes");
		Path transacoesProcessedDirectory = Path
				.of("C:\\Users\\verof\\Desktop\\Teste RabbitMQ e Banco\\arquivos\\transacoes_processado");

		Path conciliacoesDirectory = Path
				.of("C:\\Users\\verof\\Desktop\\Teste RabbitMQ e Banco\\arquivos\\conciliacoes");
		Path conciliacoesProcessedDirectory = Path
				.of("C:\\Users\\verof\\Desktop\\Teste RabbitMQ e Banco\\arquivos\\conciliacoes_processado");

		//verifica arquivos existentes
		readExisting(transacoesDirectory, transacoesProcessedDirectory, "transacoes");
		readExisting(conciliacoesDirectory, conciliacoesProcessedDirectory, "conciliacoes");
		
		// Cria threads separadas para cada observador
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

		// Agendando observadores para serem executados em intervalos regulares
		executorService.scheduleAtFixedRate(() -> {
			try {
				observeDirectory(transacoesDirectory, transacoesProcessedDirectory, "transacoes");
			} catch (JSONException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, 0, 1, TimeUnit.SECONDS);

		executorService.scheduleAtFixedRate(() -> {
			try {
				observeDirectory(conciliacoesDirectory, conciliacoesProcessedDirectory, "conciliacoes");
			} catch (JSONException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
}
