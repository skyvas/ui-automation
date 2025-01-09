package exceptions;

public class PageLoadTimeoutException extends RuntimeException {
    public PageLoadTimeoutException(String message) {
        super(message);

        // Usage:
        //if (!isPageLoaded()) {
        //    throw new PageLoadTimeoutException("The page did not load within the specified timeout.");
        //}
    }
}
