package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.xml.stream.FactoryConfigurationError;

public class DemoWindow extends JFrame {


    private JPanel pnFramePanel;
    private JPanel pnCenterPanel;

    //client
    private JPanel pnClientPanel;
    private JLabel lbClientServerIDLabel;
    private JTextField tfClientServerIDTF;
    private JButton btClientGenTLabel;
    private JLabel lbClientTimeLable;
    private JTextField tfClientTimeTF;
    private JLabel lbClientDateLable;
    private JTextField tfClientDateTF;
    private JButton btClientEncryptB;
    private JLabel lbClientEncryptedLabel;
    private JTextField tfClientEncryptedTF;
    private JLabel lbClientMessageDataL;
    private JTextField tfClientAuthMessageTF;
    private JButton btClientSendButton;
    //server
    private JPanel pnServerPanel;
    private JLabel lbServerServerIDLabel;
    private JTextField tfServerServerIDTF;
    private JLabel lbReceivedData;
    private JTextField tfServerRecievedAuthDataTF;
    private JLabel lbReceivedTime;
    private JTextField tfServerRecievedTimeTF;
    private JButton btServerGenTLabel;
    private JLabel lbLabel13;
    private JTextField tfServerTimeTF;
    private JLabel lbLabelSD;
    private JTextField tfServerDateTF;
    private JButton btCompareTime;
    private JButton btServerDecryptB;
    private JLabel lbLabel16;
    private JTextField tfDecryptedTLTF;
    private JLabel lbLabel33;
    private JTextField tfDecryptedDateTF;
    private JLabel lbLabel17;
    private JTextField tfDecryptedServerIDTF;
    private JButton btCompareClientTL;
    private JButton btCompareDecryptedTL;
    private JButton btCompareServerID;
    //transmition
    private JPanel pnTransmtionPanel;
    private JLabel lbTransAuthDataLabel;
    private JTextField tfEncryptedDataTF;
    private JButton btRecByServer;
    //protocol
    private JPanel pnProtocolDataPanel;
    private JLabel lbEncryptionKeyLabel;
    private JTextField tfKeyTF;
    private JLabel lbTimeDifLabel;
    private JTextField tfTimeDifTF;
    private JMenuBar mainMenu;
    private JMenu fileMenu;
    private JMenuItem exitMenu;
    private JMenuItem aboutItem;
    private JMenu aboutMenu;

    private byte bytesTA[];
    private byte bytesTB[];

    private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SS dd/MM/YYYY");

    private DemoWindow _this;


