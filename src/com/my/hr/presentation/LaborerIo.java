package com.my.hr.presentation;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;
import com.my.hr.service.LaborerService;

public class LaborerIo {
	private LaborerService laborerService;
	private String menu;
	
	
	public LaborerIo(LaborerService laborerService) {
		this.laborerService = laborerService;
		this.menu = Job.labels();
		
	}
	
	public void play() {
		Job job = null;
		while((job = choose(menu)) != Job.EXIT) {
			switch(job) {
			case LIST: listLaborers(); break;
			case ADD: addLaborer(); break;
			case FIX: fixLaborer(); break;
			case DEL: delLaborer(); break;
			case EXIT: break;
			}
		}
	}
	
	private Job choose(String menu) {
		boolean isGood = false;
		int choice = 0;
		
		do {
			choice = Console.inNum(menu);
			if(choice < 0 || choice > Job.length())
				Console.err("�޴� ��ȣ�� �Է��ϼ���.");
			else isGood = true;
		}while(!isGood);
		
		return Job.toJob(choice);
	}
	
	private void listLaborers() {
		List<Laborer> laborers = laborerService.getLaborers();
		
		System.out.println("ID  �̸�     �Ի���");
		System.out.println("------------------");
		
		if(laborers.size() > 0) {
			laborers.forEach(laborer -> Console.info(laborer));
			
		}else {
			Console.info("�뵿�ڰ� �����ϴ�.");
		}		
	}
	
	private void addLaborer() {
		String laborerName = Console.inStr("�뵿�ڸ��� �Է��ϼ���.", 5);
			
		if(!laborerName.equals("0")) {
			LocalDate hireDate = Console.inDate("�Ի����� �Է��ϼ���.");
			laborerService.addLaborer(laborerName, hireDate);
			Console.info("�뵿�ڸ� �߰��߽��ϴ�.");
		}else {
			Console.info("�߰� ����մϴ�.");
		}
	}
	
	private void fixLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("����");
			if(laborerId > 0) {
				String laborerName = Console.inStr("�뵿�ڸ��� �Է��ϼ���.", 5);
				LocalDate hireDate = Console.inDate("�Ի����� �Է��ϼ���.");
				
				laborerService.fixLaborer(new Laborer(laborerId, laborerName, hireDate));
				Console.info("�뵿�ڸ� �����߽��ϴ�.");
			}
		}else Console.info("�뵿�ڰ� �����ϴ�.");
	}
	
	private void delLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("����");
			if(laborerId > 0) {
				laborerService.delLaborer(laborerId);
				Console.info("�뵿�ڸ� �����߽��ϴ�.");
			}
		}else {
			Console.info("�뵿�ڰ� �����ϴ�.");
		}
	}
	
	private int getLaborer(String job) {
		Laborer laborer = null;
		int laborerId = 0;
		
		do {
			laborerId = Console.inNum("�뵿��ID�� �Է��ϼ���.");
			if(laborerId == 0) {
				Console.info(job + " ����մϴ�.");
				return 0;
			}
			
			laborer = laborerService.getLaborer(laborerId);
			if(laborer == null) {
				Console.err("�ش� ID�� �뵿�ڰ� �����ϴ�.");
			}
		}while(laborer == null);
		
		return laborerId;
	}
	
}
