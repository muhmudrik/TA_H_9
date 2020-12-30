package apap.tk.SIRekrutmenH9.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T>{
    private int status;
    private String message;
    private T result;

    // mengembalikan status
    public int getStatus() {
        return status;
    }

    // mengembalikan message
    public String getMessage() {
        return message;
    }

    // mengembalikan hasil
    public T getResult() {
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
    public void setResult(T result) {
        this.result = result;
    }
}
