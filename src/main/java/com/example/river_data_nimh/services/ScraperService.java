package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.*;
import com.example.river_data_nimh.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScraperService {

    private static final String URL = "https://storm.cfd.meteo.bg/meteo7/bg/rekiTablitsa";

    private final DunabeBasinRepository dunabeBasinRepository;
    private final BlackSeaBasinRepository blackSeaBasinRepository;
    private final EasternWhiteSeaBasinRepository easternWhiteSeaBasinRepository;
    private final WesternWhiteSeaBasinRepository westernWhiteSeaBasinRepository;

    private final DateRecordsRepository dateRecordsRepository;


    @Scheduled(cron = "0 */14 * ? * *")
    public void scrapeAndSave() throws IOException {

        // Fetch the HTML document using Jsoup
        Document doc = Jsoup.connect(URL).get();


        // Get all the table elements from the HTML document
        Elements tables = doc.select("table");

        // Get the current date element
        Element dateElement = doc.select("h2:has(br)").first();

        //Parse dateElement to LocalDate
        LocalDate date = LocalDate.parse(dateElement.text().substring(0, 10), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        DateRecords latestRecord = dateRecordsRepository.findTopByOrderByDateDesc();
        if (latestRecord == null || !latestRecord.getDate().equals(date)) {
            DateRecords dateRecords = new DateRecords();
            dateRecords.setDate(date);
            dateRecordsRepository.save(dateRecords);


            // Loop through each table element
            for (int i = 0; i < tables.size() - 1; i++) {

                Element table = tables.get(i);

                // Get the title of the table
                String title = table.previousElementSibling().text();

                // Get the table rows from the table element
                Elements rows = table.select("tr");

                // Loop through each row in the table
                for (Element row : rows) {
                    // Get the cells in the row
                    Elements cells = row.select("td");

                    //Skip the header row
                    if (cells.isEmpty()) {
                        continue;
                    }

                    // Extract the data from the cells
                    String numSt = cells.get(0).text();
                    String name = cells.get(1).text();
                    String station = cells.get(2).text();
                    String minQ = cells.get(3).text().replace(",", ".").replace(" ", "");
                    String avrQ = cells.get(4).text().replace(",", ".").replace(" ", "");
                    String maxQ = cells.get(5).text().replace(",", ".").replace(" ", "");
                    String h = cells.get(6).text().replace(",", ".");
                    String q = cells.get(7).text().replace(",", ".").replace(" ", "");
                    String dH = cells.get(8).text().replace(",", ".");

                    // Determine which entity to save the data to based on the title of the table
                    switch (title) {
                        case "Дунавски басейн" -> {
                            DunabeBasin dunabeBasin = DunabeBasin.builder()
                                    .numSt(Integer.valueOf(numSt))
                                    .name(name)
                                    .station(station)
                                    .minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                    .avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                    .maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                    .h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                    .q("n.a.".equals(q) ? null : Double.valueOf(q))
                                    .dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                    .ld(date)
                                    .build();
                            dunabeBasinRepository.save(dunabeBasin);
                            log.info("River data for station " + station + " is saved to dunabeBasin's table.");

                        }
                        case "Черноморски басейн" -> {
                            BlackSeaBasin blackSeaBasin = BlackSeaBasin.builder()
                                    .numSt(Integer.valueOf(numSt))
                                    .name(name)
                                    .station(station)
                                    .minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                    .avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                    .maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                    .h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                    .q("n.a.".equals(q) ? null : Double.valueOf(q))
                                    .dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                    .ld(date)
                                    .build();
                            blackSeaBasinRepository.save(blackSeaBasin);
                            log.info("River data for station " + station + " is saved to blackSeaBasin's table.");

                        }
                        case "Източнобеломорски басейн" -> {
                            EasternWhiteSeaBasin easternWhiteSeaBasin = EasternWhiteSeaBasin.builder()
                                    .numSt(Integer.valueOf(numSt))
                                    .name(name)
                                    .station(station)
                                    .minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                    .avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                    .maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                    .h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                    .q("n.a.".equals(q) ? null : Double.valueOf(q))
                                    .dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                    .ld(date)
                                    .build();
                            easternWhiteSeaBasinRepository.save(easternWhiteSeaBasin);
                            log.info("River data for station " + station + " is saved to easternWhiteSeaBasin's table.");
                        }
                        case "Западнобеломорски басейн" -> {
                            WesternWhiteSeaBasin westernWhiteSeaBasin = WesternWhiteSeaBasin.builder()
                                    .numSt(Integer.valueOf(numSt))
                                    .name(name)
                                    .station(station)
                                    .minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                    .avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                    .maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                    .h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                    .q("n.a.".equals(q) ? null : Double.valueOf(q))
                                    .dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                    .ld(date)
                                    .build();
                            westernWhiteSeaBasinRepository.save(westernWhiteSeaBasin);
                            log.info("River data for station " + station + " is saved to westernWhiteSeaBasin's table.");
                        }
                    }
                }
            }
        }else {
            log.info("River data is already up to date");
        }
    }
}

