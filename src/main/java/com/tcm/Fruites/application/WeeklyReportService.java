package com.tcm.Fruites.application;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.persistence.StoreRepository;

public class WeeklyReportService {
	
	public WeeklyReportService() {
		this.startScheduler();
	}

	private void startScheduler() {
		Calendar with = Calendar.getInstance();
		Map<Integer, Integer> dayToDelay = new HashMap<Integer, Integer>();
		dayToDelay.put(Calendar.FRIDAY, 2);
		dayToDelay.put(Calendar.SATURDAY, 1);
		dayToDelay.put(Calendar.SUNDAY, 0);
		dayToDelay.put(Calendar.MONDAY, 6);
		dayToDelay.put(Calendar.TUESDAY, 5);
		dayToDelay.put(Calendar.WEDNESDAY, 4);
		dayToDelay.put(Calendar.THURSDAY, 3);
		int dayOfWeek = with.get(Calendar.DAY_OF_WEEK);
		int hour = with.get(Calendar.HOUR_OF_DAY);
		int delayInDays = dayToDelay.get(dayOfWeek);
		int delayInHours = 0;
		if(delayInDays == 6 && hour<11) {
			delayInHours = 11 - hour;
		}
		else {
			delayInHours = delayInDays*24+((24-hour)+11);
		}
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new WeeklyTask(), delayInHours, 179, TimeUnit.HOURS);
	}
	
	class WeeklyTask implements Runnable{

		@Override
		public void run() {
			
			System.out.println("executant supplier");
			for (Store store: StoreRepository.getAllStores()) {
				System.out.println(store.getAllFruits().size());
			}
			
			try {
				SupplierController.supplyStore();
			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}
		}
	}
}
