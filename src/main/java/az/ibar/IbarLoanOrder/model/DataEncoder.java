package az.ibar.IbarLoanOrder.model;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * @author Ramil Mammadov
 */
public class DataEncoder {
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }
}