    public DemoWindow()
    {
        super();
        _this = this;
        setSize(900,750);
        buildGui();
        add(pnFramePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startState();
        tfKeyTF.setText("012345678012345678012345678012345678012345678012345678012345678a");
        tfClientServerIDTF.setText("801234567801234567801234567801234567801234567801");
        tfTimeDifTF.setText("25000");
        listeners();
        setVisible(true);
    }


    private void buildGui()
    {

        fileMenu = new JMenu("File");
        exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);

        aboutMenu = new JMenu("About");
        aboutItem = new JMenuItem("About program");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(_this, "Demonstration of authentication protocol with crypto algorithm Rijdael with 256-bit key and using time labels.\n"+
                        "Petro Donchuk-Dontsov\n"+
                        "1SS-16b\n"+
                        "2019","About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutMenu.add(aboutItem);

        mainMenu = new JMenuBar();

        mainMenu.add(fileMenu);
        mainMenu.add(aboutMenu);
        setJMenuBar(mainMenu);


        pnFramePanel = new JPanel();
        pnFramePanel.setBorder( BorderFactory.createTitledBorder( "" ) );
        GridBagLayout gbFramePanel = new GridBagLayout();
        GridBagConstraints gbcFramePanel = new GridBagConstraints();
        //pnFramePanel.setLayout( gbFramePanel );
        pnFramePanel.setLayout(new BorderLayout());

        pnCenterPanel = new JPanel();
        pnCenterPanel.setLayout(new GridLayout(0,2));

        //client
        pnClientPanel = new JPanel();
        pnClientPanel.setBorder( BorderFactory.createTitledBorder( "Client" ) );
        GridBagLayout gbClientPanel = new GridBagLayout();
        GridBagConstraints gbcClientPanel = new GridBagConstraints();
        pnClientPanel.setLayout( gbClientPanel );



        lbClientServerIDLabel = new JLabel( "Server ID in HEX"  );
        lbClientServerIDLabel.setVerticalAlignment(JLabel.BOTTOM);
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 0;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.BOTH;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 1;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( lbClientServerIDLabel, gbcClientPanel );
        pnClientPanel.add( lbClientServerIDLabel );

        tfClientServerIDTF = new JTextField( );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 1;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( tfClientServerIDTF, gbcClientPanel );
        pnClientPanel.add( tfClientServerIDTF );

        btClientGenTLabel = new JButton( "Generate time label"  );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 2;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( btClientGenTLabel, gbcClientPanel );
        pnClientPanel.add( btClientGenTLabel );


        lbClientDateLable = new JLabel( "Generated time label"  );
        lbClientDateLable.setVerticalAlignment(JLabel.BOTTOM);
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 3;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.BOTH;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 1;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( lbClientDateLable, gbcClientPanel );
        pnClientPanel.add( lbClientDateLable );

        tfClientDateTF = new JTextField( );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 4;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( tfClientDateTF, gbcClientPanel );
        pnClientPanel.add( tfClientDateTF );

        lbClientTimeLable = new JLabel( "Generated time label in HEX"  );
        lbClientTimeLable.setVerticalAlignment(JLabel.BOTTOM);
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 5;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.BOTH;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 1;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( lbClientTimeLable, gbcClientPanel );
        pnClientPanel.add( lbClientTimeLable );

        tfClientTimeTF = new JTextField( );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 6;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( tfClientTimeTF, gbcClientPanel );
        pnClientPanel.add( tfClientTimeTF );

        btClientEncryptB = new JButton( "Encrypt all data"  );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 7;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( btClientEncryptB, gbcClientPanel );
        pnClientPanel.add( btClientEncryptB );
        gbcFramePanel.gridx = 0;
        gbcFramePanel.gridy = 5;
        gbcFramePanel.gridwidth = 10;
        gbcFramePanel.gridheight = 19;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.CENTER;
        gbFramePanel.setConstraints( pnClientPanel, gbcFramePanel );
        pnFramePanel.add( pnClientPanel );

        lbClientEncryptedLabel = new JLabel( "Encrypted data"  );
        lbClientEncryptedLabel.setVerticalAlignment(JLabel.BOTTOM);
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 8;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.BOTH;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 1;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( lbClientEncryptedLabel, gbcClientPanel );
        pnClientPanel.add( lbClientEncryptedLabel );

        tfClientEncryptedTF = new JTextField( );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 9;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( tfClientEncryptedTF, gbcClientPanel );
        pnClientPanel.add( tfClientEncryptedTF );

        lbClientMessageDataL = new JLabel( "Authentication data"  );
        lbClientMessageDataL.setVerticalAlignment(JLabel.BOTTOM);
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 10;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.BOTH;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 1;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( lbClientMessageDataL, gbcClientPanel );
        pnClientPanel.add( lbClientMessageDataL );

        tfClientAuthMessageTF = new JTextField( );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 11;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( tfClientAuthMessageTF, gbcClientPanel );
        pnClientPanel.add( tfClientAuthMessageTF );

        btClientSendButton = new JButton( "Send authentication data"  );
        gbcClientPanel.gridx = 0;
        gbcClientPanel.gridy = 12;
        gbcClientPanel.gridwidth = 1;
        gbcClientPanel.gridheight = 1;
        gbcClientPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcClientPanel.weightx = 1;
        gbcClientPanel.weighty = 0;
        gbcClientPanel.anchor = GridBagConstraints.CENTER;
        gbClientPanel.setConstraints( btClientSendButton, gbcClientPanel );
        pnClientPanel.add( btClientSendButton );
        gbcFramePanel.gridx = 0;
        gbcFramePanel.gridy = 5;
        gbcFramePanel.gridwidth = 10;
        gbcFramePanel.gridheight = 19;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.CENTER;
        gbFramePanel.setConstraints( pnClientPanel, gbcFramePanel );
        //pnFramePanel.add( pnClientPanel );
        pnCenterPanel.add( pnClientPanel );

        //server

        pnServerPanel = new JPanel();
        pnServerPanel.setBorder( BorderFactory.createTitledBorder( "Server" ) );
        GridBagLayout gbServerPanel = new GridBagLayout();
        GridBagConstraints gbcServerPanel = new GridBagConstraints();
        pnServerPanel.setLayout( gbServerPanel );

        lbServerServerIDLabel = new JLabel( "Server ID HEX"  );
        lbServerServerIDLabel.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 0;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbServerServerIDLabel, gbcServerPanel );
        pnServerPanel.add( lbServerServerIDLabel );

        tfServerServerIDTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 1;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfServerServerIDTF, gbcServerPanel );
        pnServerPanel.add( tfServerServerIDTF );

