package com.example.mapper;

import java.util.List;

import com.example.bean.Feedback;

public interface FeedbackMapper {
	
	List<Feedback> selectFeedbackByPatDocId(int patient_id, int doctor_id);

	void updateBatch(List<Integer> list);
	
	int addFeedback(Feedback feedback);
}
