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

public class dialogHiperParameters extends JDialog {

	/**
	 * Create the dialog.
	 */
	public dialogHiperParameters() {
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
		
		final JLabel lblLearningRate = new JLabel("Learning Rate");
		lblLearningRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLearningRate.setBounds(10, 11, 200, 14);
		panel_2.add(lblLearningRate);
		
		final JSlider slider_2 = new JSlider();
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.hiperParameters.put("learning_rate",(double)((double)(slider_2.getValue())/(1000)));
				lblLearningRate.setText("Discount Factor (" + String.valueOf(config.hiperParameters.get("learning_rate"))+ ")");
				
			}
		});
		slider_2.setBounds(10, 30, 200, 26);
		panel_2.add(slider_2);
		

		final JLabel lblSeed = new JLabel("Seed");
		lblSeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeed.setBounds(291, 11, 200, 14);
		panel_2.add(lblSeed);
		
		final JSlider slider_3 = new JSlider();
		slider_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				config.hiperParameters.put("seed",(int)(slider_3.getValue()));
				lblSeed.setText("Seed (" + String.valueOf(config.hiperParameters.get("seed"))+ ")");
				
			}
		});
		slider_3.setBounds(291, 30, 200, 26);
		panel_2.add(slider_3);
		
		
		JLabel lblExploringRate = new JLabel("Exploring Rate");
		lblExploringRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblExploringRate.setBounds(10, 67, 200, 14);
		panel_2.add(lblExploringRate);
		
		JSlider slider_4 = new JSlider();
		slider_4.setBounds(10, 92, 200, 26);
		panel_2.add(slider_4);
		
		JLabel lblCustom = new JLabel("Learning Rate");
		lblCustom.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustom.setBounds(291, 67, 200, 14);
		panel_2.add(lblCustom);
		
		JSlider slider_5 = new JSlider();
		slider_5.setBounds(291, 92, 200, 26);
		panel_2.add(slider_5);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Explore", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblExplorationDiscount = new JLabel("Exploration Discount");
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
	}
}
