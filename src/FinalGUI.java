
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
import exceptions.QueueEmptyException;
import exceptions.QueueFullException;
import sort.InsertionSort;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

/**************************************************************
* Name        : DataStructFinal
* Author      : Paul Spuzello
* Created     : 3/26/2021
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : This program is a table that takes in games and lets you sort them. 
* 				
*               Input:  User input for the game
*               
*               Output: User's input is put into the table of data.
*               
* Academic Honesty: I attest that this is my original work. I have not used unauthorized source code, 
* 					either modified or unmodified. I have not given other fellow student(s) access to
*					my program.         
***************************************************************/

public class FinalGUI {
	public static void main(String[] args) {
		
		// Priority Queue and List of games
		PriorityQueue pq = new PriorityQueue();
		GameList list = new GameList();

		/*
		 * GUI Start
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
		 * Sort buttons
		 */
		
		// Name Ascending sort
		JButton btnNameAsc = new JButton("Name Asc.");
		btnNameAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[model.getRowCount()];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
					for (int i = 0; i < rowCount; i++) {
						try {
							arr[i] = list.peekRow(i);
						} catch (LinkedListEmptyException e1) {
							e1.printStackTrace();
						}
					}
					
					is.sort(arr);
					
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < table.getColumnCount(); j++) {
							model.setValueAt(arr[i].charAt(0), i, 0);
							model.setValueAt(arr[i].charAt(1), i, 1);
							model.setValueAt(arr[i].charAt(2), i, 2);
							model.setValueAt(arr[i].charAt(3), i, 3);
						}
					}

				}

			}	
		});
		/*
		int rowCount = model.getRowCount();
		
		InsertionSort is = new InsertionSort();
		
		String[] arr = new String[model.getRowCount()];
		
		if (model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
		}
		else {
			for (int i = 0; i < rowCount; i++) {
			}
			PriorityQueue newPQ = new PriorityQueue();
			try {
				newPQ = list.peekRow(0);
			} catch (LinkedListEmptyException e) {
				e.printStackTrace();
			}
			
			//is.sort(newPQ);
			
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					model.setValueAt(String.valueOf(newPQ.node.name), i, 0);
					model.setValueAt(String.valueOf(newPQ.node.genre), i, 1);
					model.setValueAt(String.valueOf(newPQ.node.releaseDate), i, 2);
					model.setValueAt(String.valueOf(newPQ.node.rating), i, 3);
				}
			}

		}
		*/
		btnNameAsc.setBounds(744, 11, 119, 25);
		frame.getContentPane().add(btnNameAsc);
		
		// Name Descending sort
		JButton btnNameDesc = new JButton("Name Des.");
		btnNameDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
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

			}
		});
		
		btnNameDesc.setBounds(744, 47, 119, 25);
		frame.getContentPane().add(btnNameDesc);
		
		// Rating Ascending sort
		JButton btnRatingAscending = new JButton("Rating Asc.");
		btnRatingAscending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
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

			}
		});
		
		btnRatingAscending.setBounds(744, 227, 119, 25);
		frame.getContentPane().add(btnRatingAscending);
		
		// Rating descending sort
		JButton btnRatingDescending = new JButton("Rating Des.");
		btnRatingDescending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
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

			}
		});
		
		btnRatingDescending.setBounds(744, 263, 119, 25);
		frame.getContentPane().add(btnRatingDescending);
		
		// Genre Descending sort
		JButton btnGenreDes = new JButton("Genre Des.");
		btnGenreDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				
				else {
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

			}
		});
		btnGenreDes.setBounds(744, 119, 119, 25);
		frame.getContentPane().add(btnGenreDes);
		
		// Genre Ascending sort
		JButton btnGenreAsc = new JButton("Genre Asc.");
		btnGenreAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
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

			}
		});
		btnGenreAsc.setBounds(744, 83, 119, 25);
		frame.getContentPane().add(btnGenreAsc);
		
		// Release descending sort
		JButton btnReleaseDes = new JButton("Release Des.");
		btnReleaseDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
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

			}
		});
		btnReleaseDes.setBounds(744, 191, 119, 25);
		frame.getContentPane().add(btnReleaseDes);
		
		// Release Ascending sort
		JButton btnReleaseAsc = new JButton("Release Asc.");
		btnReleaseAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getRowCount();
				
				InsertionSort is = new InsertionSort();
				
				String[] arr = new String[rowCount];
				
				if (model.getRowCount() == 0) {
					JOptionPane.showMessageDialog(frame, "Table is empty, enter a game to sort");
				}
				else {
					
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

			}
		});
		
		btnReleaseAsc.setBounds(744, 155, 119, 25);
		frame.getContentPane().add(btnReleaseAsc);

		/*
		 * End of sorts
		 */
		
		Object[] row = new Object[4];

		// Add button
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

				// Add game
				} else {
					Game game = new Game();

					game.name = nameText.getText();
					game.genre = genreText.getText();
					game.releaseDate = releaseText.getText();
					game.rating = ratingText.getText();

					// Add to list
					try {
						list.addLast(game);
						lblAddedVals.setText("Most recently added data: " + list.printNew());
					} catch (LinkedListFullException e1) {
					}

					// Enqueue then make rows based off the enqueues values
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

		// Delete button
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();

				// The user must click the row to delete, otherwise won't work. If it is clicked
				// after a row selected, it deletes that row
				if (i == 0) {
					JOptionPane.showMessageDialog(frame, "List cannot be empty once a game is entered");
				}
				else if (i >= 0) {
					model.removeRow(i);

					try {
						list.remove(i);
						pq.dequeue();

						lblAddedVals.setText("Most recently deleted: " + list.printNew());

					} catch (LinkedListEmptyException e1) {
						e1.printStackTrace();
					} catch (QueueEmptyException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a row to delete");
				}
			}
		});

		// Adds mouse listener
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();

				nameText.setText(model.getValueAt(i, 0).toString());
				genreText.setText(model.getValueAt(i, 1).toString());
				ratingText.setText(model.getValueAt(i, 2).toString());
				releaseText.setText(model.getValueAt(i, 3).toString());
			}
		});

		// With row clicked, update the row
		
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

