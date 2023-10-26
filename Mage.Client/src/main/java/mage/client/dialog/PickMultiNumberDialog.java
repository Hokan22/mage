package mage.client.dialog;

import mage.constants.ColoredManaSymbol;
import mage.util.MultiAmountMessage;

import org.mage.card.arcane.ManaSymbols;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author weirddan455
 */
public class PickMultiNumberDialog extends MageDialog {

    private List<JLabel> labelList = null;
    private List<JSpinner> spinnerList = null;

    public PickMultiNumberDialog() {
        initComponents();
        this.setModal(true);
    }

    public void showDialog(List<MultiAmountMessage> messages, int min, int max, Map<String, Serializable> options) {
        this.header.setText((String) options.get("header"));
        this.header.setHorizontalAlignment(SwingConstants.CENTER);
        this.setTitle((String) options.get("title"));

        if (labelList != null) {
            for (JLabel label : labelList) {
                jPanel1.remove(label);
            }
        }
        if (spinnerList != null) {
            for (JSpinner spinner : spinnerList) {
                jPanel1.remove(spinner);
            }
        }
        int size = messages.size();
        labelList = new ArrayList<>(size);
        spinnerList = new ArrayList<>(size);
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints labelC = new GridBagConstraints();
        GridBagConstraints spinnerC = new GridBagConstraints();
        for (int i = 0; i < size; i++) {
            JLabel label = new JLabel();

            // mana mode
            String manaText = null;
            String input = messages.get(i).message;
            switch (input) {
                case "W":
                    manaText = ColoredManaSymbol.W.getColorHtmlName();
                    break;
                case "U":
                    manaText = ColoredManaSymbol.U.getColorHtmlName();
                    break;
                case "B":
                    manaText = ColoredManaSymbol.B.getColorHtmlName();
                    break;
                case "R":
                    manaText = ColoredManaSymbol.R.getColorHtmlName();
                    break;
                case "G":
                    manaText = ColoredManaSymbol.G.getColorHtmlName();
                    break;
            }
            if (manaText != null) {
                label.setText("<html>" + manaText);
                Image image = ManaSymbols.getSizedManaSymbol(input);
                if (image != null) {
                    label.setIcon(new ImageIcon(image));
                }
            } else {
                // text mode
                label.setText("<html>" + input);
            }

            labelC.weightx = 0.5;
            labelC.gridx = 0;
            labelC.gridy = i;
            jPanel1.add(label, labelC);
            labelList.add(label);

            JSpinner spinner = new JSpinner();
            spinner.setModel(new SpinnerNumberModel(messages.get(i).min, messages.get(i).min, messages.get(i).max, 1));
            spinnerC.weightx = 0.5;
            spinnerC.gridx = 1;
            spinnerC.gridy = i;
            spinnerC.ipadx = 20;
            spinner.addChangeListener(e -> {
                updateControls(min, max, messages);
            });
            jPanel1.add(spinner, spinnerC);
            spinnerList.add(spinner);
        }
        this.counterText.setText("0 out of 0");
        this.counterText.setHorizontalAlignment(SwingConstants.CENTER);

        updateControls(min, max, messages);

        this.pack();
        this.makeWindowCentered();
        this.setVisible(true);
    }

    private void updateControls(int min, int max, List<MultiAmountMessage> messages) {
        int totalChosenAmount = 0;
        boolean chooseEnabled = true;

        for (int i = 0; i < spinnerList.size(); i++) {
            JSpinner jSpinner = spinnerList.get(i);
            int value = ((Number) jSpinner.getValue()).intValue();
            totalChosenAmount += value;

            chooseEnabled &= value >= messages.get(i).min && value <= messages.get(i).max;
        }
        counterText.setText(totalChosenAmount + " out of " + max);

        chooseEnabled &= totalChosenAmount >= min && totalChosenAmount <= max;
        chooseButton.setEnabled(chooseEnabled);
    }

    public String getMultiAmount() {
        return spinnerList
                .stream()
                .map(spinner -> ((Number) spinner.getValue()).intValue())
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooseButton = new javax.swing.JButton();
        header = new javax.swing.JLabel();
        counterText = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        chooseButton.setText("Choose");
        chooseButton.setEnabled(false);
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        header.setText("Header");

        counterText.setText("Counter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(counterText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(chooseButton)
                        .addGap(0, 172, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(counterText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        this.hideDialog();
    }//GEN-LAST:event_chooseButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseButton;
    private javax.swing.JLabel counterText;
    private javax.swing.JLabel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
