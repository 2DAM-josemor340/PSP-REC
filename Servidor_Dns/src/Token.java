import java.io.Serializable;

public class Token implements Serializable {
    long clientCode;
    String operation;

    public Token(long clientCode, String operation) {
        this.clientCode = clientCode;
        this.operation = operation;
    }
}