package fr.outadev.skinswitch.skinlibrary;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import fr.outadev.skinswitch.R;

/**
 * Activity displaying the skin library.
 *
 * @author outadoc
 */
public class SkinLibraryActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skin_library);

		if(getActionBar() != null) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		SkinLibraryPageAdapter adapter = new SkinLibraryPageAdapter(getSupportFragmentManager(), this);
		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.skin_library, menu);

		//set up search view
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(true);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case android.R.id.home:
				this.finish();
				return true;
			case R.id.action_upload_skinmanager: {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(getString(R.string.upload_to_sm_title));
				builder.setMessage(getString(R.string.upload_to_sm_message));

				builder.setPositiveButton(getString(R.string.upload_to_sm_positive), new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse("http://skin.outadoc.fr"));
						startActivity(intent);
					}

				});

				builder.setNegativeButton(getString(R.string.upload_to_sm_negative), null);
				builder.create().show();
				return true;
			}
		}

		return super.onOptionsItemSelected(item);
	}

}
