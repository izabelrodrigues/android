package com.devspace.ninja.navigationdrawerexample2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends ActionBarActivity {
	
	private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private String[] textOptions;
    private ActionBar actionBar;
    private DrawerItemClickListener listener;
    private Fragment currentFragment;
    
    public static final String OPTION = "option";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initViews();
		onConfigListener();
		onConfigListItem();
		onConfigActionBar();
		//Se ainda não foi clicado em nenhum item do menu (está abrindo pela primeira vez), será exibido o fragment com o texto configurado para a opção01
		if (currentFragment == null) {
			replaceFirstFrag();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	private void initViews(){
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				supportInvalidateOptionsMenu();
			}
		};
		textOptions = getResources().getStringArray(R.array.itens_menu_string);
		actionBar = getSupportActionBar();
		listener = new DrawerItemClickListener(drawerLayout, drawerList,this); 
	}
	
	private void onConfigListener(){
		
		drawerList.setOnItemClickListener(listener);
		drawerLayout.setDrawerListener(drawerToggle);

	}
	
	private void onConfigListItem(){
		
		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, textOptions));
		
	}
	
	private void onConfigActionBar(){
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		drawerToggle.syncState();
	}
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Pass the event to ActionBarDrawerToggle, if it returns
	        // true, then it has handled the app icon touch event
	        if (drawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
	        // Handle your other action bar items...
	        return super.onOptionsItemSelected(item);
	    }
	 /**
	  * Substitui o conteúdo do fragment
	  * @param frag
	  */
	 public final void replaceFragment(final Fragment frag){
		 
		 	currentFragment = frag;
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, frag).commit();
			
		}
	/**
	 * Substistui o conteúdo para o primeiro fragment 
	 */
	private void replaceFirstFrag() {

		currentFragment = new OptionFragment();
		Bundle args = new Bundle();
		args.putInt(OPTION, 0);
		currentFragment.setArguments(args);
		replaceFragment(currentFragment);

	}
}
