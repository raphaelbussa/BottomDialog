/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Raphaël Bussa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package rebus.bottomdialog.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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
                                Intent s = new Intent(android.content.Intent.ACTION_SEND);
                                s.setType("text/plain");
                                s.putExtra(android.content.Intent.EXTRA_TEXT, "https://github.com/rebus007/BottomDialog/issues");
                                startActivity(Intent.createChooser(s, getResources().getText(R.string.action_social_share)));
                                return true;
                            case R.id.action_content_add:
                                Item item = new Item();
                                item.setTitle("New element");
                                item.setIcon(getResources().getDrawable(R.drawable.ic_action_action_bug_report));
                                item.setId(100);
                                dialog.addItem(item);
                                return false;
                            case R.id.action_delete:
                                finish();
                                return true;
                            case R.id.action_bug_report:
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://github.com/rebus007/BottomDialog/issues"));
                                startActivity(i);
                                return true;
                            case R.id.ic_github:
                                Intent g = new Intent(Intent.ACTION_VIEW);
                                g.setData(Uri.parse("https://github.com/rebus007/BottomDialog"));
                                startActivity(g);
                                return true;
                            case 100:
                                Toast.makeText(MainActivity.this, "New element clicked!!!", Toast.LENGTH_SHORT).show();
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
