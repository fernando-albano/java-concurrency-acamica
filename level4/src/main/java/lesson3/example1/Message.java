package lesson3.example1;

public class Message {
	
	private String content;
	private int priority;
	
	public Message(String content, int priority) {
		this.content = content;
		this.priority = priority;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getPriorityName() {
		return Priority.getPriorityName(priority);
	}
}
