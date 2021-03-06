package com.rlgym.RLPlaygrounds.visual.window;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import com.rlgym.RLPlaygrounds.algorithms.miscelanea.helpers;
import com.rlgym.RLPlaygrounds.configuration.config;

import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class dialogHiperParametersDQN extends JDialog {
	private JTextField tfSeed;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	JLabel lblUpdaterRate, lblMinibatch,lblHeight, lblChannels, lblSeed, 
		lblTrainingMethod, lblRewardOnStep, lblWidth;
	JSlider updateRateSLD, RewardOnStepSLD, MinibatchSLd;
		
	/**
	 * Create the dialog.
	 */
	public dialogHiperParametersDQN() {
		setTitle("hiperParameters Configuration");
		setResizable(false);
		setBounds(100, 100, 522, 394);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 322, 506, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 506, 322);
		getContentPane().add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("General", null, panel_2, null);
		panel_2.setLayout(null);
		

		lblSeed = new JLabel("Seed");
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
		
		lblTrainingMethod = new JLabel("Training Method");
		lblTrainingMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainingMethod.setBounds(10, 11, 200, 14);
		panel_2.add(lblTrainingMethod);
		
		lblUpdaterRate = new JLabel("Updater Rate");
		lblUpdaterRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdaterRate.setBounds(291, 61, 200, 14);
		panel_2.add(lblUpdaterRate);
		
		updateRateSLD = new JSlider();
		updateRateSLD.setValue(-1);
		updateRateSLD.setMinimum(-2);
		updateRateSLD.setMaximum(4);
		updateRateSLD.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("update_rate",Math.pow(10,-updateRateSLD.getValue() ));
				lblUpdaterRate.setText("Update Rate (" + String.valueOf(helpers.getDFMap(config.parameters,"update_rate"))+ ")");
			}
		});
		updateRateSLD.setBounds(291, 86, 200, 26);
		panel_2.add(updateRateSLD);
		
		JLabel lblNotYetImplemented = new JLabel("Not yet implemented");
		lblNotYetImplemented.setBounds(10, 86, 200, 14);
		panel_2.add(lblNotYetImplemented);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Batches", null, panel, null);
		panel.setLayout(null);
		
		lblMinibatch = new JLabel("Minibatch");
		lblMinibatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinibatch.setBounds(10, 11, 200, 14);
		panel.add(lblMinibatch);
		
		MinibatchSLd = new JSlider();
		MinibatchSLd.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.hiperParameters.put("minibatch",MinibatchSLd.getValue());
				lblMinibatch.setText("Minibatch (" + String.valueOf(config.hiperParameters.get("minibatch"))+ ")");
			}
		});
		MinibatchSLd.setBounds(10, 36, 200, 26);
		panel.add(MinibatchSLd);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Rewards", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblRewardOnStep = new JLabel("Reward on Step");
		lblRewardOnStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblRewardOnStep.setBounds(10, 11, 200, 14);
		panel_1.add(lblRewardOnStep);
		
		RewardOnStepSLD = new JSlider();
		RewardOnStepSLD.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.hiperParameters.put("reward_on_step",RewardOnStepSLD.getValue());
				lblRewardOnStep.setText("Reward on Step (" + String.valueOf(config.hiperParameters.get("reward_on_step"))+ ")");
			}
		});
		RewardOnStepSLD.setBounds(10, 36, 200, 26);
		panel_1.add(RewardOnStepSLD);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Screen", null, panel_3, null);
		panel_3.setLayout(null);
		
		lblWidth = new JLabel("Width");
		lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
		lblWidth.setBounds(10, 11, 86, 14);
		panel_3.add(lblWidth);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 86, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		lblHeight = new JLabel("Height");
		lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeight.setBounds(106, 11, 86, 14);
		panel_3.add(lblHeight);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 36, 86, 20);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		lblChannels = new JLabel("Channels");
		lblChannels.setHorizontalAlignment(SwingConstants.CENTER);
		lblChannels.setBounds(202, 11, 86, 14);
		panel_3.add(lblChannels);
		
		textField_2 = new JTextField();
		textField_2.setBounds(202, 36, 86, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNotYetImplemened = new JLabel("Not yet implemened");
		lblNotYetImplemened.setBounds(10, 67, 278, 14);
		panel_3.add(lblNotYetImplemened);
	}
}