        lbReceivedData = new JLabel( "Received authentication data"  );
        lbReceivedData.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 2;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbReceivedData, gbcServerPanel );
        pnServerPanel.add( lbReceivedData );

        tfServerRecievedAuthDataTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 3;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfServerRecievedAuthDataTF, gbcServerPanel );
        pnServerPanel.add( tfServerRecievedAuthDataTF );

        btServerGenTLabel = new JButton( "Generate time label"  );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 4;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( btServerGenTLabel, gbcServerPanel );
        pnServerPanel.add( btServerGenTLabel );


        lbLabel13 = new JLabel( "Generated time label in HEX"  );
        lbLabel13.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 5;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbLabel13, gbcServerPanel );
        pnServerPanel.add( lbLabel13 );

        tfServerTimeTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 6;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfServerTimeTF, gbcServerPanel );
        pnServerPanel.add( tfServerTimeTF );

        lbLabelSD = new JLabel( "Generated time label"  );
        lbLabelSD.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 7;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbLabelSD, gbcServerPanel );
        pnServerPanel.add( lbLabelSD );

        tfServerDateTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 8;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfServerDateTF, gbcServerPanel );
        pnServerPanel.add( tfServerDateTF );

        gbcFramePanel.gridx = 10;
        gbcFramePanel.gridy = 5;
        gbcFramePanel.gridwidth = 10;
        gbcFramePanel.gridheight = 19;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.NORTH;
        gbFramePanel.setConstraints( pnServerPanel, gbcFramePanel );
        pnFramePanel.add( pnServerPanel );


        lbReceivedTime = new JLabel( "Received open time label"  );
        lbReceivedTime.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 9;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbReceivedTime, gbcServerPanel );
        pnServerPanel.add( lbReceivedTime );

        tfServerRecievedTimeTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 10;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfServerRecievedTimeTF, gbcServerPanel );
        pnServerPanel.add( tfServerRecievedTimeTF );

        btCompareTime = new JButton( "Compare open time labels"  );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 11;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( btCompareTime, gbcServerPanel );
        pnServerPanel.add( btCompareTime );

        btServerDecryptB = new JButton( "Decrypt data"  );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 12;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( btServerDecryptB, gbcServerPanel );
        pnServerPanel.add( btServerDecryptB );

        lbLabel33 = new JLabel( "Decrypted time label"  );
        lbLabel33.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 13;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbLabel33, gbcServerPanel );
        pnServerPanel.add( lbLabel33 );

        tfDecryptedDateTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 14;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfDecryptedDateTF, gbcServerPanel );
        pnServerPanel.add( tfDecryptedDateTF );


        lbLabel16 = new JLabel( "Decrypted time label in HEX"  );
        lbLabel16.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 15;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbLabel16, gbcServerPanel );
        pnServerPanel.add( lbLabel16 );

        tfDecryptedTLTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 16;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfDecryptedTLTF, gbcServerPanel );
        pnServerPanel.add( tfDecryptedTLTF );

        lbLabel17 = new JLabel( "Decrypted Server ID in HEX"  );
        lbLabel17.setVerticalAlignment(JLabel.BOTTOM);
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 17;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.BOTH;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 1;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( lbLabel17, gbcServerPanel );
        pnServerPanel.add( lbLabel17 );

        tfDecryptedServerIDTF = new JTextField( );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 18;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( tfDecryptedServerIDTF, gbcServerPanel );
        pnServerPanel.add( tfDecryptedServerIDTF );

        btCompareClientTL = new JButton( "Compare open and decrypted time labels"  );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 19;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( btCompareClientTL, gbcServerPanel );
        pnServerPanel.add( btCompareClientTL );

        btCompareDecryptedTL = new JButton( "Compare generated and decrypted time labels"  );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 20;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( btCompareDecryptedTL, gbcServerPanel );
        pnServerPanel.add( btCompareDecryptedTL );
        gbcFramePanel.gridx = 10;
        gbcFramePanel.gridy = 9;
        gbcFramePanel.gridwidth = 10;
        gbcFramePanel.gridheight = 19;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.NORTH;
        gbFramePanel.setConstraints( pnServerPanel, gbcFramePanel );
        pnFramePanel.add( pnServerPanel );

        btCompareServerID = new JButton( "Compare decrypted and own Server ID"  );
        gbcServerPanel.gridx = 0;
        gbcServerPanel.gridy = 21;
        gbcServerPanel.gridwidth = 1;
        gbcServerPanel.gridheight = 1;
        gbcServerPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcServerPanel.weightx = 1;
        gbcServerPanel.weighty = 0;
        gbcServerPanel.anchor = GridBagConstraints.NORTH;
        gbServerPanel.setConstraints( btCompareServerID, gbcServerPanel );
        pnServerPanel.add( btCompareServerID );
        gbcFramePanel.gridx = 10;
        gbcFramePanel.gridy = 9;
        gbcFramePanel.gridwidth = 10;
        gbcFramePanel.gridheight = 19;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.NORTH;
        gbFramePanel.setConstraints( pnServerPanel, gbcFramePanel );
        //pnFramePanel.add( pnServerPanel );
        pnCenterPanel.add( pnServerPanel );


        //protocol

        pnProtocolDataPanel = new JPanel();
        pnProtocolDataPanel.setBorder( BorderFactory.createTitledBorder( "Protocol data" ) );
        GridBagLayout gbProtocolDataPanel = new GridBagLayout();
        GridBagConstraints gbcProtocolDataPanel = new GridBagConstraints();
        pnProtocolDataPanel.setLayout( gbProtocolDataPanel );

        lbEncryptionKeyLabel = new JLabel( "Encryption secret key in HEX"  );
        gbcProtocolDataPanel.gridx = 0;
        gbcProtocolDataPanel.gridy = 0;
        gbcProtocolDataPanel.gridwidth = 1;
        gbcProtocolDataPanel.gridheight = 1;
        gbcProtocolDataPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcProtocolDataPanel.weightx = 1;
        gbcProtocolDataPanel.weighty = 1;
        gbcProtocolDataPanel.anchor = GridBagConstraints.NORTH;
        gbProtocolDataPanel.setConstraints( lbEncryptionKeyLabel, gbcProtocolDataPanel );
        pnProtocolDataPanel.add( lbEncryptionKeyLabel );

        tfKeyTF = new JTextField( );
        gbcProtocolDataPanel.gridx = 0;
        gbcProtocolDataPanel.gridy = 1;
        gbcProtocolDataPanel.gridwidth = 1;
        gbcProtocolDataPanel.gridheight = 1;
        gbcProtocolDataPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcProtocolDataPanel.weightx = 1;
        gbcProtocolDataPanel.weighty = 0;
        gbcProtocolDataPanel.anchor = GridBagConstraints.NORTH;
        gbProtocolDataPanel.setConstraints( tfKeyTF, gbcProtocolDataPanel );
        pnProtocolDataPanel.add( tfKeyTF );

        lbTimeDifLabel = new JLabel( "Max time labels difference"  );
        gbcProtocolDataPanel.gridx = 0;
        gbcProtocolDataPanel.gridy = 2;
        gbcProtocolDataPanel.gridwidth = 1;
        gbcProtocolDataPanel.gridheight = 1;
        gbcProtocolDataPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcProtocolDataPanel.weightx = 1;
        gbcProtocolDataPanel.weighty = 1;
        gbcProtocolDataPanel.anchor = GridBagConstraints.NORTH;
        gbProtocolDataPanel.setConstraints( lbTimeDifLabel, gbcProtocolDataPanel );
        pnProtocolDataPanel.add( lbTimeDifLabel );

        tfTimeDifTF = new JTextField( );
        gbcProtocolDataPanel.gridx = 0;
        gbcProtocolDataPanel.gridy = 3;
        gbcProtocolDataPanel.gridwidth = 1;
        gbcProtocolDataPanel.gridheight = 1;
        gbcProtocolDataPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcProtocolDataPanel.weightx = 1;
        gbcProtocolDataPanel.weighty = 0;
        gbcProtocolDataPanel.anchor = GridBagConstraints.NORTH;
        gbProtocolDataPanel.setConstraints( tfTimeDifTF, gbcProtocolDataPanel );
        pnProtocolDataPanel.add( tfTimeDifTF );
        gbcFramePanel.gridx = 0;
        gbcFramePanel.gridy = 0;
        gbcFramePanel.gridwidth = 20;
        gbcFramePanel.gridheight = 3;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.NORTH;
        gbFramePanel.setConstraints( pnProtocolDataPanel, gbcFramePanel );
        //pnFramePanel.add( pnProtocolDataPanel );
        pnFramePanel.add( pnProtocolDataPanel, BorderLayout.NORTH);


        //transmition
        pnTransmtionPanel = new JPanel();
        pnTransmtionPanel.setBorder( BorderFactory.createTitledBorder( "Transmition" ) );
        GridBagLayout gbTransmtionPanel = new GridBagLayout();
        GridBagConstraints gbcTransmtionPanel = new GridBagConstraints();
        pnTransmtionPanel.setLayout( gbTransmtionPanel );

        lbTransAuthDataLabel = new JLabel( "Authentication data"  );
        gbcTransmtionPanel.gridx = 0;
        gbcTransmtionPanel.gridy = 0;
        gbcTransmtionPanel.gridwidth = 1;
        gbcTransmtionPanel.gridheight = 1;
        gbcTransmtionPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcTransmtionPanel.weightx = 1;
        gbcTransmtionPanel.weighty = 0;
        gbcTransmtionPanel.anchor = GridBagConstraints.CENTER;
        gbTransmtionPanel.setConstraints( lbTransAuthDataLabel, gbcTransmtionPanel );
        pnTransmtionPanel.add( lbTransAuthDataLabel );

        tfEncryptedDataTF = new JTextField( );
        gbcTransmtionPanel.gridx = 0;
        gbcTransmtionPanel.gridy = 1;
        gbcTransmtionPanel.gridwidth = 1;
        gbcTransmtionPanel.gridheight = 1;
        gbcTransmtionPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcTransmtionPanel.weightx = 1;
        gbcTransmtionPanel.weighty = 0;
        gbcTransmtionPanel.anchor = GridBagConstraints.CENTER;
        gbTransmtionPanel.setConstraints( tfEncryptedDataTF, gbcTransmtionPanel );
        pnTransmtionPanel.add( tfEncryptedDataTF );

        btRecByServer = new JButton( "Send to Server"  );
        gbcTransmtionPanel.gridx = 0;
        gbcTransmtionPanel.gridy = 2;
        gbcTransmtionPanel.gridwidth = 1;
        gbcTransmtionPanel.gridheight = 1;
        gbcTransmtionPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcTransmtionPanel.weightx = 1;
        gbcTransmtionPanel.weighty = 0;
        gbcTransmtionPanel.anchor = GridBagConstraints.CENTER;
        gbTransmtionPanel.setConstraints( btRecByServer, gbcTransmtionPanel );
        pnTransmtionPanel.add( btRecByServer );
        gbcFramePanel.gridx = 0;
        gbcFramePanel.gridy = 22;
        gbcFramePanel.gridwidth = 20;
        gbcFramePanel.gridheight = 6;
        gbcFramePanel.fill = GridBagConstraints.BOTH;
        gbcFramePanel.weightx = 1;
        gbcFramePanel.weighty = 0;
        gbcFramePanel.anchor = GridBagConstraints.NORTH;
        gbFramePanel.setConstraints( pnTransmtionPanel, gbcFramePanel );
        //pnFramePanel.add( pnTransmtionPanel );
        pnFramePanel.add( pnTransmtionPanel, BorderLayout.SOUTH );

        pnFramePanel.add(pnCenterPanel, BorderLayout.CENTER);

    }

    private void startState()    {
        //client
        tfClientServerIDTF.setEnabled(true);
        tfClientServerIDTF.setEditable(true);
        tfClientServerIDTF.setText("");
        btClientGenTLabel.setEnabled(true);
        tfClientTimeTF.setEnabled(true);
        tfClientTimeTF.setEditable(false);
        tfClientTimeTF.setText("");
        tfClientDateTF.setEnabled(true);
        tfClientDateTF.setEditable(false);
        tfClientDateTF.setText("");
        btClientEncryptB.setEnabled(false);
        tfClientEncryptedTF.setEnabled(true);
        tfClientEncryptedTF.setEditable(false);
        tfClientEncryptedTF.setText("");
        tfClientAuthMessageTF.setEnabled(true);
        tfClientAuthMessageTF.setEditable(false);
        tfClientAuthMessageTF.setText("");
        btClientSendButton.setEnabled(false);
        //server
        tfServerServerIDTF.setEnabled(true);
        tfServerServerIDTF.setText("");
        tfServerRecievedTimeTF.setEnabled(true);
        tfServerRecievedTimeTF.setEditable(false);
        tfServerRecievedTimeTF.setText("");
        tfServerRecievedAuthDataTF.setEnabled(true);
        tfServerRecievedAuthDataTF.setEditable(false);
        tfServerRecievedAuthDataTF.setText("");
        btServerGenTLabel.setEnabled(false);
        tfServerTimeTF.setEnabled(true);
        tfServerTimeTF.setEditable(false);
        tfServerTimeTF.setText("");
        tfServerDateTF.setEnabled(true);
        tfServerDateTF.setEditable(false);
        tfServerDateTF.setText("");
        btCompareTime.setEnabled(false);
        btServerDecryptB.setEnabled(false);
        tfDecryptedTLTF.setEnabled(true);
        tfDecryptedTLTF.setEditable(false);
        tfDecryptedTLTF.setText("");
        tfDecryptedDateTF.setEnabled(true);
        tfDecryptedDateTF.setEditable(false);
        tfDecryptedDateTF.setText("");
        tfDecryptedServerIDTF.setEnabled(true);
        tfDecryptedServerIDTF.setEditable(false);
        tfDecryptedServerIDTF.setText("");
        btCompareClientTL.setEnabled(false);
        btCompareDecryptedTL.setEnabled(false);
        btCompareServerID.setEnabled(false);
        //transmition
        tfEncryptedDataTF.setEnabled(false);
        tfEncryptedDataTF.setText("");
        btRecByServer.setEnabled(false);
        //protocol
//        tfKeyTF.setEnabled(false);
//        tfTimeDifTF.setEnabled(false);
    }


    private int checkClientFields()
    {
        if (tfKeyTF.getText().isEmpty())
            return 1;
        if (tfClientServerIDTF.getText().isEmpty())
            return 2;
        if (Utils.hexRegex(tfKeyTF.getText()))
            return 11;
        if (Utils.hexRegex(tfClientServerIDTF.getText()))
            return 12;
        if (tfKeyTF.getText().length() < 64)
            return 21;
        if (tfKeyTF.getText().length() > 64)
            return 22;
        if (tfClientServerIDTF.getText().length() < 48)
            return 31;
        if (tfClientServerIDTF.getText().length() > 48)
            return 22;
        return 0;
    }

    private int checkServerFields()
    {
        if (tfKeyTF.getText().isEmpty())
            return 1;
        if (tfServerServerIDTF.getText().isEmpty())
            return 2;
        if (Utils.hexRegex(tfKeyTF.getText()))
            return 11;
        if (Utils.hexRegex(tfServerServerIDTF.getText()))
            return 12;
        if (tfKeyTF.getText().length() < 64)
            return 21;
        if (tfKeyTF.getText().length() > 64)
            return 22;
        if (tfServerServerIDTF.getText().length() < 48)
            return 31;
        if (tfServerServerIDTF.getText().length() > 48)
            return 22;
        return 0;
    }

    private void listeners()
    {
        btClientGenTLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentDate = new Date();
                bytesTA = Utils.longToByteArr(currentDate.getTime());
                tfClientDateTF.setText(df.format(currentDate));
                tfClientTimeTF.setText(Utils.byteArrToHex(bytesTA));
                btClientGenTLabel.setEnabled(false);
                btClientEncryptB.setEnabled(true);
            }
        });

        btClientEncryptB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (checkClientFields())
                {
                    case 1:
                        JOptionPane.showMessageDialog(_this, "Please, input encryption key","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 2:
                        JOptionPane.showMessageDialog(_this, "Please, input Server ID on client side","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 11:
                        JOptionPane.showMessageDialog(_this, "Encryption key has non-HEX symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 12:
                        JOptionPane.showMessageDialog(_this, "Server ID on client side has non-HEX symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 21:
                        JOptionPane.showMessageDialog(_this, "Encryption key has less than 64 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 22:
                        JOptionPane.showMessageDialog(_this, "Encryption key has more than 64 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 31:
                        JOptionPane.showMessageDialog(_this, "Server ID on client side has less than 48 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 32:
                        JOptionPane.showMessageDialog(_this, "Server ID on client side has more than 48 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                byte[] key = Utils.hexToByteArray(tfKeyTF.getText());
                byte[] t_ID = Utils.hexToByteArray(tfClientTimeTF.getText()+tfClientServerIDTF.getText());
                Rijndael rijndael = new Rijndael();
                byte[] encT_ID = rijndael.encryptMess(t_ID,key);
                tfClientEncryptedTF.setText(Utils.byteArrToHex(encT_ID));
                tfClientAuthMessageTF.setText(tfClientTimeTF.getText()+tfClientEncryptedTF.getText());
                btClientEncryptB.setEnabled(false);
                btClientSendButton.setEnabled(true);
                tfClientServerIDTF.setEditable(false);
                tfEncryptedDataTF.setEnabled(true);
            }
        });

        btClientSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btClientSendButton.setEnabled(false);
                tfEncryptedDataTF.setText(tfClientAuthMessageTF.getText());
                btRecByServer.setEnabled(true);
                tfEncryptedDataTF.setEditable(true);
            }
        });

        btRecByServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfEncryptedDataTF.getText().length() != 80 )
                {
                    JOptionPane.showMessageDialog(_this, "Length of message has been changed to " + tfEncryptedDataTF.getText().length() + " HEX-symbols (normal 80)","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Utils.hexRegex(tfEncryptedDataTF.getText()))
                {
                    JOptionPane.showMessageDialog(_this, "Sent message has non-HEX symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                btRecByServer.setEnabled(false);
                tfEncryptedDataTF.setEditable(false);
                btServerGenTLabel.setEnabled(true);
                tfServerRecievedAuthDataTF.setText(tfEncryptedDataTF.getText());
                tfServerRecievedTimeTF.setText(df.format(new Date(Utils.byteArrToLong(Utils.hexToByteArray(tfEncryptedDataTF.getText().substring(0,16))))));
            }
        });

        btServerGenTLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date currentDate = new Date();
                bytesTB = Utils.longToByteArr(currentDate.getTime());
                tfServerTimeTF.setText(Utils.byteArrToHex(bytesTB));
                btServerGenTLabel.setEnabled(false);
                btCompareTime.setEnabled(true);
                tfServerDateTF.setText(df.format(currentDate));
            }
        });

        btCompareTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = tfTimeDifTF.getText();
                if (time.isEmpty())
                {
                    JOptionPane.showMessageDialog(_this, "Please, set maximum time difference","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Utils.timeRegex(time))
                {
                    JOptionPane.showMessageDialog(_this, "Time difference field has non number symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long maxDT;

                try
                {
                    maxDT = Long.parseLong(time);
                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(_this, "Time difference is too large","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long dif = Utils.byteArrToLong(bytesTB) - Utils.byteArrToLong(bytesTA);

                if (dif > maxDT)
                {
                    JOptionPane.showMessageDialog(_this, "Authentication failed : open time timeout","Authentication failed", JOptionPane.ERROR_MESSAGE);
                    startState();
                    return;
                }

                JOptionPane.showMessageDialog(_this, "Time difference for open time is lesser then maximum","Authentication", JOptionPane.INFORMATION_MESSAGE);

                btCompareTime.setEnabled(false);
                btServerDecryptB.setEnabled(true);

            }
        });

        btServerDecryptB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (checkServerFields())
                {
                    case 1:
                        JOptionPane.showMessageDialog(_this, "Please, input encryption key","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 11:
                        JOptionPane.showMessageDialog(_this, "Encryption key has non-HEX symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 21:
                        JOptionPane.showMessageDialog(_this, "Encryption key has less than 64 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 22:
                        JOptionPane.showMessageDialog(_this, "Encryption key has more than 64 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                }

                Rijndael rijndael = new Rijndael();

                byte[] key = Utils.hexToByteArray(tfKeyTF.getText());
                byte[] authData = Utils.hexToByteArray(tfServerRecievedAuthDataTF.getText());
                byte[] message = Utils.subArray(authData, 8, authData.length);
                byte[] decrypted = rijndael.decryptMess(message, key);
                tfDecryptedTLTF.setText(Utils.byteArrToHex(Utils.subArray(decrypted,0,8)));
                tfDecryptedServerIDTF.setText(Utils.byteArrToHex(Utils.subArray(decrypted,8,decrypted.length)));
                tfDecryptedDateTF.setText(df.format(new Date(Utils. byteArrToLong(Utils.hexToByteArray(tfDecryptedTLTF.getText())))));

                btServerDecryptB.setEnabled(false);
                btCompareClientTL.setEnabled(true);
            }
        });

        btCompareClientTL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfDecryptedTLTF.getText().contentEquals(tfServerRecievedAuthDataTF.getText().substring(0,16)))
                {
                    JOptionPane.showMessageDialog(_this, "Authentication failed : open time label and decrypted time label mismatch","Authentication failed", JOptionPane.ERROR_MESSAGE);
                    startState();
                    return;
                }
                JOptionPane.showMessageDialog(_this, "Open time label equals decrypted time label","Authentication", JOptionPane.INFORMATION_MESSAGE);
                btCompareClientTL.setEnabled(false);
                btCompareDecryptedTL.setEnabled(true);
            }
        });

        btCompareDecryptedTL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = tfTimeDifTF.getText();
                if (time.isEmpty())
                {
                    JOptionPane.showMessageDialog(_this, "Please, set maximum time difference","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Utils.timeRegex(time))
                {
                    JOptionPane.showMessageDialog(_this, "Time difference field has non number symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long maxDT;

                try
                {
                    maxDT = Long.parseLong(time);
                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(_this, "Time difference is too large","Incorrect data", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long dif = Utils.byteArrToLong(bytesTB) - Utils.byteArrToLong(bytesTA);

                if (dif > maxDT)
                {
                    JOptionPane.showMessageDialog(_this, "Authentication failed : decrypted time timeout","Authentication failed", JOptionPane.ERROR_MESSAGE);
                    startState();
                    return;
                }

                JOptionPane.showMessageDialog(_this, "Time difference for decrypted time label is lesser then maximum","Authentication", JOptionPane.INFORMATION_MESSAGE);

                btCompareDecryptedTL.setEnabled(false);
                btCompareServerID.setEnabled(true);
            }
        });

        btCompareServerID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (checkServerFields())
                {
                    case 2:
                        JOptionPane.showMessageDialog(_this, "Please, input Server ID on server side","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 12:
                        JOptionPane.showMessageDialog(_this, "Server ID on server side has non-HEX symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 31:
                        JOptionPane.showMessageDialog(_this, "Server ID on server side has less than 56 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                    case 32:
                        JOptionPane.showMessageDialog(_this, "Server ID on server side has more than 56 HEX-symbols","Incorrect data", JOptionPane.ERROR_MESSAGE);
                        return;
                }

                if (!tfServerServerIDTF.getText().contentEquals(tfDecryptedServerIDTF.getText()))
                {
                    JOptionPane.showMessageDialog(_this, "Authentication failed : Server ID mismatch","Authentication failed", JOptionPane.ERROR_MESSAGE);
                }else
                {
                    JOptionPane.showMessageDialog(_this, "Authentication success","Authentication", JOptionPane.INFORMATION_MESSAGE);
                }
                startState();
            }
        });

    }


}
