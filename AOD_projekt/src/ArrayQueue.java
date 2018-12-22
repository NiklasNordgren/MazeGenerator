

/**
 * The {@code ArrayQueue} class is a data structure that contains objects. Objects are added and removed
 * in FIFO-order (first in, first out). {@code ArrayQueue} uses a circular array implementation.
 *
 * @author Thomas Lundgren, Niklas Nordgren
 * @version 1.0.0
 * @param <T> The type of elements in this {@code ArrayQueue}.
 * @since 2018-11-30
 */

public class ArrayQueue<T> implements Queue<T> {

	private int front = -1;
	private int back = -1;
	private int size;
	private T[] array;

	/**
	 * Instantiates an {@code ArrayQueue} object.
	 *
	 * @param size The maximum number of objects that the {@code ArrayQueue} can hold simultaneously.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int size) {
		this.size = size;
		array = (T[]) new Object[size];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		for(int i = 0; i < array.length; i++)
			array[i] = null;

		front = -1;
		back = -1;
		System.out.print("ArrayQueue is cleared.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return front == -1 && back == -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enqueue(T t) {

		if(isFull())
			System.out.println("es full");

		if(isEmpty()) {
			front = 0;
			back = 0;
		}else
			back = (back + 1) % size;

		array[back] = t;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T dequeue() {

		T temp;

		if(isEmpty())
			System.out.println("es empty");

		if(front == back) {
			temp = array[front];
			front = -1;
			back = -1;
		}else {
			temp = array[front];
			front = (front + 1) % size;
		}

		return temp;
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public T getFront() {
		if(!isEmpty()) 
			return array[front];
		else
			return null;

	}

	/**
	 * Checks if the {@code ArrayQueue} is full.
	 * @return {@code true} if the {@code ArrayStack} is full, otherwise {@code false}.
	 */
	public boolean isFull() {
		return (back + 1) % size == front;
	}

}
