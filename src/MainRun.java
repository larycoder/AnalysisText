package bin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import bin.*;

public class MainRun{
    protected GUI GUI = new GUI();
    protected JsonReadData Data;

    private JButton positive , negative , unknow , start;

    protected JTextArea Word;
    protected JTextArea Log;

    protected JTextField PathText;

    private String ConfigPath = "Config.txt";
    private String BufferPathText = "";
    private String BufferID = ""; // Use to store index of element already parsed 

    public MainRun() throws Exception{
        //config path
        getConfig();

        positive = GUI.getPositive();
        negative = GUI.getNegative();
        unknow = GUI.getUnknow();
        start = GUI.getStart();

        Word = GUI.getWord();
        Log = GUI.getLog();

        PathText = GUI.getTextPath();
        PathText.setText(BufferPathText);

        positive.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Data == null) { Log.setText(null); Log.append("File not exit !"); }
                else{
                    try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Positive.txt",true));
                        bw.write("<text> "+Word.getText()+" <!text>");
                        bw.newLine();
                        bw.flush();
                        int ID=1+Integer.parseInt(BufferID);
                        BufferID=Integer.toString(ID);
                        Word.setText(Data.getString(ID));
                        Log.setText("["+BufferID+"/"+Data.getLength()+"]"+"\nOK");

                        //Update config file
                        FileWriter fw = new FileWriter("Config.txt");
                        fw.write("Path="+PathText.getText()+"\nIDTx="+BufferID);
                        fw.flush();
                        fw.close();
                    } catch(Exception E){ 
                        E.printStackTrace(); 
                    }
                }
            }
        });

        negative.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Data == null) { Log.setText(null); Log.append("File not exit !"); }
                else{
                    try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Negative.txt",true));
                        bw.write("<text> "+Word.getText()+" <!text>");
                        bw.newLine();
                        bw.flush();
                        int ID=1+Integer.parseInt(BufferID);
                        BufferID=Integer.toString(ID);
                        Word.setText(Data.getString(ID));
                        Log.setText("["+BufferID+"/"+Data.getLength()+"]"+"\nOK");

                        //Update config file
                        FileWriter fw = new FileWriter("Config.txt");
                        fw.write("Path="+PathText.getText()+"\nIDTx="+BufferID);
                        fw.flush();
                        fw.close();
                    } catch(Exception E){ 
                        E.printStackTrace(); 
                    }
                }
            }
        });

        unknow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Data == null) { Log.setText(null); Log.append("File not exit !"); }
                else{
                    try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Unknown.txt",true));
                        bw.write("<text> "+Word.getText()+" <!text>");
                        bw.newLine();
                        bw.flush();
                        int ID=1+Integer.parseInt(BufferID);
                        BufferID=Integer.toString(ID);
                        Word.setText(Data.getString(ID));
                        Log.setText("["+BufferID+"/"+Data.getLength()+"]"+"\nOK");

                        //Update config file
                        FileWriter fw = new FileWriter("Config.txt");
                        fw.write("Path="+PathText.getText()+"\nIDTx="+BufferID);
                        fw.flush();
                        fw.close();
                    } catch(Exception E){ 
                        E.printStackTrace(); 
                    }
                }
            }
        });

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    Data = new JsonReadData(PathText.getText());
                    Word.setText(Data.getString(Integer.parseInt(BufferID)));
                    Log.setText("["+BufferID+"/"+Data.getLength()+"]");

                    //Update config file
                    FileWriter fw = new FileWriter("Config.txt");
                    fw.write("Path="+PathText.getText()+"\nIDTx="+BufferID);
                    fw.flush();
                    fw.close();
                } catch(Exception E){ E.printStackTrace(); Log.setText("Error!"); System.out.println(PathText.getText());}
            }
        });
    }

    public void getConfig() throws Exception {
        FileReader fr = new FileReader(ConfigPath);
        if (fr == null) BufferID = "0";
        else {
            String tmp="";
            int c;
            while ((c=fr.read()) != -1){
                if (c != '\n'){
                    tmp +=(char)c;
                    if (tmp.length()>5){
                        if (tmp.substring(0, 5).equals("Path=")) BufferPathText+=(char)c;
                        if (tmp.substring(0, 5).equals("IDTx=")) BufferID+=(char)c;
                    }
                }
                else tmp="";
            }
        }
    }

    public static void main(String[] args) throws Exception{
        MainRun obj = new MainRun();
    }
}