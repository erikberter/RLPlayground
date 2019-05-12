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

public class dialogParameters extends JDialog {

	/**
	 * Create the dialog.
	 */
	
	JSlider slider,sldEpoch,sldDiscFactor,sldLearningRate;
	JLabel lblEpochs, lblDiscountFactor,lblLearningRate,lblExplorationDiscount;
	public dialogParameters() {
		setBounds(100, 100, 522, 394);
		getContentPane().setLayout(null);
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBounds(0, 322, 506, 33);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPanel);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPanel.add(cancelButton);
			}
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 506, 322);
		getContentPane().add(tabbedPane);
		
		JPanel parGeneralPan = new JPanel();
		tabbedPane.addTab("General", null, parGeneralPan, null);
		parGeneralPan.setLayout(null);
		
		lblEpochs = new JLabel("Epochs");
		lblEpochs.setHorizontalAlignment(SwingConstants.CENTER);
		lblEpochs.setBounds(10, 11, 200, 14);
		parGeneralPan.add(lblEpochs);
		
		sldEpoch = new JSlider();
		sldEpoch.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("epochs",sldEpoch.getValue());
				lblEpochs.setText("Epoch (" + String.valueOf(config.parameters.get("epochs"))+ ")");
			}
		});
		sldEpoch.setBounds(10, 30, 200, 26);
		parGeneralPan.add(sldEpoch);
		
		sldDiscFactor = new JSlider();
		sldDiscFactor.setBounds(291, 30, 200, 26);
		parGeneralPan.add(sldDiscFactor);
		
		lblDiscountFactor = new JLabel("Discount Factor");
		lblDiscountFactor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscountFactor.setBounds(291, 11, 200, 14);
		parGeneralPan.add(lblDiscountFactor);
		
		lblLearningRate = new JLabel("Learning Rate");
		lblLearningRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLearningRate.setBounds(291, 67, 200, 14);
		parGeneralPan.add(lblLearningRate);
		
		sldLearningRate = new JSlider();
		sldLearningRate.setBounds(291, 92, 200, 26);
		parGeneralPan.add(sldLearningRate);
		
		JPanel parExplorePanel = new JPanel();
		tabbedPane.addTab("Explore", null, parExplorePanel, null);
		parExplorePanel.setLayout(null);
		
		lblExplorationDiscount = new JLabel("Exploration Rate");
		lblExplorationDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplorationDiscount.setBounds(10, 11, 200, 14);
		parExplorePanel.add(lblExplorationDiscount);
		
		slider = new JSlider();
		slider.setBounds(10, 36, 200, 26);
		parExplorePanel.add(slider);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 98, 200, 20);
		parExplorePanel.add(comboBox);
		
		JLabel lblExploringDecayFunction = new JLabel("Exploring Decay Function");
		lblExploringDecayFunction.setBounds(10, 73, 200, 14);
		parExplorePanel.add(lblExploringDecayFunction);
		
		JPanel parRewardPanel = new JPanel();
		tabbedPane.addTab("Rewards", null, parRewardPanel, null);
		parRewardPanel.setLayout(null);
		
		JLabel lblRewardOnStep = new JLabel("Reward on Step");
		lblRewardOnStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblRewardOnStep.setBounds(10, 11, 200, 14);
		parRewardPanel.add(lblRewardOnStep);
		
		JSlider sldRewardpStep = new JSlider();
		sldRewardpStep.setBounds(10, 36, 200, 26);
		parRewardPanel.add(sldRewardpStep);
	}
}
