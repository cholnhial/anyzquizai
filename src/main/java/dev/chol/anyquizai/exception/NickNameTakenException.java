package dev.chol.anyquizai.exception;

public class NickNameTakenException extends RuntimeException {

    public NickNameTakenException(String nickname) {
        super("The following nickname is already taken: " + nickname);
    }
}
