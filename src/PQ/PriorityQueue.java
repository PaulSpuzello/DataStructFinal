package PQ;

import java.util.Arrays;
import java.util.LinkedList;

import exceptions.QueueEmptyException;
import exceptions.QueueFullException;

public class PriorityQueue {
	// Variables
	private int head;
	private int tail;
	public int size;
	private int maxSize;
	private LinkedList<Object> list;
	public Node node = new Node();

	// Constructors
	public PriorityQueue() {
		this.maxSize = 10;
		this.size = 0;
		this.head = -1;
		this.tail = -1;
		this.list = new LinkedList<>();
		this.node = new Node();
	}

	public PriorityQueue(Node node) {
		this.maxSize = 10;
		this.size = 0;
		this.head = -1;
		this.tail = -1;
		this.list = new LinkedList<>();
		this.node = new Node();
	}

	public boolean isFull() { // Check if full
		if (size == maxSize) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() { // Check if empty
		if (size == 0) {
			return true;
		} else {
			return false;
		}

	}

	public int size() { // Check size
		return this.head + 1;
	}

	public void enqueue(String gameName, String gameGenre, String releaseDate, String rating)
			throws QueueFullException { // Put something in a queue
		System.out.println(size);
		if (!this.isFull()) {
			list.addLast(gameName + gameGenre + releaseDate + rating);

			node.setName(gameName);
			node.setGenre(gameGenre);
			node.setReleaseDate(releaseDate);
			node.setRating(rating);

			tail = (tail + 1) % maxSize;
			size++;
			head++;
		} else {
			throw new QueueFullException();
		}
	}

	public Object dequeue() throws QueueEmptyException { // Take something out of a queue
		Object item;
		System.out.println(size);
		if (!this.isEmpty()) {
			item = list.get(tail);
			list.remove(tail);
			
			size--;
			head--;
			tail--;
			return item;
		} else {
			throw new QueueEmptyException();
		}
	}

	public String[] printPriorityQueue() throws QueueEmptyException { // Print the queue in order of priority

		String[] queueString = { "", "", "", "", "", "", "", "", "", "", "" };

		if (this.isEmpty()) {
			throw new QueueEmptyException();
		}

		for (int i = 0; i <= head; i++) { // Add to the queueString array
			queueString[i] = list.get(i).toString();

		}
		Arrays.sort(queueString); // Sort the array
		return queueString;
	}
}
