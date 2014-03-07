package com.davealton.shoppinglist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView shoppingList;
	static final int READ_BLOCK_SIZE = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try
		{
			shoppingList = (TextView) findViewById(R.id.shoppingList);
			//---SD Storage---
            /*File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + 
                "/MyFiles");
            File file = new File(directory, "textfile.txt");
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fIn);*/

            //---Internal Storage---
			FileInputStream fIn = 
					openFileInput("shoppingList.txt");
			InputStreamReader isr = new 
					InputStreamReader(fIn);
            
            
			char[] inputBuffer = new char[READ_BLOCK_SIZE];
			String s = "here";

			int charRead;
			while ((charRead = isr.read(inputBuffer))>0)
			{
				//---convert the chars to a String---
				String readString =
						String.copyValueOf(inputBuffer, 0,
								charRead);
				s += readString;

				inputBuffer = new char[READ_BLOCK_SIZE];
			}
			//---set the EditText to the text that has been 
			// read---
			
			isr.close();
			shoppingList.setText(s);
		}
		catch (IOException ioe) {
			String str = "";
			try
			{
	            //---SD Card Storage---
	            /*File sdCard = Environment.getExternalStorageDirectory();
	            File directory = new File (sdCard.getAbsolutePath() +
	                "/MyFiles");
	            directory.mkdirs();
	            File file = new File(directory, "textfile.txt");
	            FileOutputStream fOut = new FileOutputStream(file);*/

				//---Internal Storage---			
				FileOutputStream fOut =
						openFileOutput("shoppingList.txt",
								MODE_PRIVATE);
				
	                        
				OutputStreamWriter osw = new
						OutputStreamWriter(fOut);

				//---write the string to the file---
				osw.write(str);
				osw.flush(); 
				osw.close();

				//---clears the EditText---
				shoppingList.setText("");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
