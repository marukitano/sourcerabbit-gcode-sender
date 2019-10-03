/*
 Copyright (C) 2015  Nikos Siatras

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 SourceRabbit GCode Sender is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sourcerabbit.gcode.sender.UI;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jssc.SerialPortList;
import sourcerabbit.gcode.sender.Core.CNCController.CNCControllFrameworks.CNCControlFramework;
import sourcerabbit.gcode.sender.Core.CNCController.CNCControllFrameworks.CNCControlFrameworkManager;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.ConnectionHandler;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.ConnectionHelper;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.Events.SerialConnectionEvents.ISerialConnectionEventListener;
import sourcerabbit.gcode.sender.Core.CNCController.Connection.Events.SerialConnectionEvents.SerialConnectionEvent;
import sourcerabbit.gcode.sender.Core.Threading.ManualResetEvent;
import sourcerabbit.gcode.sender.Core.CNCController.Position.Position2D;
import sourcerabbit.gcode.sender.Core.Settings.SettingsManager;
import sourcerabbit.gcode.sender.UI.UITools.UITools;

/**
 *
 * @author Nikos Siatras
 */
public class frmMain extends javax.swing.JFrame
{

    private ManualResetEvent fWaitToEstablishConnectionResetEvent = new ManualResetEvent(false);
    private final frmMain fInstance;

    public frmMain()
    {
        fInstance = this;
        initComponents();

        InitUI();

        // Set form in middle of screen
        Position2D pos = UITools.getPositionForFormToOpenInMiddleOfScreen(this.getSize().width, this.getSize().height);
        setLocation((int) pos.getX(), (int) pos.getY());

        setTitle("SourceRabbit GCode Sender (Version " + SettingsManager.getAppVersion() + ")");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Images/SourceRabbitIcon.png")));
    }

    private void InitUI()
    {
        String[] serialPorts = SerialPortList.getPortNames();
        ArrayList<CNCControlFramework> cncframeworks = CNCControlFrameworkManager.getCNCControlFrameworks();

        if (serialPorts.length < 1)
        {
        }
        else
        {
            String preselectedSerialPort = SettingsManager.getPreselectedSerialPort();

            // Add serial ports to jComboBoxPort
            int index = 0;
            int selectedIndex = 0;
            for (String port : serialPorts)
            {
                jComboBoxPort.addItem(port);
                if (port.equals(preselectedSerialPort))
                {
                    selectedIndex = index;
                }
                index += 1;
            }
            jComboBoxPort.setSelectedIndex(selectedIndex);

            // Add Baud rates
            jComboBoxBaud.addItem("115200");

            // Add Frameworks
            for (CNCControlFramework framework : cncframeworks)
            {
                jComboBoxFramework.addItem(framework.getName());
            }
            jComboBoxFramework.setSelectedIndex(0);
        }
    }

