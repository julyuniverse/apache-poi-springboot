package com.onepromath.apachepoi.dto.learning;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseLearningData {
    private int level;
    private int chapterSeq;
    private int unitSeq;
    private int learningMode;
    private int profileNo;
    private int solveCnt;
    private int rightCnt;
    private int wrongCnt;
    private int accuracy;
    private int second;
    private int grade;
    private String startTime;
    private String endTime;
    private int tryCnt;
}
