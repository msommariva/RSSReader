package com.example.matteo.rssreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class RssfeedActivity extends ActionBarActivity implements MyListFragment.OnItemSelectedListener {

    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public void onRssItemSelected(String link)
    {
        DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout())
        {
            fragment.setText(link);
        } else
        {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_URL, link);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh button pressed", Toast.LENGTH_SHORT).show();
                menuItem = item;
                menuItem.setActionView(R.layout.progressbar);
                menuItem.expandActionView();
                TestTask task = new TestTask();
                task.execute("task");
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Setting button pressed", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    private class TestTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground (String... params)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            menuItem.collapseActionView();
            menuItem.setActionView(null);
        }
    }
}
