package az.ibar.IbarLoanOrder.Enums;

public enum ResponseCode {
    SUCCESS(1, "OK"),
    VALIDATION(2, "A validation error has occured."),
    DATA_NOT_FOUND(3, "No data found"),
    ALREADY_EXIST(4, " already exist."),
    INTERNAL_ERROR(500, "Internal Error Occured."),
    NOT_FOUND(404, "No data found.");

    private final int code;
    private final String description;

    private ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}