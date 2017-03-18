package com.example.service;

import java.io.PrintWriter;

import org.json.JSONObject;

import com.example.bean.Feedback;

public interface FeedbackService {
	
	void addFeedback(JSONObject jsonObject, PrintWriter printWriter);
}
