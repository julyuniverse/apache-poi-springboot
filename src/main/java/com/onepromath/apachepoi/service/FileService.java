package com.onepromath.apachepoi.service;

import com.onepromath.apachepoi.dto.learning.ResponseLearningData;
import com.onepromath.apachepoi.dto.learning.ResponseLevelChapterSeqUnitSeq;
import com.onepromath.apachepoi.mapper.learning.LearningMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FileService {
    private final LearningMapper learningMapper;

    public FileService(LearningMapper learningMapper) {
        this.learningMapper = learningMapper;
    }

    public String downloadCsv(int level, int unitCount) {
        String data = "";
        data += "레벨,챕터(단원),유닛(스테이지),학습모드,프로필넘버,문제개수,맞춘개수,틀린개수,정확도,학습시간(초),등급,시작시각,종료시각,도전횟수\n";

        ArrayList<ResponseLevelChapterSeqUnitSeq> responseLevelChapterSeqUnitSeqArrayList = learningMapper.getLevelChapterSeqUnitSeq(level);

        for (ResponseLevelChapterSeqUnitSeq levelChapterSeqUnitSeq : responseLevelChapterSeqUnitSeqArrayList) {
            ArrayList<ResponseLearningData> responseLearningDataArrayList = learningMapper.getLearningData(levelChapterSeqUnitSeq.getLevel(), levelChapterSeqUnitSeq.getChapterSeq(), levelChapterSeqUnitSeq.getUnitSeq(), unitCount);

            for (ResponseLearningData learningData : responseLearningDataArrayList) {
                System.out.println(learningData.toString());
                data += learningData.getLevel() + ",";
                data += learningData.getChapterSeq() + ",";
                data += learningData.getUnitSeq() + ",";
                data += learningData.getLearningMode() + ",";
                data += learningData.getProfileNo() + ",";
                data += learningData.getSolveCnt() + ",";
                data += learningData.getRightCnt() + ",";
                data += learningData.getWrongCnt() + ",";
                data += learningData.getAccuracy() + ",";
                data += learningData.getSecond() + ",";
                data += learningData.getGrade() + ",";
                data += learningData.getStartTime() + ",";
                data += learningData.getEndTime() + ",";
                data += learningData.getTryCnt() + "\n";
            }
        }

        return data;
    }
}
