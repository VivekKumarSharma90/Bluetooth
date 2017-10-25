package com.vivek.android2.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;
    ListView device_name;
    Button turn_on, turn_off, visible, paired_devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turn_on = (Button) findViewById(R.id.turn_on);
        turn_off = (Button) findViewById(R.id.turn_off);
        visible = (Button) findViewById(R.id.visible);
        paired_devices = (Button) findViewById(R.id.paired_devices);
        device_name = (ListView) findViewById(R.id.device_name);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        button_action();
    }

    public void button_action() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.turn_on:
                        Intent turn_on = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(turn_on, 0);
                        break;
                    case R.id.turn_off:
                        bluetoothAdapter.disable();
                        break;
                    case R.id.visible:
                        Intent visibil = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        startActivityForResult(visibil, 0);
                        break;
                    case R.id.paired_devices:
                        ArrayList arrayList = new ArrayList();
                        for (BluetoothDevice bt : bluetoothAdapter.getBondedDevices())
                            arrayList.add(bt.getName());
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
                        device_name.setAdapter(arrayAdapter);
                        break;
                }
            }
        };
        turn_on.setOnClickListener(clickListener);
        turn_off.setOnClickListener(clickListener);
        visible.setOnClickListener(clickListener);
        paired_devices.setOnClickListener(clickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
