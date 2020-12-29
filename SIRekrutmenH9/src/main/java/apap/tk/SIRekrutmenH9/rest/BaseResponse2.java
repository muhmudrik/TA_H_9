package apap.tk.SIRekrutmenH9.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse2{
    private int status;
    private String message;
    private PegawaiData result;

    // mengembalikan status
    public int getStatus() {
        return status;
    }

    // mengembalikan message
    public String getMessage() {
        return message;
    }

    // mengembalikan hasil
    public PegawaiData getResult() {
        return result;
    }

    // mengembalikan status untuk di set
    public void setStatus(int status) {
        this.status = status;
    }

    // mengembalikan message untuk di set
    public void setMessage(String message) {
        this.message = message;
    }

    // mengembalikan hasil untuk di set
    public void setResult(PegawaiData result) {
        this.result = result;
    }
}