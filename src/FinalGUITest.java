import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import PQ.PriorityQueue;
import exceptions.LinkedListEmptyException;
import exceptions.LinkedListFullException;
import exceptions.QueueEmptyException;
import exceptions.QueueFullException;

class FinalGUITest {
	
	GameList list = new GameList();
	Game game = new Game();
	PriorityQueue pq = new PriorityQueue();
	
	/*
	 * Test Game Class
	 */
	@Test
	void testGameName() {
		
		game.name = "Super Mario Bros.";
		
		assertEquals("Super Mario Bros.", game.name);
	}
	
	@Test
	void testGameGenre() {
		game.genre = "Platformer";
		
		assertEquals("Platformer", game.genre);
	}
	
	@Test
	void testGameRlease() {
		game.releaseDate = "8/12/1996";
		
		assertEquals("8/12/1996", game.releaseDate);
	}
	
	@Test
	void testGameRating() {
		game.rating = "4/5";
		
		assertEquals("4/5", game.rating);
	}
	
	/*
	 * Test game list class
	 */
	
	@Test
	void testAddGame() throws LinkedListFullException, LinkedListEmptyException {
		game.name = "Super Mario Bros.";
		game.genre = "Platformer";
		game.releaseDate = "8/12/1996";
		game.rating = "4/5";
		
		list.addLast(game);
		
		assertEquals(" Super Mario Bros. Platformer 4/5 8/12/1996 ", list.print());
	}
	
	@Test
	void testRemoveGame() throws LinkedListFullException, LinkedListEmptyException {
		game.name = "Super Mario Bros.";
		game.genre = "Platformer";
		game.releaseDate = "8/12/1996";
		game.rating = "4/5";
		
		list.addLast(game);
		
		list.remove(0);
		
		assertEquals("0", String.valueOf(list.size));
	}
	
	@Test
	void testPeekGame() throws LinkedListFullException, LinkedListEmptyException {
		game.name = "Super Mario Bros.";
		game.genre = "Platformer";
		game.releaseDate = "8/12/1996";
		game.rating = "4/5";
		
		list.addLast(game);
		
		assertEquals("Super Mario Bros.Platformer8/12/19964/5", String.valueOf(list.peekLast()));
	}
	
	@Test
	void testListSize() throws LinkedListFullException {
		game.name = "Super Mario Bros.";
		game.genre = "Platformer";
		game.releaseDate = "8/12/1996";
		game.rating = "4/5";
		
		list.addLast(game);
		
		assertEquals("1", String.valueOf(list.size));
	}
	
	/*
	 * Test Priority Queue
	 */

	@Test
	void testEnqueue() throws QueueFullException, QueueEmptyException {
		pq.enqueue("Super Mario", "Platformer", "8/12/1996", "4/5");
		
		assertEquals("Super Mario", pq.node.name);
	}
	
	@Test
	void testDequeue() throws QueueFullException, QueueEmptyException {
		pq.enqueue("Super Mario", "Platformer", "8/12/1996", "4/5");
		pq.dequeue();
		
		assertEquals("", pq.node.name);
	}
}
