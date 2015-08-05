package com.example.erfan.erfansroom;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.microsoft.windowsazure.mobileservices.*;
import java.net.MalformedURLException;
import java.util.List;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;


public class MainActivity extends ActionBarActivity {
    private MobileServiceClient mClient;
    private MobileServiceTable<erfanroomservice> mToDoTable;
    private erfanroomservice lastStats;
    boolean busy = false;
    boolean failed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView statusView = (TextView) findViewById(R.id.textView);

        final Switch sw1 = (Switch) findViewById(R.id.switch1);
        final Switch sw2 = (Switch) findViewById(R.id.switch2);
        final Switch sw3 = (Switch) findViewById(R.id.switch3);
        final Switch sw4 = (Switch) findViewById(R.id.switch4);
        final Switch sw5 = (Switch) findViewById(R.id.switch5);
        statusView.setText("Initializing...");

        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://erfanhome.azure-mobile.net/",
                    "rctFRmkNiKsYDEltDhlnLxMWXzVWHR12",
                    this);

            // Get the Mobile Service Table instance to use
            mToDoTable = mClient.getTable(erfanroomservice.class);
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    String ex = "no exception";
                    try {
                        statusView.setText("retrieving previous switch stats... ");
                        final List<erfanroomservice> results =
                                mToDoTable.orderBy("__updatedAt", QueryOrder.Ascending).execute().get();
                        //statusView.setText("debug flag ");
                        //statusView.setText(results.size());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lastStats = results.get(results.size() - 1);
                                sw1.setChecked(lastStats.switch1);
                                sw2.setChecked(lastStats.switch2);
                                sw3.setChecked(lastStats.switch3);
                                sw4.setChecked(lastStats.switch4);
                                sw5.setChecked(lastStats.switch5);
                                statusView.setText("ready..");
                            }
                        });


                    } catch (Exception e) {
                        //createAndShowDialog(e, "Error");
                        Log.e("MYAPP", "exception", e);
                    }

                    return null;
                }
            }.execute();

        } catch (MalformedURLException e) {
            //createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
            //statusView.setText("please check your data connection and restart...");
            Log.e("MYAPP", "2ndexception", e);
        }
        //switch 1
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked && !busy) {
                    //code
                    statusView.setText("turning switch1 on...");
                    busy = true;

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                lastStats.switch1 = true;
                                final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (entity.switch1 == lastStats.switch1) {
                                            statusView.setText("switch1 turned on");
                                        } else {
                                            statusView.setText("failed to turn switch1 on");
                                            //failed = true;
                                            sw1.setChecked(false);
                                        }
                                        //statusView.setText(entity.id);
                                    }
                                });

                            } catch (Exception e) {
                                //createAndShowDialog(e, "Error");
                            }

                            return null;
                        }
                    }.execute();
                    busy = false;

                } else {
                    //code
                    if (!busy) {
                        statusView.setText("turning switch1 off");
                        busy = true;

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    lastStats.switch1 = false;
                                    final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (entity.switch1 == lastStats.switch1) {
                                                statusView.setText("switch1 turned off");
                                            } else {
                                                statusView.setText("failed to turn switch1 off");
                                                sw1.setChecked(true);
                                            }
                                            //statusView.setText(entity.id);
                                        }
                                    });
                                } catch (Exception e) {
                                    //createAndShowDialog(e, "Error");
                                }

                                return null;
                            }
                        }.execute();
                        busy = false;
                    }
                }
            }
        });
//switch 2
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked && !busy) {
                    //code
                    statusView.setText("turning switch2 on...");
                    busy = true;

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                lastStats.switch2 = true;
                                final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (entity.switch2 == lastStats.switch2) {
                                            statusView.setText("switch2 turned on");
                                        } else {
                                            statusView.setText("failed to turn switch2 on");
                                            //failed = true;
                                            sw2.setChecked(false);
                                        }
                                        //statusView.setText(entity.id);
                                    }
                                });

                            } catch (Exception e) {
                                //createAndShowDialog(e, "Error");
                            }

                            return null;
                        }
                    }.execute();
                    busy = false;

                } else {
                    //code
                    if (!busy) {
                        statusView.setText("turning switch2 off");
                        busy = true;

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    lastStats.switch2 = false;
                                    final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (entity.switch2 == lastStats.switch2) {
                                                statusView.setText("switch1 turned off");
                                            } else {
                                                statusView.setText("failed to turn switch2 off");
                                                sw2.setChecked(true);
                                            }
                                            //statusView.setText(entity.id);
                                        }
                                    });
                                } catch (Exception e) {
                                    //createAndShowDialog(e, "Error");
                                }

                                return null;
                            }
                        }.execute();
                        busy = false;
                    }
                }
            }
        });

