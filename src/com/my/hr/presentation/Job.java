package com.my.hr.presentation;

public enum Job {
	EXIT("종료"), LIST("목록"), ADD("추가"), FIX("수정"), DEL("삭제");

	private String label;
	
	private Job(String label) {
		this.label = label;
	}
	
	public static int length() {
		return values().length;
	}
	
	public static Job toJob(int ordinal) {
		return values()[ordinal];
	}
	
	public static String labels() {
		Job[] jobs = values();
		String line = "";
		String last = "";
		
		for(Job job: jobs) {
			if(job.ordinal() == 0) last += job.ordinal() + "." + job.label;
			else line += job.ordinal() + "." + job.label + ", ";
		}
		line += last;
		return line;
	}
}
