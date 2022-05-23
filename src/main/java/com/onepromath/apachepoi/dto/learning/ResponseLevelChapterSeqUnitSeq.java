package com.onepromath.apachepoi.dto.learning;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseLevelChapterSeqUnitSeq {
    private int level;
    private int chapterSeq;
    private int unitSeq;
}
