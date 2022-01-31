package org.techfreak.test;
 
import android.app.Activity;
import android.os.Bundle;
import org.techfreak.floating.Floating;

public class MainActivity extends Activity { 
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		Floating.makeDark(this,"Hola mundo", Floating.LENGTH_LONG).show();
		
    }
	
} 
