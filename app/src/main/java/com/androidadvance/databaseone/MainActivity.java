package com.androidadvance.databaseone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import com.androidadvance.databaseone.data.DataManager;
import com.androidadvance.databaseone.event.DatabaseChangedEvent;
import com.androidadvance.databaseone.model.Doc;
import com.androidadvance.databaseone.service.SyncService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

  private TextView textView_log;
  private Button button_save;
  private MainActivity mContext;
  private DataManager dataManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mContext = MainActivity.this;
    textView_log = (TextView) findViewById(R.id.textView_log);
    button_save = (Button) findViewById(R.id.button_save);

    textView_log.setMovementMethod(new ScrollingMovementMethod());

    dataManager = new DataManager();
    dataManager.mainActivityDatabaseChange();

    button_save.setOnClickListener(v -> {
      startService(SyncService.getStartIntent(mContext));
    });
  }

  @Subscribe public void onDatabaseChangedEvent(DatabaseChangedEvent event) {

    String output = "";
    for(Doc mDoc: event.new_things){
      output += mDoc.headline.main + " \n\n";
    }
    textView_log.setText(output);
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    EventBus.getDefault().unregister(this);
    dataManager.unregisterListener();
    super.onStop();
  }
}