    /**
     * The SourceRabbit GCode Sender managed to connect successfully to the CNC
     * controller. Now the Control Form must be opened!
     */
    private void OpenControlForm()
    {
        // Remove the fConnectionEstablishedEventListener (We dont need it any more)
        ConnectionHelper.ACTIVE_CONNECTION_HANDLER.getSerialConnectionEventManager().RemoveListener(fConnectionEstablishedEventListener);

        // Set the selected port as the preselected port for later use
        String port = jComboBoxPort.getSelectedItem().toString();
        SettingsManager.setPreselectedSerialPort(port);

        // Show to control form
        frmControl frm = new frmControl();
        frm.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    private final ISerialConnectionEventListener fConnectionEstablishedEventListener = new ISerialConnectionEventListener()
    {
        @Override
        public void ConnectionEstablished(SerialConnectionEvent evt)
        {
            fWaitToEstablishConnectionResetEvent.Set();
            OpenControlForm();
        }

        @Override
        public void ConnectionClosed(SerialConnectionEvent evt)
        {

        }

        @Override
        public void DataReceivedFromSerialConnection(SerialConnectionEvent evt)
        {

        }
    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBoxPort = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxFramework = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxBaud = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7_close = new javax.swing.JLabel();
        jButtonConnect = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 75, 127));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Connect to your CNC");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SourceRabbit GCODE Sender ver 1.0");
        setBackground(new java.awt.Color(32, 33, 35));
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(32, 33, 35));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(32, 33, 35));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourcerabbit/gcode/sender/UI/Images/Logo_grbl_remote_400.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, -1, -1));

        jPanel1.setBackground(new java.awt.Color(32, 33, 35));

        jComboBoxPort.setBorder(null);

        jLabel2.setForeground(java.awt.Color.lightGray);
        jLabel2.setText("Port:");

        jLabel3.setForeground(java.awt.Color.lightGray);
        jLabel3.setText("Framework:");

        jComboBoxFramework.setBorder(null);
        jComboBoxFramework.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxFramework.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFrameworkActionPerformed(evt);
            }
        });

        jLabel4.setForeground(java.awt.Color.lightGray);
        jLabel4.setText("Baud:");

        jComboBoxBaud.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxFramework, 0, 156, Short.MAX_VALUE)
                    .addComponent(jComboBoxBaud, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxPort, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxBaud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxFramework, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourcerabbit/gcode/sender/UI/Images/Drehbank_500x750_dunkel.jpg"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 500));

        jLabel7_close.setBackground(new java.awt.Color(32, 33, 35));
        jLabel7_close.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7_close.setForeground(java.awt.Color.lightGray);
        jLabel7_close.setText("X");
        jLabel7_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7_closeMouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 27, 29));

        jButtonConnect.setBackground(new java.awt.Color(255, 0, 0));
        jButtonConnect.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonConnect.setForeground(java.awt.Color.lightGray);
        jButtonConnect.setText("Connect");
        jButtonConnect.setContentAreaFilled(false);
        jButtonConnect.setDefaultCapable(false);
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 462, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("frmMain");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonConnectActionPerformed
    {//GEN-HEADEREND:event_jButtonConnectActionPerformed

        if (ConnectionHelper.ACTIVE_CONNECTION_HANDLER != null)
        {
            try
            {
                ConnectionHelper.ACTIVE_CONNECTION_HANDLER.CloseConnection();
            }
            catch (Exception ex)
            {

            }
        }

        String frameworkName = jComboBoxFramework.getSelectedItem().toString();
        String baud = jComboBoxBaud.getSelectedItem().toString();
        String port = jComboBoxPort.getSelectedItem().toString();

        CNCControlFramework framework = CNCControlFrameworkManager.getFrameworkByName(frameworkName);
        final ConnectionHandler handler = framework.getHandler();
        ConnectionHelper.ACTIVE_CONNECTION_HANDLER = handler;

        // Add connection established listener
        handler.getSerialConnectionEventManager().RemoveListener(fConnectionEstablishedEventListener);
        handler.getSerialConnectionEventManager().AddListener(fConnectionEstablishedEventListener);

        try
        {
            jButtonConnect.setText("Connecting...");
            jButtonConnect.setEnabled(false);
            fWaitToEstablishConnectionResetEvent.Reset();
            if (handler.OpenConnection(port, Integer.parseInt(baud)))
            {
                // Wait for 3 seconds to establish connection
                // Otherwise disconnect (Close Connection)
                Thread th = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        fWaitToEstablishConnectionResetEvent.WaitOne(3000);

                        if (!handler.isConnectionEstablished())
                        {
                            try
                            {
                                handler.CloseConnection();
                            }
                            catch (Exception ex)
                            {

                            }

                            jButtonConnect.setText("Connect");
                            jButtonConnect.setEnabled(true);
                            JOptionPane.showMessageDialog(fInstance, "Unable to establish connection with the CNC Controller!\nPlease check if you are using the correct port.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                th.start();
            }
            else
            {
                handler.CloseConnection();
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(fInstance, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            jButtonConnect.setText("Connect");
            jButtonConnect.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonConnectActionPerformed

    private void jComboBoxFrameworkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxFrameworkActionPerformed
    {//GEN-HEADEREND:event_jComboBoxFrameworkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFrameworkActionPerformed

    private void jLabel7_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7_closeMouseClicked

    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {

        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JComboBox jComboBoxBaud;
    private javax.swing.JComboBox jComboBoxFramework;
    private javax.swing.JComboBox jComboBoxPort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7_close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
