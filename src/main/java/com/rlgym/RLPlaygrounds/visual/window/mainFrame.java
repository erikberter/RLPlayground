package com.rlgym.RLPlaygrounds.visual.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.rlgym.RLPlaygrounds.algorithms.optimization.categories.OptimizationNames;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.rlgym.RLPlaygrounds.configuration.config;
import com.rlgym.RLPlaygrounds.enviroment.games.GameName;
import com.rlgym.RLPlaygrounds.manager.Manager;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class mainFrame {

	private JFrame frmRlplayground;
	
	private Manager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.frmRlplayground.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainFrame() {
		config.initConfig();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRlplayground = new JFrame();
		frmRlplayground.setResizable(false);
		frmRlplayground.setTitle("RLPlayGround");
		frmRlplayground.setBounds(100, 100, 620, 480);
		frmRlplayground.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRlplayground.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel rightPanel = new JPanel();
		frmRlplayground.getContentPane().add(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTittle = new JLabel("Reinforcement Learning Gym");
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(lblTittle, BorderLayout.NORTH);
		lblTittle.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel controlPanel = new JPanel();
		rightPanel.add(controlPanel);
		controlPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel upperControlPanel = new JPanel();
		controlPanel.add(upperControlPanel, BorderLayout.CENTER);
		upperControlPanel.setLayout(new GridLayout(2, 0));
		
		JPanel selectionPanel = new JPanel();
		upperControlPanel.add(selectionPanel);
		selectionPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		selectionPanel.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAlgoritmo = new JLabel("Algoritmos");
		panel_7.add(lblAlgoritmo);
		
		final JComboBox comboBox_1 = new JComboBox();
		panel_7.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(OptimizationNames.values()));
		comboBox_1.setEditable(true);
		
		JPanel panel_8 = new JPanel();
		selectionPanel.add(panel_8);
		
		JLabel lblEnviroment = new JLabel("Enviroment");
		panel_8.add(lblEnviroment);
		
		final JComboBox comboBox = new JComboBox();
		panel_8.add(comboBox);
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(GameName.values()));
		
		JPanel parameterPanel = new JPanel();
		upperControlPanel.add(parameterPanel);
		
		JButton btnHiperparameters = new JButton("Hiperparameters");
		parameterPanel.add(btnHiperparameters);
		
		JButton btnDialog = new JButton("Parameters");
		parameterPanel.add(btnDialog);
		btnDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogParameters j = new dialogParameters();
				j.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				j.setVisible(true);
			}
		});
		btnHiperparameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogHiperParametersDQN j = new dialogHiperParametersDQN();
				j.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				j.setVisible(true);
			}
		});
		
		JPanel buttonPanel = new JPanel();
		controlPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnReset = new JButton("Set Manager");
		buttonPanel.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("SI");
				manager = new Manager().setEnviroment((GameName) comboBox.getSelectedItem()).setOptimization((OptimizationNames) comboBox_1.getSelectedItem());
				manager.initOptimizator();
			}
		});
		
		JButton btnNewButton = new JButton("Train");
		buttonPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.OptimizeAgent();
			}
		});
		
		JButton btnStart = new JButton("Print");
		buttonPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.printResult();
			}
		});
		
		JPanel leftPanel = new JPanel();
		frmRlplayground.getContentPane().add(leftPanel);
		leftPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		leftPanel.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		leftPanel.add(scrollPane_1);
		
		JTree tree = new JTree();
		scrollPane_1.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Model") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("Enviroments");
						node_2 = new DefaultMutableTreeNode("Finite");
							node_2.add(new DefaultMutableTreeNode("GridWorld"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Infinite");
							node_2.add(new DefaultMutableTreeNode("AppleFall"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Algorithms");
						node_1.add(new DefaultMutableTreeNode("Qsa Optimization"));
						node_1.add(new DefaultMutableTreeNode("DQN"));
					add(node_1);
				}
			}
		));
		
		
		
		
	}
}
