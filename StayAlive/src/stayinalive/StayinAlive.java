/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stayinalive;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jscheleder
 */
public class StayinAlive {

    public static int TEMPO = 30000;
    public static int Y = 400;
    public static int X = 400;
    public static boolean ALT = true;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //adicionando TrayIcon.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                mouseMove();
            }
        });
    }

    private static void createAndShowGUI() {
        //Checa se o SO suporta SystemTray 
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray não suportado!");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("image/bulb.gif", "Stay Alive"));
        final SystemTray tray = SystemTray.getSystemTray();
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Stayin Alive");

        // popup menu
        MenuItem aboutItem = new MenuItem("Sobre");
        CheckboxMenuItem cb3 = new CheckboxMenuItem("10 Segundos");
        CheckboxMenuItem cb4 = new CheckboxMenuItem("20 Segundos");
        CheckboxMenuItem cb5 = new CheckboxMenuItem("30 Segundos");
        CheckboxMenuItem cb6 = new CheckboxMenuItem("60 Segundos");
        CheckboxMenuItem altY = new CheckboxMenuItem("Sim");
        CheckboxMenuItem altN = new CheckboxMenuItem("Não");
        Menu alternaMenu = new Menu("Alternar");
        Menu displayMenu = new Menu("Intervalo");
        MenuItem exitItem = new MenuItem("Sair");

        //Add menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(alternaMenu);
        alternaMenu.add(altY);
        alternaMenu.add(altN);
        popup.add(displayMenu);
        displayMenu.add(cb3);
        displayMenu.add(cb4);
        displayMenu.add(cb5);
        displayMenu.add(cb6);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Tray Icon não pode ser adicionado.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Stayin Alive, bloqueia o bloqueio!");
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage();
                JOptionPane.showMessageDialog(null,
                        "v20200922 - Designed by scheleder");

            }

            private void showMessage() {
                trayIcon.displayMessage("Stayin Alive",
                        "Bloqueia o bloqueio!", TrayIcon.MessageType.INFO);
            }
        });

        cb3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cb3.getState() == true) {
                    int cb3Id = e.getStateChange();
                    if (cb3Id == ItemEvent.SELECTED) {
                        TEMPO = 10000;
                        System.err.println("TEMPO: " + TEMPO);
                        trayIcon.displayMessage("Stayin Alive",
                                "Intervalo: 10 segundos", TrayIcon.MessageType.NONE);
                        cb4.setState(false);
                        cb5.setState(false);
                        cb6.setState(false);
                    }
                } else {
                    cb3.setState(true);
                }
            }
        });

        cb4.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cb4.getState() == true) {
                    int cb4Id = e.getStateChange();
                    if (cb4Id == ItemEvent.SELECTED) {
                        TEMPO = 20000;
                        System.err.println("TEMPO: " + TEMPO);
                        trayIcon.displayMessage("Stayin Alive",
                                "Intervalo: 20 segundos", TrayIcon.MessageType.NONE);
                        cb3.setState(false);
                        cb5.setState(false);
                        cb6.setState(false);
                    }
                } else {
                    cb4.setState(true);
                }
            }
        });

        cb5.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cb5.getState() == true) {
                    int cb5Id = e.getStateChange();
                    if (cb5Id == ItemEvent.SELECTED) {
                        TEMPO = 30000;
                        System.err.println("TEMPO: " + TEMPO);
                        trayIcon.displayMessage("Stayin Alive",
                                "Intervalo: 30 segundos", TrayIcon.MessageType.NONE);
                        cb3.setState(false);
                        cb4.setState(false);

                        cb6.setState(false);
                    }
                } else {
                    cb5.setState(true);
                }
            }
        });

        cb6.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cb6.getState() == true) {
                    int cb6Id = e.getStateChange();
                    if (cb6Id == ItemEvent.SELECTED) {
                        TEMPO = 60000;
                        System.err.println("TEMPO: " + TEMPO);
                        trayIcon.displayMessage("Stayin Alive",
                                "Intervalo: 60 Segundos", TrayIcon.MessageType.NONE);
                        cb3.setState(false);
                        cb4.setState(false);
                        cb5.setState(false);

                    }
                } else {
                    cb6.setState(true);
                }
            }
        });
        
        altY.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (altY.getState() == true) {
                    int altYId = e.getStateChange();
                    if (altYId == ItemEvent.SELECTED) {
                        ALT = true;
                        System.err.println("Alterna: " + ALT);
                        trayIcon.displayMessage("Stayin Alive",
                                "Alterna habilitado", TrayIcon.MessageType.NONE);
                        altN.setState(false);
                    }
                } else {
                    altY.setState(true);
                }
            }
        });
        
        altN.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (altN.getState() == true) {
                    int altNId = e.getStateChange();
                    if (altNId == ItemEvent.SELECTED) {
                        ALT = false;
                        System.err.println("Alterna: " + ALT);
                        trayIcon.displayMessage("Stayin Alive",
                                "Alterna desabilitado", TrayIcon.MessageType.NONE);
                        altY.setState(false);
                    }
                } else {
                    altN.setState(true);
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        
        altY.setState(true);
        cb5.setState(true);
        trayIcon.displayMessage("Stayin Alive",
                "Bloqueia o bloqueio!", TrayIcon.MessageType.INFO);
        
    }

    
    //Caminho da imagem
    protected static Image createImage(String path, String description) {
        URL imageURL = StayinAlive.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Não encontrado: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    
    private static void mouseMove() {
        new Thread() {
            @Override
            public void run() {
                Robot robot;
                try {
                    robot = new Robot();

                    while (true) {
                        X = MouseInfo.getPointerInfo().getLocation().x;
                        Y = MouseInfo.getPointerInfo().getLocation().y;
                        if (X > 1360) {
                            X = 1;
                        }
                        robot.mouseMove(X + 1, Y);
                        if(ALT){                        
                        robot.keyPress(KeyEvent.VK_ALT);
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_END);
                        robot.delay(100);
                        robot.keyRelease(KeyEvent.VK_TAB);                     
                        robot.keyRelease(KeyEvent.VK_ALT);
                        robot.keyRelease(KeyEvent.VK_END);
                        //Runtime.getRuntime.exec("c:\alttab.bat");
                        }
                        Thread.sleep(TEMPO);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(StayinAlive.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.start();
    }
   
}
