package de.brockhausag.diversitylunchspringboot.voucher.utils;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VoucherCSVHelper {

    public static List<VoucherEntity> csvToVoucherEntities(InputStream is) {
        try {
            List<VoucherEntity> voucherEntities = new ArrayList<>();
            CSVParser csvParser = CSVParser.parse(is, StandardCharsets.UTF_8, CSVFormat.RFC4180);


            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                VoucherEntity voucherEntity = new VoucherEntity(csvRecord.get(0));
                voucherEntities.add(voucherEntity);
            }

            return voucherEntities;
        } catch (IOException ex) {
            throw new RuntimeException("fail to parse CSV file: " + ex.getMessage());
        }
    }
}
