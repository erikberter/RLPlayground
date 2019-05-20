package com.rlgym.RLPlaygrounds.visual.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
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

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.configuration.config;
import com.rlgym.RLPlaygrounds.enviroment.games.GameName;

import javax.swing.event.ChangeEvent;

public class dialogParameters extends JDialog {

	/**
	 * Create the dialog.
	 */
	
	// TODO Cambiar las funciones de las barras a la que esta en config para evitar errores
	JSlider slider,sldEpoch,sldDiscFactor,sldLearningRate;
	JLabel lblEpochs, lblDiscountFactor,lblLearningRate,lblExplorationDiscount;
	public dialogParameters() {
		setTitle("Parameter Configuration");
		setResizable(false);
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
		sldDiscFactor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("discount_factor",sldDiscFactor.getValue());
				lblDiscountFactor.setText("Discount Factor (" + String.valueOf(config.parameters.get("discount_factor"))+ ")");
			}
		});
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
		sldLearningRate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.parameters.put("learning_rate",sldLearningRate.getValue());
				lblDiscountFactor.setText("Learning Rate (" + String.valueOf(config.parameters.get("learning_rate"))+ ")");
			}
		});
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
		comboBox.setModel(new DefaultComboBoxModel(new explorationFunction[] {
				explorationFunction.CONSTANT,explorationFunction.CONSTANT_DECAY,
				explorationFunction.EXPONENTIAL_DECAY, explorationFunction.LINEAR_DECAY_WITH_MINIMUM }));
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