//switch 3
    sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

    {

        public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){

        if (isChecked && !busy) {
            //code
            statusView.setText("turning switch3 on...");
            busy = true;

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        lastStats.switch3 = true;
                        final erfanroomservice entity = mToDoTable.update(lastStats).get();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (entity.switch3 == lastStats.switch3) {
                                    statusView.setText("switch3 turned on");
                                } else {
                                    statusView.setText("failed to turn switch3 on");
                                    //failed = true;
                                    sw3.setChecked(false);
                                }
                                //statusView.setText(entity.id);
                            }
                        });

                    } catch (Exception e) {
                        //createAndShowDialog(e, "Error");
                    }

                    return null;
                }
            }.execute();
            busy = false;

        } else {
            //code
            if (!busy) {
                statusView.setText("turning switch3 off");
                busy = true;

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            lastStats.switch3 = false;
                            final erfanroomservice entity = mToDoTable.update(lastStats).get();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (entity.switch3 == lastStats.switch3) {
                                        statusView.setText("switch3 turned off");
                                    } else {
                                        statusView.setText("failed to turn switch3 off");
                                        sw3.setChecked(true);
                                    }
                                    //statusView.setText(entity.id);
                                }
                            });
                        } catch (Exception e) {
                            //createAndShowDialog(e, "Error");
                        }

                        return null;
                    }
                }.execute();
                busy = false;
            }
        }
    }
    }

    );
        //switch 4
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked && !busy){
                    //code
                    statusView.setText("turning switch4 on...");
                    busy = true;

                    new AsyncTask<Void, Void, Void>(){
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                lastStats.switch4 = true;
                                final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(entity.switch4 == lastStats.switch4){
                                            statusView.setText("switch4 turned on");
                                        }else{
                                            statusView.setText("failed to turn switch4 on");
                                            //failed = true;
                                            sw4.setChecked(false);
                                        }
                                        //statusView.setText(entity.id);
                                    }
                                });

                            } catch (Exception e){
                                //createAndShowDialog(e, "Error");
                            }

                            return null;
                        }
                    }.execute();
                    busy = false;

                }else {
                    //code
                    if(!busy){
                        statusView.setText("turning switch4 off");
                        busy = true;

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    lastStats.switch4 = false;
                                    final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (entity.switch4 == lastStats.switch4) {
                                                statusView.setText("switch4 turned off");
                                            } else {
                                                statusView.setText("failed to turn switch4 off");
                                                sw4.setChecked(true);
                                            }
                                            //statusView.setText(entity.id);
                                        }
                                    });
                                } catch (Exception e) {
                                    //createAndShowDialog(e, "Error");
                                }

                                return null;
                            }
                        }.execute();
                        busy = false;
                    }
                }
            }
        });
        //switch 5
        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked && !busy){
                    //code
                    statusView.setText("turning switch5 on...");
                    busy = true;

                    new AsyncTask<Void, Void, Void>(){
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                lastStats.switch5 = true;
                                final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(entity.switch5 == lastStats.switch5){
                                            statusView.setText("switch5 turned on");
                                        }else{
                                            statusView.setText("failed to turn switch5 on");
                                            //failed = true;
                                            sw5.setChecked(false);
                                        }
                                        //statusView.setText(entity.id);
                                    }
                                });

                            } catch (Exception e){
                                //createAndShowDialog(e, "Error");
                            }

                            return null;
                        }
                    }.execute();
                    busy = false;

                }else {
                    //code
                    if(!busy){
                        statusView.setText("turning switch5 off");
                        busy = true;

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    lastStats.switch5 = false;
                                    final erfanroomservice entity = mToDoTable.update(lastStats).get();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (entity.switch5 == lastStats.switch5) {
                                                statusView.setText("switch5 turned off");
                                            } else {
                                                statusView.setText("failed to turn switch5 off");
                                                sw5.setChecked(true);
                                            }
                                            //statusView.setText(entity.id);
                                        }
                                    });
                                } catch (Exception e) {
                                    //createAndShowDialog(e, "Error");
                                }

                                return null;
                            }
                        }.execute();
                        busy = false;
                    }
                }
            }
        });
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
