import PQ.PriorityQueue;
import exceptions.LinkedListEmptyException;
import exceptions.LinkedListFullException;

// Linked List
public class GameList {

	// Variables
	public int size;
	private int maxSize;
	private Game gameList[];

	// Constructor
	public GameList() {
		this.maxSize = 10;
		this.size = 0;
		this.gameList = new Game[maxSize];
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

	// Add a value to the end
	public void addLast(Game item) throws LinkedListFullException {
		if (this.isFull()) {
			throw new LinkedListFullException();
		}
		gameList[size] = item;
		size++;
	}

	// Add a value to a certain index
	public void add(int index, Game item) throws LinkedListFullException {
		if (this.isFull()) {
			throw new LinkedListFullException();
		}
		gameList[index] = item;
		size++;

	}

	// Return without deleting the last value
	public String peekLast() throws LinkedListEmptyException {
		if (!this.isEmpty()) {
			for (int i = 0; i < size; i++) {
				if (i == size - 1) {
					return gameList[i].name + gameList[i].genre + gameList[i].releaseDate + gameList[i].rating;
				}
			}
		} else {
			throw new LinkedListEmptyException();
		}
		return "";
	}

	public String peekRow(int row) throws LinkedListEmptyException {
		if (!this.isEmpty()) {
			return gameList[row].name + gameList[row].genre + gameList[row].releaseDate + gameList[row].rating;
		} else
			throw new LinkedListEmptyException();
	}

	// Remove a value depending on index
	public double remove(int index) throws LinkedListEmptyException {
		if (this.isEmpty()) {
			throw new LinkedListEmptyException();
		}

		double item = 0;
		size--;

		return item;
	}

	// Return the size of the array
	public int size() {
		return size;
	}

	// Return max size
	public int maxSize() {
		return maxSize;
	}

	// Print the array's values
	public String print() {
		String listString = new String();

		for (int i = 0; i < size; i++) {
			listString += " " + gameList[i].getName() + " " + gameList[i].getGenre() + " " + gameList[i].getRating()
					+ " " + gameList[i].getReleaseDate() + " ";
		}
		return listString;
	}

	// Print last added game
	public String printNew(int row) {
		String listString = new String();

		listString += " " + gameList[row].getName() + " " + gameList[row].getGenre() + " " + gameList[row].getRating()
				+ " " + gameList[row].getReleaseDate() + " ";

		return listString;
	}
}
