package com.honu.aloha.sample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.title_activity_info);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        }

        List<Pair<String, String>> data;
        data = new ArrayList();
        data.add(new Pair("Version", BuildConfig.VERSION_NAME));
        data.add(new Pair("License", "ISC"));
        data.add(new Pair("Author", "Betty Diegel"));

        ListView listView = (ListView) findViewById(R.id.info_listview);

        ListAdapter adapter = new PairAdapter(this, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        showLicenseInfo();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    protected void showLicenseInfo() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("License information");

        TextView tv = new TextView(this);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(Html.fromHtml(getString(R.string.isc_license)));
        tv.setPadding(16, 12, 16, 0);
        alert.setView(tv);

        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    class PairAdapter extends BaseAdapter {

        private Context context;
        private List<Pair<String, String>> data;

        public PairAdapter(Context context, List<Pair<String, String>> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }


        @Override
        public Object getItem(int position) {
            return data.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.info_item, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.title_view);
            TextView valueView = (TextView) rowView.findViewById(R.id.value_view);

            Pair<String, String> p = data.get(position);
            titleView.setText(p.first);
            valueView.setText(p.second);

            return rowView;
        }
    }

}
