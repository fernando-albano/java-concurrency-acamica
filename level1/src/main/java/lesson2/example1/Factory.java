package lesson2.example1;

public class Factory {

	private final ThreadLocal<Object> threadLocal;
	
	public Factory() {
		threadLocal = ThreadLocal.withInitial(Object::new);
	}
	
	public Object getObject() {
		return threadLocal.get();
	}
}
