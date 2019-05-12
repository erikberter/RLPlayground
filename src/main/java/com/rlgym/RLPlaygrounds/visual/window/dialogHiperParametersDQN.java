package com.rlgym.RLPlaygrounds.visual.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import com.rlgym.RLPlaygrounds.configuration.config;

import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;

public class dialogHiperParametersDQN extends JDialog {
	private JTextField tfSeed;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the dialog.
	 */
	public dialogHiperParametersDQN() {
		setBounds(100, 100, 522, 394);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 322, 506, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 506, 322);
		getContentPane().add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("General", null, panel_2, null);
		panel_2.setLayout(null);
		

		final JLabel lblSeed = new JLabel("Seed");
		lblSeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeed.setBounds(291, 11, 200, 14);
		panel_2.add(lblSeed);
		
		tfSeed = new JTextField();
		tfSeed.setBounds(291, 30, 200, 20);
		panel_2.add(tfSeed);
		tfSeed.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Train Epochs");
		rdbtnNewRadioButton.setBounds(10, 56, 200, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtTrainML = new JRadioButton("Minimize Loss");
		rdbtTrainML.setBounds(10, 30, 200, 23);
		panel_2.add(rdbtTrainML);
		
		JLabel lblTrainingMethod = new JLabel("Training Method");
		lblTrainingMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainingMethod.setBounds(10, 11, 200, 14);
		panel_2.add(lblTrainingMethod);
		
		JLabel lblUpdater = new JLabel("Updater");
		lblUpdater.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdater.setBounds(291, 56, 200, 14);
		panel_2.add(lblUpdater);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(291, 81, 200, 20);
		panel_2.add(comboBox);
		
		JLabel lblUpdaterRate = new JLabel("Updater Rate");
		lblUpdaterRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdaterRate.setBounds(291, 112, 200, 14);
		panel_2.add(lblUpdaterRate);
		
		JSlider slider_2 = new JSlider();
		slider_2.setBounds(291, 137, 200, 26);
		panel_2.add(slider_2);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Batches", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblExplorationDiscount = new JLabel("Minibatch");
		lblExplorationDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplorationDiscount.setBounds(10, 11, 200, 14);
		panel.add(lblExplorationDiscount);
		
		JSlider slider = new JSlider();
		slider.setBounds(10, 36, 200, 26);
		panel.add(slider);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Rewards", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblRewardOnStep = new JLabel("Reward on Step");
		lblRewardOnStep.setBounds(10, 11, 200, 14);
		panel_1.add(lblRewardOnStep);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBounds(10, 36, 200, 26);
		panel_1.add(slider_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Screen", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
		lblWidth.setBounds(10, 11, 86, 14);
		panel_3.add(lblWidth);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 86, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeight.setBounds(106, 11, 86, 14);
		panel_3.add(lblHeight);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 36, 86, 20);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblChannels = new JLabel("Channels");
		lblChannels.setHorizontalAlignment(SwingConstants.CENTER);
		lblChannels.setBounds(202, 11, 86, 14);
		panel_3.add(lblChannels);
		
		textField_2 = new JTextField();
		textField_2.setBounds(202, 36, 86, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
	}
}
