
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import PQ.PriorityQueue;
import exceptions.LinkedListEmptyException;
import exceptions.LinkedListFullException;
import exceptions.QueueFullException;
import sort.InsertionSort;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class FinalGUI {
	public static void main(String[] args) {
		// Priority Queue and List of games
		PriorityQueue pq = new PriorityQueue();
		GameList list = new GameList();

		/*
		 * GUI Stuff
		 */

		// Create JFrame and table, color frame
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.shadow"));
		JTable table = new JTable();

		// Name columns and set into model, set the model into the table
		Object[] columns = { "Game Name", "Genre", "Rating", "Release Date" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);

		// Color of table, changing font and row height
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 12);
		table.setFont(font);
		table.setRowHeight(30);

		// Create columns and get hem ready for input
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRender);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRender);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRender);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRender);

		// Text fields for text input
		JTextField nameText = new JTextField();
		JTextField genreText = new JTextField();
		JTextField releaseText = new JTextField();
		JTextField ratingText = new JTextField();

		// Buttons to add, update and delete
		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");

		// Set where the input boxes and buttons are located
		nameText.setBounds(101, 61, 100, 25);
		genreText.setBounds(101, 97, 100, 25);
		releaseText.setBounds(101, 133, 100, 25);
		ratingText.setBounds(101, 169, 100, 25);
		btnAdd.setBounds(101, 205, 100, 25);
		btnUpdate.setBounds(101, 241, 100, 25);
		btnDelete.setBounds(101, 277, 100, 25);

		// Make a pane of the table above, set location
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(232, 0, 502, 302);

		// Adding things to the frame such as text inputs, buttons and labels
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(pane);

		frame.getContentPane().add(nameText);
		frame.getContentPane().add(genreText);
		frame.getContentPane().add(releaseText);
		frame.getContentPane().add(ratingText);

		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(btnDelete);
		frame.getContentPane().add(btnUpdate);

		JLabel lblAddedVals = new JLabel(""); // Spot to describe the last entered game
		lblAddedVals.setVerticalAlignment(SwingConstants.TOP);
		lblAddedVals.setBounds(0, 330, 734, 50);
		frame.getContentPane().add(lblAddedVals);

		// Labels
		JLabel lblName = new JLabel("Game Name:");
		lblName.setBounds(10, 66, 102, 14);
		frame.getContentPane().add(lblName);

		JLabel lblGenre = new JLabel("Game Genre:");
		lblGenre.setBounds(10, 102, 102, 14);
		frame.getContentPane().add(lblGenre);

		JLabel lblRelease = new JLabel("Game Release:");
		lblRelease.setBounds(10, 138, 102, 14);
		frame.getContentPane().add(lblRelease);

		JLabel lblRating = new JLabel("Game Rating:");
		lblRating.setBounds(10, 174, 102, 14);
		frame.getContentPane().add(lblRating);
		
		/*
		 * Sorts
		 */
		JButton btnNameAsc = new JButton("Name Asc.");
		btnNameAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNameAsc.setBounds(744, 61, 119, 25);
		frame.getContentPane().add(btnNameAsc);
		
		JButton btnNameDesc = new JButton("Name Des.");
		btnNameDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNameDesc.setBounds(744, 97, 119, 25);
		frame.getContentPane().add(btnNameDesc);
		
		JButton btnRatingAscending = new JButton("Rating Asc.");
		btnRatingAscending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				for (int i = 0; i < rowCount; i++) {
					try {
						arr[i] = list.peekRow(i);
					} catch (LinkedListEmptyException e1) {
						e1.printStackTrace();
					}
				}
				
				is.sort(arr);
				
				for (int i = 0; i < rowCount; i++) {
					model.setValueAt(arr[i].charAt(0), i, 0);
					model.setValueAt(arr[i].charAt(1), i, 1);
					model.setValueAt(arr[i].charAt(2), i, 2);
					model.setValueAt(arr[i].charAt(3), i, 3);
				}
			}
		});
		
		btnRatingAscending.setBounds(744, 241, 119, 25);
		frame.getContentPane().add(btnRatingAscending);
		
		JButton btnRatingDescending = new JButton("Rating Des.");
		btnRatingDescending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				for (int i = 0; i < rowCount; i++) {
					try {
						arr[i] = list.peekRow(i);
					} catch (LinkedListEmptyException e1) {
						e1.printStackTrace();
					}
				}
				
				is.sortDesc(arr);
				
				for (int i = 0; i < rowCount; i++) {
					model.setValueAt(arr[i].charAt(0), i, 0);
					model.setValueAt(arr[i].charAt(1), i, 1);
					model.setValueAt(arr[i].charAt(2), i, 2);
					model.setValueAt(arr[i].charAt(3), i, 3);
				}
			}
		});
		
		btnRatingDescending.setBounds(744, 277, 119, 25);
		frame.getContentPane().add(btnRatingDescending);

		// Create rows object
		Object[] row = new Object[4];

		// Once the add button is pressed
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// If the Priority Queue is full, show a message and do nothing
				if (pq.isFull()) {
					JOptionPane.showMessageDialog(frame, "The queue of games is full, please delete a game");
				}
				// If a data field is empty, prompt to finish entering data
				if (nameText.getText().equals("") || genreText.getText().equals("") || releaseText.getText().equals("")
						|| ratingText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Please enter all Data");
					// If all passes, make a game and insert the inputed values
				} else {
					Game game = new Game();

					game.name = nameText.getText();
					game.genre = genreText.getText();
					game.releaseDate = releaseText.getText();
					game.rating = ratingText.getText();

					// Try to add the game to the gameList named list made at the top
					try {
						list.addLast(game);
						lblAddedVals.setText("Most recently added data: " + list.print());
					} catch (LinkedListFullException e1) {
						System.out.println("Error");
					}

					// Enqueue the inputed fields into the priority queue, use the priority queue to
					// then fill in the table
					try {
						pq.enqueue(nameText.getText(), genreText.getText(), releaseText.getText(),
								ratingText.getText());

						row[0] = pq.node.getName();
						row[1] = pq.node.getGenre();
						row[2] = pq.node.getReleaseDate();
						row[3] = pq.node.getRating();

						model.addRow(row);

					} catch (QueueFullException e1) {
						System.out.println("Priority Queue is full");
					}
				}
			}
		});

		// Delete button clicked
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();

				// The user must click the row to delete, otherwise won't work. If it is clicked
				// after a row
				// selected, it deletes that row
				if (i >= 0) {
					model.removeRow(i);

					try {
						list.remove(i);

						lblAddedVals.setText(list.print());

					} catch (LinkedListEmptyException e1) {
						e1.printStackTrace();
					}

					pq.size--;
					list.size--;
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a row to delete");
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();

				nameText.setText(model.getValueAt(i, 0).toString());
				genreText.setText(model.getValueAt(i, 1).toString());
				releaseText.setText(model.getValueAt(i, 2).toString());
				ratingText.setText(model.getValueAt(i, 3).toString());
			}
		});

		// On click, update selected row
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();

				if (i >= 0) {
					model.setValueAt(nameText.getText(), i, 0);
					model.setValueAt(genreText.getText(), i, 1);
					model.setValueAt(releaseText.getText(), i, 2);
					model.setValueAt(ratingText.getText(), i, 3);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a row to update");
				}
			}
		});

		frame.setSize(900, 430);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
