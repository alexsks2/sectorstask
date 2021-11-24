package com.solbeg.sectorstask.service;

import com.solbeg.sectorstask.entity.Sector;
import com.solbeg.sectorstask.repository.SectorRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SectorService {

    @Value("${app.import.file.path}")
    private Resource fileName;

    private final SectorRepository sectorRepository;

    @Autowired
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public void importSectorList() throws IOException {

        File file = fileName.getFile();

        List<Sector> sectorList = new ArrayList<>();

        Document doc = Jsoup.parse(file, "UTF-8");
        Elements elementsByTag = doc.body().getElementsByTag("option");

        Sector previousSector = null;
        int previousIndent = 0;

        for (Element elem : elementsByTag) {
            Sector sector = new Sector();
            sector.setId(Integer.parseInt(elem.val()));
            sector.setName(elem.text());

            int indent = (elem.wholeText().stripTrailing().length() - elem.text().length()) / 4;

            sector.setIndent(indent);

            if (indent == 0) {
                sector.setParent(null);
            } else {
                switch (indent - previousIndent) {
                    case 1:
                        sector.setParent(previousSector);
                        break;
                    case 0:
                        sector.setParent(previousSector.getParent());
                        break;
                    case -1:
                        sector.setParent(previousSector.getParent().getParent());
                        break;
                    case -2:
                        sector.setParent(previousSector.getParent().getParent().getParent());
                        break;
                    case -3:
                        sector.setParent(previousSector.getParent().getParent().getParent().getParent());
                        break;
                }
            }

            previousSector = sector;
            previousIndent = indent;

            sectorList.add(sector);
        }

        sectorRepository.saveAllAndFlush(sectorList);
    }

    public List<Sector> getAll() {
        List<Sector> sectorList = sectorRepository.findAll();

        for (Sector s : sectorList) {
            s.setName("" + "&nbsp;".repeat(Math.max(0, s.getIndent() * 4)) + s.getName());
        }

        return sectorList;
    }
}

