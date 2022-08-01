package com.my.hr.service;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;

public interface LaborerService {
	List<Laborer> getLaborers();
	Laborer getLaborer(int laborerId);
	void addLaborer(String laborerName, LocalDate HireDate);
	void fixLaborer(Laborer laborer);
	void delLaborer(int laborerId);
}

// naming rule - coding rule(ex. 코딩의 스타일 등)