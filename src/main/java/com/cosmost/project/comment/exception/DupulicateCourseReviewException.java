package com.cosmost.project.comment.exception;

public class DupulicateCourseReviewException extends RuntimeException {
    private static final String MESSAGE = "리뷰를 중복해서 작성 할 수 없습니다.";

    public  DupulicateCourseReviewException() {
        super(MESSAGE);
    }
}
