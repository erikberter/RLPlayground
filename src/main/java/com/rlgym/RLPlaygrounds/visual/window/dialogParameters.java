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

public class dialogParameters extends JDialog {

	/**
	 * Create the dialog.
	 */
	public dialogParameters() {
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
		
		JLabel lblNewLabel = new JLabel("Epochs");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 200, 14);
		panel_2.add(lblNewLabel);
		
		JSlider slider_2 = new JSlider();
		slider_2.setBounds(10, 30, 200, 26);
		panel_2.add(slider_2);
		
		JSlider slider_3 = new JSlider();
		slider_3.setBounds(291, 30, 200, 26);
		panel_2.add(slider_3);
		
		JLabel lblDiscountFactor = new JLabel("Discount Factor");
		lblDiscountFactor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscountFactor.setBounds(291, 11, 200, 14);
		panel_2.add(lblDiscountFactor);
		
		JLabel lblExploringRate = new JLabel("Exploring Rate");
		lblExploringRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblExploringRate.setBounds(10, 67, 200, 14);
		panel_2.add(lblExploringRate);
		
		JSlider slider_4 = new JSlider();
		slider_4.setBounds(10, 92, 200, 26);
		panel_2.add(slider_4);
		
		JLabel lblLearningRate = new JLabel("Learning Rate");
		lblLearningRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLearningRate.setBounds(291, 67, 200, 14);
		panel_2.add(lblLearningRate);
		
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
