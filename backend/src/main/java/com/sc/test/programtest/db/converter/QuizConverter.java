package com.sc.test.programtest.db.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.sc.test.programtest.db.dto.QuizDTO;
import com.sc.test.programtest.db.model.Quiz;

/**
 * Convert between DTO and MO
 * @author chenhuaquan
 *
 */
public class QuizConverter {

	public static Quiz toModel(final QuizDTO dto){
		final ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, Quiz.class);
	}

	public static QuizDTO toDTO(final Quiz quiz){
		final ModelMapper mapper = new ModelMapper();
		return mapper.map(quiz, QuizDTO.class);
	}

	public static List<QuizDTO> toListDTO(final List<Quiz> list){
		final List<QuizDTO> results = new ArrayList<>();
		for (final Quiz quiz : list){
			results.add(toDTO(quiz));
		}
		return results;
	}
}
