package de.brockhausag.diversitylunchspringboot.voucher.utils;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VoucherCSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"voucherCode"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<VoucherEntity> csvToVoucherEntities(InputStream is) {
        try {
            List<VoucherEntity> voucherEntities = new ArrayList<>();
            CSVParser csvParser = CSVParser.parse(is.toString(), CSVFormat.RFC4180);


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
