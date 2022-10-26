package de.brockhausag.diversitylunchspringboot.voucher.utils;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import liquibase.util.csv.opencsv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VoucherCSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "voucherCode" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<VoucherEntity> csvToVoucherEntities(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<VoucherEntity> voucherEntities = new ArrayList<VoucherEntity>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                VoucherEntity voucherEntity = new VoucherEntity(
                        csvRecord.get("voucherCode"),
                );

                voucherEntities.add(voucherEntity);
            }

            return voucherEntities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
