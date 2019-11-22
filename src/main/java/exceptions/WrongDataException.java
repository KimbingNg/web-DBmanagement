package exceptions;

public class WrongDataException extends Exception {
    WrongDataException(String msg) {
        super(msg);
    }

    public WrongDataException() {
        super();
    }
}
