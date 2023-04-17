package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.River;
import com.example.river_data_nimh.repositories.RiverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScraperService {

    private static final String URL = "https://storm.cfd.meteo.bg/meteo7/bg/rekiTablitsa";

    List<River> rivers = new ArrayList<>();

    private final RiverRepository riverRepository;


    public void scrapeAndSave() throws IOException{

            // Fetch the HTML document using Jsoup
            Document doc = Jsoup.connect(URL).get();


            // Get all the table elements from the HTML document
            Elements tables = doc.select("table");


            // Loop through each table element
            for (int i = 0; i < tables.size() - 1; i++) {

                Element table = tables.get(i);

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
                    String id = cells.get(0).text();
                    String name = cells.get(1).text();
                    String station = cells.get(2).text();
                    String minQ = cells.get(3).text().replace(",", ".").replace(" ", "");
                    String avrQ = cells.get(4).text().replace(",", ".").replace(" ", "");
                    String maxQ = cells.get(5).text().replace(",", ".").replace(" ", "");
                    String h = cells.get(6).text().replace(",", ".");
                    String q = cells.get(7).text().replace(",", ".").replace(" ", "");
                    String dH = cells.get(8).text().replace(",", ".");

                    River river = River.builder()
                            .id(Long.valueOf(id))
                            .name(name)
                            .station(station)
                            .minQ("n.a.".equals(minQ) ? null : Double.valueOf(minQ))
                            .avrQ("n.a.".equals(avrQ) ? null : Double.valueOf(avrQ))
                            .maxQ("n.a.".equals(maxQ) ? null : Double.valueOf(maxQ))
                            .h("n.a.".equals(h) ? null : Integer.valueOf(h))
                            .q("n.a.".equals(q) ? null : Double.valueOf(q))
                            .dH("n.a.".equals(dH) ? null : Integer.valueOf(dH))
                            .build();

                    rivers.add(river);
                }
            }
            if(!rivers.isEmpty()){
                riverRepository.saveAll(rivers);
            }
    }
}

