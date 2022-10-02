package kba.exception;

public class ErrorInfo {
    public final String url;
    public final String title;
    public final String details;

    public ErrorInfo(StringBuffer url, KBAException ex) {
        this.url = String.valueOf(url);
        this.title = ex.getMessage();
        this.details = ex.getDetails();
    }
}
