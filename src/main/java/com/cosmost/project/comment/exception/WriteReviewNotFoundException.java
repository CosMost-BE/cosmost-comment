package com.cosmost.project.comment.exception;

public class WriteReviewNotFoundException extends RuntimeException {

    private static final String MESSAGE = "작성한 리뷰가 존재하지 않습니다.";

    public  WriteReviewNotFoundException() {
        super(MESSAGE);
    }
}
