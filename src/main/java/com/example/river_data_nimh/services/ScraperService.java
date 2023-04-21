package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.BlackSeaBasin;
import com.example.river_data_nimh.domain.DunabeBasin;
import com.example.river_data_nimh.domain.EasternWhiteSeaBasin;
import com.example.river_data_nimh.domain.WesternWhiteSeaBasin;
import com.example.river_data_nimh.repositories.BlackSeaBasinRepository;
import com.example.river_data_nimh.repositories.DunabeBasinRepository;
import com.example.river_data_nimh.repositories.EasternWhiteSeaBasinRepository;
import com.example.river_data_nimh.repositories.WesternWhiteSeaBasinRepository;
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
import java.time.LocalDateTime;
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


    @Scheduled(cron = "0 0 12 * * ?")
    public void scrapeAndSave() throws IOException {

        // Fetch the HTML document using Jsoup
        Document doc = Jsoup.connect(URL).get();


        // Get all the table elements from the HTML document
        Elements tables = doc.select("table");

        // Get the current date element
        Element dateElement = doc.select("h2:has(br)").first();

        //Parse dateElement to LocalDate
        LocalDate date = LocalDate.parse(dateElement.text().substring(0, 10), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

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
                                .a_numSt(Integer.valueOf(numSt))
                                .b_name(name)
                                .c_station(station)
                                .d_minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                .e_avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                .f_maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                .g_h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                .h_q("n.a.".equals(q) ? null : Double.valueOf(q))
                                .i_dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                .j_ld(date)
                                .build();
                        dunabeBasinRepository.save(dunabeBasin);
                    }
                    case "Черноморски басейн" -> {
                        BlackSeaBasin blackSeaBasin = BlackSeaBasin.builder()
                                .a_numSt(Integer.valueOf(numSt))
                                .b_name(name)
                                .c_station(station)
                                .d_minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                .e_avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                .f_maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                .g_h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                .h_q("n.a.".equals(q) ? null : Double.valueOf(q))
                                .i_dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                .j_ld(date)
                                .build();
                        blackSeaBasinRepository.save(blackSeaBasin);
                    }
                    case "Източнобеломорски басейн" -> {
                        EasternWhiteSeaBasin easternWhiteSeaBasin = EasternWhiteSeaBasin.builder()
                                .a_numSt(Integer.valueOf(numSt))
                                .b_name(name)
                                .c_station(station)
                                .d_minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                .e_avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                .f_maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                .g_h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                .h_q("n.a.".equals(q) ? null : Double.valueOf(q))
                                .i_dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                .j_ld(date)
                                .build();
                        easternWhiteSeaBasinRepository.save(easternWhiteSeaBasin);
                    }
                    case "Западнобеломорски басейн" -> {
                        WesternWhiteSeaBasin westernWhiteSeaBasin = WesternWhiteSeaBasin.builder()
                                .a_numSt(Integer.valueOf(numSt))
                                .b_name(name)
                                .c_station(station)
                                .d_minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                                .e_avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                                .f_maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                                .g_h("n.a.".equals(h) ? null : Integer.valueOf(h))
                                .h_q("n.a.".equals(q) ? null : Double.valueOf(q))
                                .i_dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                                .j_ld(date)
                                .build();
                        westernWhiteSeaBasinRepository.save(westernWhiteSeaBasin);
                    }
                }
            }
        }
    }
}

