package rebus.bottomdialog.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import rebus.bottomdialog.BottomDialog;
import rebus.bottomdialog.Item;

public class MainActivity extends AppCompatActivity {

    private BottomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomDialog(MainActivity.this);
                dialog.title(R.string.app_name);
                dialog.canceledOnTouchOutside(true);
                dialog.cancelable(true);
                dialog.inflateMenu(R.menu.menu_main);
                dialog.setOnItemSelectedListener(new BottomDialog.OnItemSelectedListener() {
                    @Override
                    public boolean onItemSelected(int id) {
                        switch (id) {
                            case R.id.action_social_share:
                                return true;
                            case R.id.action_content_add:
                                Item item = new Item();
                                item.setTitle("New element");
                                item.setIcon(getResources().getDrawable(R.drawable.ic_action_action_bug_report));
                                item.setId(100);
                                dialog.addItem(item);
                                return false;
                            default:
                                return false;
                        }
                    }
                });
                dialog.show();
            }
        });
    }

}
