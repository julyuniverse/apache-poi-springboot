package com.onepromath.apachepoi.mapper.learning;

import com.onepromath.apachepoi.dto.learning.ResponseLearningData;
import com.onepromath.apachepoi.dto.learning.ResponseLevelChapterSeqUnitSeq;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface LearningMapper {
    @Select("select c.level, c.chapter_seq, u.unit_seq\n" +
            "from CHAPTER_2 c\n" +
            "         left join UNIT_2 u on u.CHAPTER_NO = c.NO\n" +
            "where level = #{level}\n" +
            "group by c.level, c.chapter_seq, u.unit_seq;\n")
    @Results(id="levelChapterSeqUnitSeq", value = {
            @Result(property = "level", column = "level"),
            @Result(property = "chapterSeq", column = "chapter_seq"),
            @Result(property = "unitSeq", column = "unit_seq")
    })
    ArrayList<ResponseLevelChapterSeqUnitSeq> getLevelChapterSeqUnitSeq(@Param("level") int level);

    @Select("select c.level, c.chapter_seq, u.unit_seq, learning_data.learning_mode, learning_data.profile_no, learning_data.solve_cnt, learning_data.right_cnt, learning_data.wrong_cnt, learning_data.accuracy, learning_data.time second, learning_data.grade, learning_data.start_time, learning_data.end_time, learning_data.try_cnt\n" +
            "from CHAPTER_2 c\n" +
            "         left join UNIT_2 u on u.CHAPTER_NO = c.NO\n" +
            "         left join (select '1' learning_mode, UNIT_NO unit_no, LOGIN_ID_PROFILE_NO profile_no, solve_cnt, right_cnt, wrong_cnt, accuracy, time, result_grade grade, start_time, end_time, try_cnt from RESULT_DAILY_2\n" +
            "                    union all\n" +
            "                    select '2' learning_mode, UNIT_NO unit_no, LOGIN_ID_PROFILE_NO profile_no, solve_cnt, right_cnt, wrong_cnt, accuracy, time, result_grade grade, start_time, end_time, try_cnt from RESULT_FREE_2\n" +
            "                    union all\n" +
            "                    select '4' learning_mode, UNIT_NO unit_no, LOGIN_ID_PROFILE_NO profile_no, solve_cnt, right_cnt, wrong_cnt, accuracy, time, result_grade grade, start_time, end_time, try_cnt from RESULT_WORLD_2) learning_data\n" +
            "                   on learning_data.unit_no = u.NO\n" +
            "where c.level = #{level}\n" +
            "  and c.chapter_seq = #{chapterSeq}\n" +
            "  and u.unit_seq = #{unitSeq}\n" +
            "order by learning_data.start_time desc\n" +
            "limit #{unitCount};")
    @Results(id="learningData", value = {
            @Result(property = "level", column = "level"),
            @Result(property = "chapterSeq", column = "chapter_seq"),
            @Result(property = "unitSeq", column = "unit_seq"),
            @Result(property = "learningMode", column = "learning_mode"),
            @Result(property = "profileNo", column = "profile_no"),
            @Result(property = "solveCnt", column = "solve_cnt"),
            @Result(property = "rightCnt", column = "right_cnt"),
            @Result(property = "wrongCnt", column = "wrong_cnt"),
            @Result(property = "accuracy", column = "accuracy"),
            @Result(property = "second", column = "second"),
            @Result(property = "grade", column = "grade"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "tryCnt", column = "try_cnt"),
    })
    ArrayList<ResponseLearningData> getLearningData(@Param("level") int level, @Param("chapterSeq") int chapterSeq, @Param("unitSeq") int unitSeq, @Param("unitCount") int unitCount);
}
