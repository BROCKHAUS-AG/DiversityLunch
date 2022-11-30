package de.brockhausag.diversitylunchspringboot.voucher.exception;

public class ForbiddenVoucherClaim extends Exception {

    final static String FORBIDDEN = "user tried to claim voucher without meeting requirements";

    public ForbiddenVoucherClaim() {
        super(FORBIDDEN);
    }
}
