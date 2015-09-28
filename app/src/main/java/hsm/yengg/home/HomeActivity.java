package hsm.yengg.home;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    String titles[]={"News & Updates","Syllabus","Rules & Regulations","Academic Calendar","Join with us"};
    //int icons[]={R.drawable.update,R.drawable.syllabus,R.drawable.rules_regulations,R.drawable.calendar,R.drawable.join_with_us};
    public NavigationView navigationView;
    private Toolbar mToolbar;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {



                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);


                drawerLayout.closeDrawers();


                switch (menuItem.getItemId()) {



                    case R.id.updates:
                        Toast.makeText(getApplicationContext(), "News & updates Secleted", Toast.LENGTH_SHORT).show();
                        ContentFragmentUpdates fragment = new ContentFragmentUpdates();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                        return true;



                    case R.id.academic_calendar:
                        Toast.makeText(getApplicationContext(), "Academic Calendar Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.join:
                        Toast.makeText(getApplicationContext(), "Please Join With Us Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

       /*recyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        adapter=new NavigationDrawerAdapter(titles,icons);
        recyclerView.setAdapter(adapter);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/

        /*drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer){
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();*/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
