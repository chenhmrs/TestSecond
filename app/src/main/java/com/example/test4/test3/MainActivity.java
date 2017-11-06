package com.example.test4.test3;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jdsm.test4.IMyAidlInterface;

/**
 * Created by jdsm on 2017/10/9.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    private IMyAidlInterface iMyAidlInterface;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button_1);
        button.setOnClickListener(this);
        bindService(new Intent("com.example.jesm.test4"), new ServiceConnection()
        {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {

                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {

            }
        }, BIND_AUTO_CREATE);
    }

    public void onClick(View view)
    {
        try
        {
            Toast.makeText(MainActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}

