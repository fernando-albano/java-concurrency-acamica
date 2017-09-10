package lesson3.example1;

import java.util.Comparator;

public class Priority implements Comparator<Message> {

	public final static int HIGH = 1;
	public final static int MEDIUM = 2;
	public final static int LOW = 3;
	
	@Override
	public int compare(Message m1, Message m2) {
		return Integer.compare(m1.getPriority(), m2.getPriority());
	}
	
	public static String getPriorityName(int priority) {
		switch(priority) {
			case Priority.HIGH: 	return "HIGH";
			case Priority.MEDIUM: 	return "MEDIUM";
			case Priority.LOW: 		return "LOW";
			default: 				return "INVALID";
		}
	}
}
