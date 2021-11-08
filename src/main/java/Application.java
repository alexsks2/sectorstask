import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        try {
            Properties props = readProperties();

            String dbConnUrl = props.getProperty("db.conn.url");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");

            List<Sector> sectorsToInsert = prepareList("index.html");

            try {
                Connection con = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
                PreparedStatement pst = con.prepareStatement("INSERT INTO sectors(id,name,parent) "
                         + "VALUES(?,?,?)");

                for (Sector sector : sectorsToInsert) {
                    pst.setInt(1, sector.getId());
                    pst.setString(2, sector.getName());

                    if (sector.getParent() != null) {
                        pst.setInt(3, sector.getParent().getId());
                    } else {
                        pst.setNull(3, Types.INTEGER);
                    }

                    pst.addBatch();
                }
                pst.executeBatch();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Properties readProperties() throws IOException {

        Properties props = new Properties();
        Path dbSettingsPropertyFile = Paths.get("src/main/resources/application.properties");

        BufferedReader bf = Files.newBufferedReader(dbSettingsPropertyFile, StandardCharsets.UTF_8);
        props.load(bf);

        return props;
    }

    private static List<Sector> prepareList(String fileName) throws URISyntaxException, IOException {
        URL res = Application.class.getClassLoader().getResource(fileName);
        File file = Paths.get(Objects.requireNonNull(res).toURI()).toFile();

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

        return sectorList;
    }
}