/*
 * 싱글턴 패턴
 * 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이다.
 * 최초 생성 이후에 호출된 생성자는 최초 생성자가 생성한 객체를 리턴한다.
 * 주로 공통된 객체를 여러개 생성해서 사용하는 DBCP(DataBase Connetion Pool)와 같은 상황에서 많이 사용된다.
 * 생성자를 private으로 선언하여 상속이 불가능함을 지정하기도 한다.
 * ref : 위키백과.
 * 메모리 낭비 방지. 공통된 객체 사용 시 유용.
 * 싱글턴 인스턴스가 너무 많은 일을 하거나 많은 데이터를 공유시킬 경우 결합도가 높아진다.
 */
package pattern.singleton;

// 멀티쓰레드에서 안전한(Thread-safe) 싱글턴.
// 1. Thread safe Lazy initialization (게으른 초기화)
class ThreadSafeLazyInit{
	private static ThreadSafeLazyInit instance;
	
	// private 생성자로 외부 생성 방지.
	private ThreadSafeLazyInit() {}
	
	// synchronized : thread-safe.
	public static synchronized ThreadSafeLazyInit getInstance() {
		if(instance == null) {
			instance = new ThreadSafeLazyInit();
		}
		return instance;
	}
}

// 2. Thread safe lazy Initialization + Double-checked locking
// 게ㅡ른 초기화의 성능 저하 완화.
class ThreadSafeLazyInit2{
	private volatile static ThreadSafeLazyInit2 instance;
	
	private ThreadSafeLazyInit2(){}
	
	public static ThreadSafeLazyInit2 getInstance() {
		if(instance == null) {
			// 최초 인스턴스 생성 후 synchronized 블럭을 타지 않기 때문에 성능저하 완화.
			synchronized (ThreadSafeLazyInit2.class) {
				//if(instance == null)
					instance = new ThreadSafeLazyInit2();
			}
		}
		return instance;
	}
}

// 3. Initialization on demand holder idiom
// holder에 의한 초기화.
// 클래스 안에 Holder 클래스를 두어 JVM의 Class loader 매커니즘과 Class가 로드되는 시점을 이용한 방법.
// JVM의 클래스 초기화 과정에서 보장되는 원자적 특성을 이용하여 싱글턴의 초기화 문제에 대한 책임을 JVM에 떠넘긴다. 
class Something{
	private Something() {}
	
	private static class LazyHolder{
		public static final Something INSTANCE = new Something();
	}
	
	public static Something getInstance(){
		return LazyHolder.INSTANCE;
	}
}

public class Main {
	public static void main(String[] args) {
		// 객체 생성.
		ThreadSafeLazyInit tsli = ThreadSafeLazyInit.getInstance();
		ThreadSafeLazyInit2 tsli2 = ThreadSafeLazyInit2.getInstance();
		Something st = Something.getInstance();
	}
}
