package PQ;

import exceptions.QueueEmptyException;
import exceptions.QueueFullException;

public class PriorityQueue {
	// Variables
	private int head;
	private int tail;
	public int size;
	private int maxSize;
	Node next;
	public Node node = new Node();

	// Constructors
	public PriorityQueue() {
		this.maxSize = 10;
		this.size = 0;
		this.head = -1;
		this.tail = -1;
		this.node = new Node();
	}

	public PriorityQueue(Node node) {
		this.maxSize = 10;
		this.size = 0;
		this.head = -1;
		this.tail = -1;
		this.node = new Node();
	}

	// Check if full
	public boolean isFull() { 
		if (size == maxSize) {
			return true;
		} else {
			return false;
		}
	}

	// Check if empty
	public boolean isEmpty() { 
		if (size == 0) {
			return true;
		} else {
			return false;
		}

	}

	// Return size
	public int size() { 
		return this.head + 1;
	}

	// Add to the priority queue
	public void enqueue(String gameName, String gameGenre, String releaseDate, String rating)
			throws QueueFullException {
		
		if (!this.isFull()) {

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
		/*
		if (!this.isFull()) {

			Node temp = new Node();
			
			temp.setName(gameName);
			temp.setGenre(gameGenre);
			temp.setReleaseDate(releaseDate);
			temp.setRating(rating);

			tail = (tail + 1) % maxSize;
			size++;
			head++;
			
			return temp;
		} else {
			throw new QueueFullException();
		}*/
	}

	// Remove the value first put into the queue
	public void dequeue() throws QueueEmptyException {
		
		if (!this.isEmpty()) {			
			node.setName("");
			node.setGenre("");
			node.setReleaseDate("");
			node.setRating("");
			
			size--;
			head -= 1;
		} else {
			throw new QueueEmptyException();
		}
	}
}
