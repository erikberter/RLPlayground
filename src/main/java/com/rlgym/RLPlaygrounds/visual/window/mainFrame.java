package com.rlgym.RLPlaygrounds.visual.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;


import com.rlgym.RLPlaygrounds.agent.Agent;

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

public class mainFrame {

	private JFrame frame;
	
	private Agent manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reinforcement Learning Gym");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 300, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 264, 274, 166);
		frame.getContentPane().add(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Model") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("Enviroments");
						node_2 = new DefaultMutableTreeNode("Finite");
							node_2.add(new DefaultMutableTreeNode("GridWorld"));
							node_2.add(new DefaultMutableTreeNode("k-Arm Bandit"));
							node_2.add(new DefaultMutableTreeNode("Fake Gambler"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Infinite");
							node_2.add(new DefaultMutableTreeNode("Pong"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Agents & Algorithms");
						node_2 = new DefaultMutableTreeNode("MDP");
							node_2.add(new DefaultMutableTreeNode("Qsa Optimization"));
							node_2.add(new DefaultMutableTreeNode("Qsa r Opimization"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Enviroment", "GridWorld", "K-Arms Bandit", "AppleFall"}));
		comboBox.setBounds(10, 307, 140, 20);
		frame.getContentPane().add(comboBox);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Algorithms", "Qsa Optimization", "DQN"}));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(10, 276, 140, 20);
		frame.getContentPane().add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Agents", "MDP"}));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(160, 276, 140, 20);
		frame.getContentPane().add(comboBox_2);
		
		final JLabel lblEpochs = new JLabel("Epochs");
		lblEpochs.setHorizontalAlignment(SwingConstants.CENTER);
		lblEpochs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEpochs.setBounds(10, 60, 140, 20);
		frame.getContentPane().add(lblEpochs);
		
		final JLabel lblLearningRate = new JLabel("Learning Rate");
		lblLearningRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLearningRate.setBounds(170, 118, 140, 20);
		frame.getContentPane().add(lblLearningRate);
		
		final JLabel lblExploringRate = new JLabel("Exploring Rate");
		lblExploringRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblExploringRate.setBounds(10, 118, 140, 20);
		frame.getContentPane().add(lblExploringRate);
		
		final JLabel lblDiscountFactor = new JLabel("Discount Factor");
		lblDiscountFactor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscountFactor.setBounds(170, 60, 140, 20);
		frame.getContentPane().add(lblDiscountFactor);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(320, 11, 274, 241);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton btnReset = new JButton("Set Manager");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager = new Agent((String)comboBox.getEditor().getItem(), (String)comboBox_2.getEditor().getItem(), (String)comboBox_1.getEditor().getItem(), config.parameters, config.hiperParameters);
			}
		});
		btnReset.setBounds(10, 407, 102, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnStart = new JButton("Print");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.printResult();
			}
		});
		btnStart.setBounds(221, 407, 89, 23);
		frame.getContentPane().add(btnStart);
		
		JButton btnNewButton = new JButton("Train");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.OptimizeAgent();
			}
		});
		btnNewButton.setBounds(122, 407, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		final JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				config.parameters.put("epochs",slider.getValue());
				lblEpochs.setText("Epoch (" + String.valueOf(config.parameters.get("epochs"))+ ")");
			}
		});
		slider.setMaximum(1000);
		slider.setBounds(10, 87, 140, 20);
		frame.getContentPane().add(slider);
		
		final JSlider slider_1 = new JSlider();
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("discount_factor",(double)((double)(slider_1.getValue())/(1000)));
				lblDiscountFactor.setText("Discount Factor (" + String.valueOf(config.parameters.get("discount_factor"))+ ")");
			}
		});
		slider_1.setMaximum(1000);
		slider_1.setBounds(170, 87, 140, 20);
		frame.getContentPane().add(slider_1);
		
		final JSlider slider_2 = new JSlider();
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("exploring_rate",(double)((double)(slider_2.getValue())/(1000)));
				lblExploringRate.setText("Exploring Rate (" + String.valueOf(config.parameters.get("exploring_rate"))+ ")");
			}
		});
		slider_2.setMaximum(1000);
		slider_2.setBounds(10, 149, 140, 20);
		frame.getContentPane().add(slider_2);
		
		final JSlider slider_3 = new JSlider();
		slider_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("learning_rate",(double)((double)(slider_3.getValue())/(1000)));
				lblLearningRate.setText("Learning Rate (" + String.valueOf(config.parameters.get("learning_rate"))+ ")");
			}
		});
		slider_3.setMaximum(1000);
		slider_3.setBounds(170, 149, 140, 20);
		frame.getContentPane().add(slider_3);
		
		final JLabel lblRewardOnStep = new JLabel("Reward On Step");
		lblRewardOnStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblRewardOnStep.setBounds(10, 180, 140, 14);
		frame.getContentPane().add(lblRewardOnStep);
		
		final JSlider slider_4 = new JSlider();
		slider_4.setMaximum(1000);
		slider_4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				config.parameters.put("reward_on_step",(double)((double)(slider_4.getValue()-500)/(1000)));
				lblRewardOnStep.setText("Reward on Step (" + String.valueOf(config.parameters.get("reward_on_step"))+ ")");
			}
		});
		slider_4.setBounds(10, 205, 140, 20);
		frame.getContentPane().add(slider_4);
		
		JButton btnDialog = new JButton("Parameters");
		btnDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogParameters j = new dialogParameters();
				j.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				j.setVisible(true);
			}
		});
		btnDialog.setBounds(10, 373, 140, 23);
		frame.getContentPane().add(btnDialog);
		
		JButton btnHiperparameters = new JButton("Hiperparameters");
		btnHiperparameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogHiperParameters j = new dialogHiperParameters();
				j.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				j.setVisible(true);
			}
		});
		btnHiperparameters.setBounds(10, 338, 140, 23);
		frame.getContentPane().add(btnHiperparameters);
		
		
		
		
	}
}
