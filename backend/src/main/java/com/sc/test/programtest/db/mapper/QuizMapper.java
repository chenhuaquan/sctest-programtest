package com.sc.test.programtest.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sc.test.programtest.db.model.Quiz;

@Mapper
public interface QuizMapper {

	@Select("select * from quiz where id = #{id}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Quiz findById(@Param("id") Long id);

}
