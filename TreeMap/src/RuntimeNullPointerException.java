public class RuntimeNullPointerException extends RuntimeException {
    public RuntimeNullPointerException() {}

    public RuntimeNullPointerException(String message) {
        System.out.println(message);
    }
}
